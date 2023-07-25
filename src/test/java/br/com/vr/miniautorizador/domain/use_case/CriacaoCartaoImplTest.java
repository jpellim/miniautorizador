package br.com.vr.miniautorizador.domain.use_case;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.vr.miniautorizador.domain.models.Cartao;
import br.com.vr.miniautorizador.domain.port.CartaoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CriacaoCartaoImplTest {

    @Mock
    private CartaoPort cartaoPort;

    private CriacaoCartaoImpl criacaoCartaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        criacaoCartaoImpl =
                new CriacaoCartaoImpl( cartaoPort );
    }

    @Test
    void testCriacaoCriadoComSucesso() {

        Cartao cartao = Cartao.builder()
                .numero(1L)
                .senha("outra-senha")
                .build();

        when(cartaoPort.save(any())).thenReturn(1);

        when(cartaoPort.findByNumeroCartao(any())).thenReturn(Optional.empty());

        criacaoCartaoImpl.criar(cartao);

        assertThat(cartao.getSaldo()).isEqualByComparingTo(BigDecimal.valueOf(500L));

    }

}
