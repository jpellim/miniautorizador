package br.com.vr.miniautorizador.domain.resources;

import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import br.com.vr.miniautorizador.domain.models.Transacao;

public interface ProcessamentoTransacao {

    Transacao executar(TransacaoInput input);
}
