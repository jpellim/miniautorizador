package br.com.vr.miniautorizador.domain.use_case;

import br.com.vr.miniautorizador.domain.exception.BusinessException;
import br.com.vr.miniautorizador.domain.input.TransacaoInput;
import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.models.Transacao;
import br.com.vr.miniautorizador.domain.port.CartaoPort;
import br.com.vr.miniautorizador.domain.port.TransacaoPort;
import br.com.vr.miniautorizador.domain.rules.validacao_transacao.TemValidacaoTransacao;
import br.com.vr.miniautorizador.domain.rules.validacao_transacao.ValidacaoSaldo;
import br.com.vr.miniautorizador.domain.rules.validacao_transacao.ValidacaoSenha;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessamentoTransacaoImplIntegrationTest {

    @ClassRule
    public static MySQLContainer mySQLContainer = MysqlContainer.getInstance();

    @Autowired
    private CartaoPort cartaoPort;
    @Autowired
    private TransacaoPort transacaoPort;
    private List<TemValidacaoTransacao> temValidacaoTransacaoList;
    private ProcessamentoTransacaoImpl processamentoTransacaoImpl;

    @BeforeEach
    void setUp() {

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
    void testTransacoesAteSaldoInsuficiente() {



        Cartao cartao = Cartao.builder()
                .numero(1L)
                .senha("#senha1")
                .saldo(BigDecimal.valueOf(500L))
                .build();

        cartaoPort.save(cartao);



        for (int i = 0; i < 5; i++) {
            TransacaoInput input = TransacaoInput.builder()
                    .numeroCartao(1L)
                    .senhaCartao("#senha1")
                    .valor(BigDecimal.valueOf(100L))
                    .build();

            Transacao transacao = processamentoTransacaoImpl.executar(input);

            Optional<Cartao> cartaoOptional = cartaoPort.findByNumeroCartao(1L);
            Cartao cartaoAlterado = cartaoOptional.get();

            switch (i) {
                case 0:
                    assertThat(cartaoAlterado.getSaldo()).isEqualByComparingTo(BigDecimal.valueOf(400L));
                    break;
                case 1:
                    assertThat(cartaoAlterado.getSaldo()).isEqualByComparingTo(BigDecimal.valueOf(300L));
                    break;
                case 2:
                    assertThat(cartaoAlterado.getSaldo()).isEqualByComparingTo(BigDecimal.valueOf(200L));
                    break;
                case 3:
                    assertThat(cartaoAlterado.getSaldo()).isEqualByComparingTo(BigDecimal.valueOf(100L));
                    break;
                case 4:
                    assertThat(cartaoAlterado.getSaldo()).isEqualByComparingTo(BigDecimal.valueOf(0L));
                    break;
            }
        }

        TransacaoInput input = TransacaoInput.builder()
                .numeroCartao(1L)
                .senhaCartao("#senha1")
                .valor(BigDecimal.valueOf(100L))
                .build();


        Exception exception = Assertions.assertThrows(BusinessException.class, () -> {
            processamentoTransacaoImpl.executar(input);
        });

        assertEquals(exception.getMessage(), "SALDO_INSUFICIENTE");


    }


}
