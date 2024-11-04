package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) { // a função da classe é a save

        // salva os dados que vem preenchidos pelo cliente. mas tb vem esses valores
        // preencidos por default (definido na regra do negocio)
        cliente.setHabilitado(Boolean.TRUE);
        cliente.setVersao(1L);
        cliente.setDataCriacao(LocalDate.now());
        return repository.save(cliente); // grava o registro no banco
    }

    public List<Cliente> listarTodos() {

        return repository.findAll();
    }

    public Cliente obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Cliente clienteAlterado) {

        Cliente cliente = repository.findById(id).get();
        cliente.setNome(clienteAlterado.getNome());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setFoneCelular(clienteAlterado.getFoneCelular());
        cliente.setFoneFixo(clienteAlterado.getFoneFixo());

        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }

}
