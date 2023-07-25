package br.com.vr.miniautorizador.infrastructure.entities;

import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.models.StatusTransacao;
import br.com.vr.miniautorizador.domain.models.Transacao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private CartaoEntity cartao;

    private BigDecimal valor;

    public static TransacaoEntity fromModel(Transacao transacao) {

        return TransacaoEntity.builder()
                .cartao(CartaoEntity.fromModel(transacao.getCartao()))
                .valor(transacao.getValor())
                .build();
    }

    public static Transacao toModel(TransacaoEntity entity) {
        return Transacao.builder()
                .cartao(CartaoEntity.toModel(entity.getCartao()))
                .valor(entity.getValor())
                .build();
    }
}
