import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Home10 {

    @ParameterizedTest
    @ValueSource(strings = {" ", "Hello, world", "1234567891234568667"})
    public void TestLength(String text) {

        assertTrue(text.length() > 15, "Длина менее или равна 15");

    }
}
