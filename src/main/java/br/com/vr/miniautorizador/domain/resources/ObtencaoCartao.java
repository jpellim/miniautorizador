package br.com.vr.miniautorizador.domain.resources;

import br.com.vr.miniautorizador.domain.models.Cartao;

import java.math.BigDecimal;

public interface ObtencaoCartao {

    BigDecimal obterSaldo(Long numero);
}
