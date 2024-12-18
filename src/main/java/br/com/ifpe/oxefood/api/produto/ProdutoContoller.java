package br.com.ifpe.oxefood.api.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.produto.CategoriaProdutoService;
import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;
import jakarta.validation.Valid;

@RestController // é uma api rest
@RequestMapping("/api/produto") // rota da classe
@CrossOrigin // possibilita requisições feitas pelo react

public class ProdutoContoller {
    @Autowired
    private ProdutoService produtoService;
    
       @Autowired
   private CategoriaProdutoService categoriaProdutoService;

   
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoRequest request) {
        //Produto produto = produtoService.save(request.build());

        Produto produtoNovo = request.build();
        produtoNovo.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
        Produto produto = produtoService.save(produtoNovo);

        return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) { // o parametro vai ser preenchido via url
        return produtoService.obterPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody @Valid ProdutoRequest request) {

       //produtoService.update(id, request.build());
       Produto produto = request.build();
       produto.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
       produtoService.update(id, produto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
