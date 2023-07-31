package br.com.vr.miniautorizador.api.v1;

import br.com.vr.miniautorizador.domain.models.Transacao;
import br.com.vr.miniautorizador.domain.resources.ProcessamentoTransacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Transacao Controller", description = "Controller responsável pelas requisições ao serviço de transações.")
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final ProcessamentoTransacao processamentoTransacao;

    @Operation(summary = "Processa uma transação, aprovando ou não",
            description = "Retorna 201 quando sucesso!")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = TransacaoRequest.class), mediaType = "application/json") }),
    })
    @PostMapping
    public Transacao processa(@Valid @RequestBody TransacaoRequest request) {
        return processamentoTransacao.executar(request.toInput());
    }
}
