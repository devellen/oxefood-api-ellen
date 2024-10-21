package br.com.ifpe.oxefood.api.entregador;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //substitui a anotação de getter e setter
@Builder //sempre que precisar criar/instanciar um objeto
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorRequest { //cria uma coluna com o atributo
    private String nome;

    private String cpf;

    private String rg;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    
    private String foneCelular;

    private String foneFixo;

    private Integer qtdEntregasRealizadas;

    private Double valorFrete;

    private Boolean ativo;

    public Entregador build() {
        return Entregador.builder()
        .nome(nome)
        .cpf(cpf)
        .rg(rg)
        .dataNascimento(dataNascimento)
        .foneCelular(foneCelular)
        .foneFixo(foneFixo)
        .qtdEntregasRealizadas(qtdEntregasRealizadas)
        .valorFrete(valorFrete)
        .ativo(ativo)
        .build();
    }
}