import com.api.bicicletario.service.NotificacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NotificacaoServiceTest {

    @InjectMocks
    private NotificacaoService notificacaoService;

    @BeforeEach
    public void setUp() {
        notificacaoService = new NotificacaoService();
    }

    @Test
    public void testEnviarEmail() {
        // Parâmetros para enviar e-mail
        String destinatario = "exemplo@teste.com";
        String assunto = "Assunto do e-mail";
        String mensagem = "Corpo da mensagem";

        // Chama o método para enviar o e-mail
        String retorno = notificacaoService.enviarEmail(destinatario, assunto, mensagem);

        String retornoEsperado = "Enviando e-mail para: " + destinatario + " Assunto: " + assunto + " Mensagem: " + mensagem + " E-mail enviado com sucesso!";
        assertEquals(retornoEsperado, retorno);
    }
}