package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.acesso.Perfil;
import br.com.ifpe.oxefood.modelo.acesso.PerfilRepository;
import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.mensagens.EmailService;
import br.com.ifpe.oxefood.util.exception.ClienteException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    private boolean validarDDD(String foneCelular) {
        // Extrai os dois primeiros dígitos do telefone
        String ddd = foneCelular.substring(1, 3);
        // Verifica se o DDD é "81" (ou adicione outros válidos)
        return ddd.equals("81");
    }

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilRepository perfilUsuarioRepository; 

    @Autowired
    private EmailService emailService;

    @Transactional
    public Cliente save(Cliente cliente, Usuario usuarioLogado) { // a função da classe é a save

        if (!validarDDD(cliente.getFoneCelular())) {
            throw new ClienteException(ClienteException.MSG_DDD_81);
        }

        usuarioService.save(cliente.getUsuario()); // antes de salvar cliente, salva usuario

        for (Perfil perfil : cliente.getUsuario().getRoles()) {
           perfil.setHabilitado(Boolean.TRUE);
           cliente.setCriadoPor(usuarioLogado);
           perfilUsuarioRepository.save(perfil);
      }


        // salva os dados que vem preenchidos pelo cliente. mas tb vem esses valores
        // preencidos por default (definido na regra do negocio)
        cliente.setHabilitado(Boolean.TRUE);
        cliente.setVersao(1L);
        cliente.setDataCriacao(LocalDate.now());
        Cliente clienteSalvo = repository.save(cliente);
        emailService.enviarEmailConfirmacaoCadastroCliente(clienteSalvo);

        return clienteSalvo;

    }

    public List<Cliente> listarTodos() {

        return repository.findAll();
    }

    public Cliente obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Cliente clienteAlterado, Usuario usuarioLogado) {

        Cliente cliente = repository.findById(id).get();
        cliente.setNome(clienteAlterado.getNome());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setFoneCelular(clienteAlterado.getFoneCelular());
        cliente.setFoneFixo(clienteAlterado.getFoneFixo());

        cliente.setVersao(cliente.getVersao() + 1);

        cliente.setUltimaModificacaoPor(usuarioLogado);
        repository.save(cliente);
    }

    public void delete(Long id) {

        Cliente cliente = repository.findById(id).get();
        cliente.setHabilitado(Boolean.FALSE); // desabilita cliente
        cliente.setVersao(cliente.getVersao() + 1);

        repository.save(cliente);
    }

    // metodos endereço cliente

    @Transactional
    public EnderecoCliente adicionarEnderecoCliente(Long clienteId, EnderecoCliente endereco) {

        Cliente cliente = this.obterPorID(clienteId);

        // Primeiro salva o EnderecoCliente:

        endereco.setCliente(cliente);
        endereco.setHabilitado(Boolean.TRUE);
        enderecoClienteRepository.save(endereco);

        // Depois acrescenta o endereço criado ao cliente e atualiza o cliente:

        List<EnderecoCliente> listaEnderecoCliente = cliente.getEnderecos();

        if (listaEnderecoCliente == null) {
            listaEnderecoCliente = new ArrayList<EnderecoCliente>();
        }

        listaEnderecoCliente.add(endereco);
        cliente.setEnderecos(listaEnderecoCliente);
        repository.save(cliente);

        return endereco;
    }

    public List<EnderecoCliente> listarEnderecosCliente(Long clienteId) {
        Cliente cliente = obterPorID(clienteId); // Certifique-se de que este método já busca o cliente pelo ID
        return cliente.getEnderecos(); // Assumindo que o Cliente tem uma lista de endereços mapeada.
    }

    @Transactional
    public EnderecoCliente atualizarEnderecoCliente(Long id, EnderecoCliente enderecoAlterado) {

        EnderecoCliente endereco = enderecoClienteRepository.findById(id).get();
        endereco.setRua(enderecoAlterado.getRua());
        endereco.setNumero(enderecoAlterado.getNumero());
        endereco.setBairro(enderecoAlterado.getBairro());
        endereco.setCep(enderecoAlterado.getCep());
        endereco.setCidade(enderecoAlterado.getCidade());
        endereco.setEstado(enderecoAlterado.getEstado());
        endereco.setComplemento(enderecoAlterado.getComplemento());

        return enderecoClienteRepository.save(endereco);
    }

    @Transactional
    public void removerEnderecoCliente(Long idEndereco) {

        EnderecoCliente endereco = enderecoClienteRepository.findById(idEndereco).get();
        endereco.setHabilitado(Boolean.FALSE);
        enderecoClienteRepository.save(endereco);

        Cliente cliente = this.obterPorID(endereco.getCliente().getId());
        cliente.getEnderecos().remove(endereco);
        repository.save(cliente);
    }

}
