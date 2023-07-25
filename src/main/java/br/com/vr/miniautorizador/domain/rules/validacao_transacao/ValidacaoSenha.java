package br.com.vr.miniautorizador.domain.rules.validacao_transacao;

import br.com.vr.miniautorizador.domain.exception.BusinessException;
import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import br.com.vr.miniautorizador.domain.models.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidacaoSenha implements TemValidacaoTransacao {

    @Override
    public void validar(TransacaoInput input, Cartao cartao) {
        if (!input.getSenhaCartao().equals(cartao.getSenha())) {
            throw new BusinessException("SENHA_INVALIDA");
        }
    }
}
