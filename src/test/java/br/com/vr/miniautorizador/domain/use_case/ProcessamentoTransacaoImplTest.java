package br.com.vr.miniautorizador.domain.use_case;

import br.com.vr.miniautorizador.domain.exception.BusinessException;
import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.port.CartaoPort;
import br.com.vr.miniautorizador.domain.port.TransacaoPort;
import br.com.vr.miniautorizador.domain.rules.validacao_transacao.TemValidacaoTransacao;
import br.com.vr.miniautorizador.domain.rules.validacao_transacao.ValidacaoSaldo;
import br.com.vr.miniautorizador.domain.rules.validacao_transacao.ValidacaoSenha;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProcessamentoTransacaoImplTest {

    @Mock
    private CartaoPort cartaoPort;
    @Mock
    private TransacaoPort transacaoPort;
    private List<TemValidacaoTransacao> temValidacaoTransacaoList;
    private ProcessamentoTransacaoImpl processamentoTransacaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        TemValidacaoTransacao validacaoSenha = new ValidacaoSenha();
        TemValidacaoTransacao validacaoSaldo = new ValidacaoSaldo();

        temValidacaoTransacaoList = new ArrayList<>();
        temValidacaoTransacaoList.add(validacaoSaldo);
        temValidacaoTransacaoList.add(validacaoSenha);

        processamentoTransacaoImpl =
                new ProcessamentoTransacaoImpl(temValidacaoTransacaoList
                                              , cartaoPort
                                              , transacaoPort);
    }

    @Test
    void testTransacaoCartaoSenhaInvalida() {

        TransacaoInput input = TransacaoInput.builder()
                .numeroCartao(1L)
                .senhaCartao("#senha1")
                .valor(BigDecimal.valueOf(100L))
                .build();

        Cartao cartao = Cartao.builder()
                .numero(1L)
                .senha("outra-senha")
                .saldo(BigDecimal.valueOf(500L))
                .build();

        when(cartaoPort.findByNumeroCartao(any())).thenReturn(Optional.of(cartao));

        Exception exception = Assertions.assertThrows(BusinessException.class, () -> {
            processamentoTransacaoImpl.executar(input);
        });

        assertEquals(exception.getMessage(), "SENHA_INVALIDA");

    }

    @Test
    void testTransacaoCartaoInexistente() {

        TransacaoInput input = TransacaoInput.builder()
                                        .numeroCartao(1L)
                                        .senhaCartao("#senha1")
                                        .valor(BigDecimal.valueOf(100L))
                                        .build();

        when(cartaoPort.findByNumeroCartao(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            processamentoTransacaoImpl.executar(input);
        });

    }

}
