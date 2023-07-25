package br.com.vr.miniautorizador.domain.input;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class TransacaoInput {

    private Long numeroCartao;

    private String senhaCartao;

    private BigDecimal valor;
}
