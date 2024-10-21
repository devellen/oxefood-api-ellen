package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

//1º arg: qual classe essa interface se refere
//2º arg: o tipo do dado da chave primária
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
