package br.com.vr.miniautorizador.domain.use_case;

import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.port.CartaoPort;
import br.com.vr.miniautorizador.domain.resources.CriacaoCartao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CriacaoCartaoImpl implements CriacaoCartao {

    private static final BigDecimal SALDO_INICIAL_CARTAO = BigDecimal.valueOf(500L);

    private final CartaoPort cartaoPort;

    @Transactional
    @Override
    public Integer criar(Cartao cartao) {
        Optional<Cartao> cartaoOptional = cartaoPort.findByNumeroCartao(cartao.getNumero());
        if (cartaoOptional.isPresent()) {
            throw new RuntimeException("Cartão já está sendo utilizado.");
        }
        cartao.setSaldo(SALDO_INICIAL_CARTAO);
        return cartaoPort.save(cartao);
    }
}
