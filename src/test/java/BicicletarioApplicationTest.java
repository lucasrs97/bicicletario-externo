import com.api.bicicletario.BicicletarioApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BicicletarioApplication.class)
class BicicletarioApplicationTest {
    @Test
    void testContextLoads() {
        Assertions.assertTrue(true, "Sucesso");
    }

    @Test
    void testMain() {
        Assertions.assertTrue(true);
        BicicletarioApplication.main(new String[]{});
    }
}
