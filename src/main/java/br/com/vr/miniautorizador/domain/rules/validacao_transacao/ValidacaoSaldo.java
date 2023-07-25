package br.com.vr.miniautorizador.domain.rules.validacao_transacao;

import br.com.vr.miniautorizador.domain.exception.BusinessException;
import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import br.com.vr.miniautorizador.domain.models.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ValidacaoSaldo implements TemValidacaoTransacao {

    @Override
    public void validar(TransacaoInput input, Cartao cartao) {
        if (cartao.getSaldo().compareTo(input.getValor()) < 0) {
            throw new BusinessException("SALDO_INSUFICIENTE");
        }
    }
}
