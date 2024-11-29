package br.com.ifpe.oxefood.api.configuracao;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.configuracao.ConfiguracaoSistema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoSistemaRequest {
    private String nomeEmpresa;
    private String cnpj;
    private String site;
    private String emailContato;
    private Long tempoMinimoAgendamentoPedidos;
    private Boolean ligarAceitePedidos;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEntradaSistema;

    public ConfiguracaoSistema build() {
        return ConfiguracaoSistema.builder()
            .nomeEmpresa(nomeEmpresa)
            .cnpj(cnpj)
            .site(site)
            .emailContato(emailContato)
            .tempoMinimoAgendamentoPedidos(tempoMinimoAgendamentoPedidos)
            .ligarAceitePedidos(ligarAceitePedidos)
            .dataEntradaSistema(dataEntradaSistema)
            .build();
    }
}
