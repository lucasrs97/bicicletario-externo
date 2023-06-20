package com.api.bicicletario.service;

import com.api.bicicletario.dao.CiclistaDAO;
import com.api.bicicletario.dto.CadastrarCiclistaDTO;
import com.api.bicicletario.model.CartaoDeCredito;
import com.api.bicicletario.model.Ciclista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.api.bicicletario.util.Constantes.MENSAGEM_ATIVACAO_CADASTRO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CiclistaServiceTest {

    @Mock
    private CadastrarCiclistaDTO cadastrarCiclistaDTO;

    @Mock
    private Ciclista ciclista;

    @Mock
    private CartaoDeCredito cartaoDeCredito;

    @Mock
    private EmailService emailService;

    @Mock
    private CartaoDeCreditoService cartaoDeCreditoService;

    @Mock
    private CiclistaDAO dao;

    @InjectMocks
    private CiclistaService ciclistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarCiclista_DeveLancarExcecao_QuandoCadastrarCiclistaDTOForNulo() {
        when(cadastrarCiclistaDTO.getCiclista()).thenReturn(null);
        when(cadastrarCiclistaDTO.getCartaoDeCredito()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> ciclistaService.cadastrarCiclista(cadastrarCiclistaDTO));

        verifyNoInteractions(emailService);
        verifyNoInteractions(cartaoDeCreditoService);
        verifyNoInteractions(dao);
    }

    @Test
    void cadastrarCiclista_DeveLancarExcecao_QuandoEmailForInvalido() {
        when(cadastrarCiclistaDTO.getCiclista()).thenReturn(ciclista);
        when(cadastrarCiclistaDTO.getCartaoDeCredito()).thenReturn(cartaoDeCredito);
        when(emailService.emailValido(ciclista.getEmail())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> ciclistaService.cadastrarCiclista(cadastrarCiclistaDTO));

        verify(emailService).emailValido(ciclista.getEmail());
        verifyNoMoreInteractions(emailService);
        verifyNoInteractions(cartaoDeCreditoService);
        verifyNoInteractions(dao);
    }

    @Test
    void cadastrarCiclista_DeveLancarExcecao_QuandoCartaoDeCreditoForInvalido() {
        when(cadastrarCiclistaDTO.getCiclista()).thenReturn(ciclista);
        when(cadastrarCiclistaDTO.getCartaoDeCredito()).thenReturn(cartaoDeCredito);
        when(emailService.emailValido(ciclista.getEmail())).thenReturn(true);
        when(cartaoDeCreditoService.cartaoDeCreditoInvalido(cartaoDeCredito)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> ciclistaService.cadastrarCiclista(cadastrarCiclistaDTO));

        verify(emailService).emailValido(ciclista.getEmail());
        verify(cartaoDeCreditoService).cartaoDeCreditoInvalido(cartaoDeCredito);
        verifyNoMoreInteractions(emailService);
        verifyNoMoreInteractions(cartaoDeCreditoService);
        verifyNoInteractions(dao);
    }
}
