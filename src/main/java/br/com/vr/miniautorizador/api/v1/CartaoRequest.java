package br.com.vr.miniautorizador.api.v1;

import br.com.vr.miniautorizador.domain.models.Cartao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CartaoRequest {

    @NotNull
    private Long numero;
    @NotBlank
    private String senha;

    Cartao toModel() {
        Cartao cartao = Cartao.builder()
                .numero(numero)
                .senha(senha)
                .build();
        return cartao;
    }

}
