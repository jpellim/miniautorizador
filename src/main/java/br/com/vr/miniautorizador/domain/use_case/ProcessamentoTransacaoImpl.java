package br.com.vr.miniautorizador.domain.use_case;

import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.models.StatusTransacao;
import br.com.vr.miniautorizador.domain.models.Transacao;
import br.com.vr.miniautorizador.domain.port.CartaoPort;
import br.com.vr.miniautorizador.domain.port.TransacaoPort;
import br.com.vr.miniautorizador.domain.resources.ProcessamentoTransacao;
import br.com.vr.miniautorizador.domain.rules.BuscarCartaoExistente;
import br.com.vr.miniautorizador.domain.rules.validacao_transacao.TemValidacaoTransacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProcessamentoTransacaoImpl implements ProcessamentoTransacao {

    private final List<TemValidacaoTransacao> listaValidacaoTransacao;
    private final CartaoPort cartaoPort;
    private final TransacaoPort transacaoPort;

    @Transactional
    @Override
    public Transacao executar(TransacaoInput input) {
        Cartao cartao = BuscarCartaoExistente.validar(cartaoPort, input);
        listaValidacaoTransacao.forEach(t -> t.validar(input, cartao));
        Transacao transacao = Transacao.builder()
                .cartao(cartao)
                .valor(input.getValor())
                .status(StatusTransacao.APROVED)
                .build();
        cartao.setSaldo(cartao.getSaldo().subtract(input.getValor()));
        cartaoPort.save(cartao);
        return transacaoPort.save(transacao);

    }
}
