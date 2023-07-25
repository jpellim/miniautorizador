package br.com.vr.miniautorizador.domain.rules.validacao_transacao;

import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import br.com.vr.miniautorizador.domain.models.Cartao;

public interface TemValidacaoTransacao {

    void validar(TransacaoInput input, Cartao cartao);

}
