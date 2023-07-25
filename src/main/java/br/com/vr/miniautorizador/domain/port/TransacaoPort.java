package br.com.vr.miniautorizador.domain.port;

import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.models.Transacao;

public interface TransacaoPort {

    Transacao save(Transacao transacao);

}
