package br.com.vr.miniautorizador.api.v1;

import br.com.vr.miniautorizador.domain.resources.CriacaoCartao;
import br.com.vr.miniautorizador.domain.resources.ObtencaoCartao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Tag(name = "Cartao Controller", description = "Controller responsável pelas requisições ao serviço de cartões.")
@RequestMapping("/v1/cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final ObtencaoCartao obtencaoCartao;
    private final CriacaoCartao criacaoCartao;

    @Operation(summary = "Obtenção de saldo do cartão",
            description = "Retorna 200 quando sucesso!")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Long.class), mediaType = "application/json") }),
    })
    @GetMapping("/{numeroCartao}")
    public BigDecimal obterSaldo(@PathVariable Long numeroCartao) {
        return obtencaoCartao.obterSaldo(numeroCartao);
    }

    @Operation(summary = "Cria um novo cartão",
            description = "Retorna 201 quando sucesso!")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CartaoRequest.class), mediaType = "application/json") }),
    })
    @PostMapping
    public Integer criar(@Valid @RequestBody CartaoRequest request) {
        return criacaoCartao.criar(request.toModel());
    }
}
