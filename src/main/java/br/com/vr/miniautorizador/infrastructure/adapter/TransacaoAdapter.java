package br.com.vr.miniautorizador.infrastructure.adapter;

import br.com.vr.miniautorizador.domain.models.Transacao;
import br.com.vr.miniautorizador.domain.port.TransacaoPort;
import br.com.vr.miniautorizador.infrastructure.entities.TransacaoEntity;
import br.com.vr.miniautorizador.infrastructure.repositories.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransacaoAdapter implements TransacaoPort {

    private final TransacaoRepository transacaoRepository;

    @Override
    public Transacao save(Transacao transacao) {
        var transacaoEntity = transacaoRepository.save(TransacaoEntity.fromModel(transacao));
        return TransacaoEntity.toModel(transacaoEntity);
    }
}
