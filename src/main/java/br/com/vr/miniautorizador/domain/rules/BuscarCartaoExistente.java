package br.com.vr.miniautorizador.domain.rules;

import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.port.CartaoPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class BuscarCartaoExistente {

    public static Cartao validar(CartaoPort cartaoPort, TransacaoInput input) {
        Optional<Cartao> maybeCartao = cartaoPort.findByNumeroCartao(input.getNumeroCartao());
        if (maybeCartao.isPresent()) {
            return maybeCartao.get();
        } else {
            throw new EntityNotFoundException(String.format("Cartão não encontrado %s", input.getNumeroCartao()));
        }
    }
}
