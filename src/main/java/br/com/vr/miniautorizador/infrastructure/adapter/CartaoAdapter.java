package br.com.vr.miniautorizador.infrastructure.adapter;

import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.port.CartaoPort;
import br.com.vr.miniautorizador.infrastructure.entities.CartaoEntity;
import br.com.vr.miniautorizador.infrastructure.repositories.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartaoAdapter implements CartaoPort {

    private final CartaoRepository cartaoRepository;
    @Override
    public Integer save(Cartao cartao) {
        CartaoEntity entity = cartaoRepository.save(CartaoEntity.fromModel(cartao));
        return entity.getId();
    }

    @Override
    public Optional<Cartao> findByNumeroCartao(Long numero) {
        CartaoEntity cartaoEntity = cartaoRepository.findByNumero(numero);
        if (cartaoEntity == null) {
            return Optional.empty();
        } else {
            return Optional.of(CartaoEntity.toModel(cartaoEntity));
        }
    }
}
