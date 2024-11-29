package br.com.ifpe.oxefood.api.produto;

import br.com.ifpe.oxefood.modelo.produto.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //substitui a anotação de getter e setter
@Builder //sempre que precisar criar/instanciar um objeto
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaProdutoRequest {
    private String descricao;

    public CategoriaProduto build() {
        return CategoriaProduto.builder()
        .descricao(descricao)
        .build();
    }
}
