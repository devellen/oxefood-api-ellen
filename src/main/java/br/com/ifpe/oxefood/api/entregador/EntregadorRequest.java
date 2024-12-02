package br.com.ifpe.oxefood.api.entregador;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // substitui a anotação de getter e setter
@Builder // sempre que precisar criar/instanciar um objeto
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorRequest { // cria uma coluna com o atributo
    private String nome;

    @NotBlank(message = "O CPF é de preenchimento obrigatório") // verifica se é nulo ou vazio
    @CPF
    private String cpf;

    private String rg;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String foneCelular;

    private String foneFixo;

    private Integer qtdEntregasRealizadas;

    private Double valorFrete;

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String cep;

    private String uf;

    private String complemento;

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
                .rua(rua)
                .numero(numero)
                .bairro(bairro)
                .cidade(cidade)
                .cep(cep)
                .uf(uf)
                .complemento(complemento)
                .ativo(ativo)
                .build();
    }
}
