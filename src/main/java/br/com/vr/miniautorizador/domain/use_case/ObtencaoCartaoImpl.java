package br.com.vr.miniautorizador.domain.use_case;

import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.port.CartaoPort;
import br.com.vr.miniautorizador.domain.resources.CriacaoCartao;
import br.com.vr.miniautorizador.domain.resources.ObtencaoCartao;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ObtencaoCartaoImpl implements ObtencaoCartao {

    private final CartaoPort cartaoPort;

    @Transactional(readOnly = true)
    @Override
    public BigDecimal obterSaldo(Long numero) {
        Optional<Cartao> cartaoOptional = cartaoPort.findByNumeroCartao(numero);
        if (!cartaoOptional.isPresent()) {
            throw new EntityNotFoundException("Cartão não foi encontrado.");
        }
        return cartaoOptional.get().getSaldo();
    }
}
