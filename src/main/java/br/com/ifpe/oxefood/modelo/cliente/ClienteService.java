package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
       @Autowired
   private ClienteRepository repository;

   @Transactional 
   public Cliente save(Cliente cliente) { //a função da classe é a save

        //salva os dados que vem preenchidos pelo cliente. mas tb vem esses valores preencidos por default (definido na regra do negocio)
       cliente.setHabilitado(Boolean.TRUE);
       cliente.setVersao(1L);
       cliente.setDataCriacao(LocalDate.now());
       return repository.save(cliente); //grava o registro no banco
   }

}
