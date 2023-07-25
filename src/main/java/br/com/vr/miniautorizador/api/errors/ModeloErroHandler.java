package br.com.vr.miniautorizador.api.errors;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ModeloErroHandler {

    private LocalDateTime data;
    private List<DadosMensagemErro> dadosErro;

}
