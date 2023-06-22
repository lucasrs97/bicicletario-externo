import com.api.bicicletario.BicicletarioApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BicicletarioApplication.class)
class BicicletarioApplicationTest {

    @Test
    void contextLoads() {
        // Verifica se o contexto da aplicação é carregado corretamente
    }

    @Test
    void mainTest() {
        // Testa a execução do método main
        BicicletarioApplication.main(new String[]{});
        // Verifica se a aplicação é iniciada sem lançar exceções
    }
}