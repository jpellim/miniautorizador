package br.com.vr.miniautorizador.api.v1;

import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoRequest {

    private Long numeroCartao;

    private String senhaCartao;

    private BigDecimal valor;

    TransacaoInput toInput() {
        return TransacaoInput.builder()
                .numeroCartao(numeroCartao)
                .senhaCartao(senhaCartao)
                .valor(valor)
                .build();
    }

}
