package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //substitui a anotação de getter e setter
@Builder //sempre que precisar criar/instanciar um objeto
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest { //essa classe so serve para transferencia de dados

   private String nome;

   @JsonFormat(pattern = "dd/MM/yyyy") //para vim nesse formato no json
   private LocalDate dataNascimento;

   private String cpf;

   private String foneCelular;

   private String foneFixo;

   //instanciação de objeto Cliente
   public Cliente build() { 

       return Cliente.builder()
           .nome(nome)
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .build();
   }

}

