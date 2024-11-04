package br.com.ifpe.oxefood.api.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;

@RestController // é uma api rest
@RequestMapping("/api/cliente") // rota da classe
@CrossOrigin // possibilita requisições feitas pelo react

public class ClienteController {

    @Autowired // instacia o objeto e coloca na função
    private ClienteService clienteService;

    @PostMapping // rota de cadastro
    public ResponseEntity<Cliente> save(@RequestBody ClienteRequest request) { // o parametro vai ser preenchido via
                                                                               // json

        Cliente cliente = clienteService.save(request.build()); // cria o objeto para o cliente request
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) { // o parametro vai ser preenchido via url
        return clienteService.obterPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest request) {

        clienteService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

}
