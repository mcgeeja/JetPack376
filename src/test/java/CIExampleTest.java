import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CIExampleTest {
    @BeforeAll
    static void setUpBeforeClass() {
        Sound.audioEnabled=false;
    }

    @Test
    public void test() {
        Assertions.assertTrue(true);
    }
}
