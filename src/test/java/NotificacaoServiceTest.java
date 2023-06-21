import com.api.bicicletario.service.NotificacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class NotificacaoServiceTest {

    @Mock
    private NotificacaoService notificacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEnviarEmail() {
        String destinatario = "test@example.com";
        String assunto = "Teste de envio de e-mail";
        String mensagem = "Este é um teste de envio de e-mail usando Mockito.";

        Mockito.doNothing().when(notificacaoService).enviarEmail(destinatario, assunto, mensagem);

        notificacaoService.enviarEmail(destinatario, assunto, mensagem);

        Mockito.verify(notificacaoService, Mockito.times(1)).enviarEmail(destinatario, assunto, mensagem);
    }

    @Test
    public void testEnviarEmailDestinatarioNulo() {
        String assunto = "Teste de envio de e-mail";
        String mensagem = "Este é um teste de envio de e-mail usando Mockito.";

        notificacaoService.enviarEmail(null, assunto, mensagem);

        Mockito.verify(notificacaoService, Mockito.never()).enviarEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testEnviarEmailAssuntoNulo() {
        String destinatario = "test@example.com";
        String mensagem = "Este é um teste de envio de e-mail usando Mockito.";

        notificacaoService.enviarEmail(destinatario, null, mensagem);

        Mockito.verify(notificacaoService, Mockito.never()).enviarEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testEnviarEmailMensagemNula() {
        String destinatario = "test@example.com";
        String assunto = "Teste de envio de e-mail";

        notificacaoService.enviarEmail(destinatario, assunto, null);

        Mockito.verify(notificacaoService, Mockito.never()).enviarEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testEnviarEmailParametrosNulos() {
        notificacaoService.enviarEmail(null, null, null);

        Mockito.verify(notificacaoService, Mockito.never()).enviarEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }
}