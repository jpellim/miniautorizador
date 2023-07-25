package br.com.vr.miniautorizador.domain.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Cartao {

    private Integer id;
    private Long numero;
    private String senha;
    private BigDecimal saldo;

}
