package br.com.ifpe.oxefood.util.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter; //lombok gera get e set automaticamente para as propriedades
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" }) //pesquisar metodo equals
public abstract class EntidadeNegocio implements Serializable {

    private Long id;

    private Boolean habilitado;
    
}
