package br.com.vr.miniautorizador.api.errors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DadosMensagemErro {

    private String campo;
    private String erro;
}
