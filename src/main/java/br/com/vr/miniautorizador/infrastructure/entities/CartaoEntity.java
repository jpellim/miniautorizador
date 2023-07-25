package br.com.vr.miniautorizador.infrastructure.entities;

import br.com.vr.miniautorizador.domain.models.Cartao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Long numero;
    private String senha;
    private BigDecimal saldo;

    public static CartaoEntity fromModel(Cartao cartao) {

        return CartaoEntity.builder()
                .id(cartao.getId())
                .numero(cartao.getNumero())
                .senha(cartao.getSenha())
                .saldo(cartao.getSaldo())
                .build();
    }

    public static Cartao toModel(CartaoEntity entity) {
        return Cartao.builder()
                .id(entity.getId())
                .numero(entity.getNumero())
                .senha(entity.getSenha())
                .saldo(entity.getSaldo())
                .build();
    }
}
