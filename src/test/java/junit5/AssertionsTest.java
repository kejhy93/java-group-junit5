package junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionsTest {

    @Test
    public void testDouble() {
        double result = 1.0;
        double expected = 0.0;

        double delta = 1.5;

        assertEquals(expected, result, delta, () -> "Delta is too low :)");
    }

}
