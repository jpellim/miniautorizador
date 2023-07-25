package br.com.vr.miniautorizador.domain.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Transacao {

    private Integer id;

    private Cartao cartao;

    private BigDecimal valor;

    private StatusTransacao status;

    private String motivoReprovacao;
}
