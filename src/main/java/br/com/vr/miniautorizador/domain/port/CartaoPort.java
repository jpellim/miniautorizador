package br.com.vr.miniautorizador.domain.port;

import br.com.vr.miniautorizador.domain.models.Cartao;

import java.util.Optional;

public interface CartaoPort {

    Integer save(Cartao cartao);

    Optional<Cartao> findByNumeroCartao(Long numero);

}
