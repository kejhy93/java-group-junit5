package junit5.param;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamTest {

    @ParameterizedTest()
    @ValueSource(ints = {2, 4, 6, 8, 10})
    public void simpleIntsParamTest( final int number) {
        assertTrue( (number%2) == 0);
    }

    @ParameterizedTest()
    @MethodSource("generateOddNumbers")
    public void simpleParamGeneratedTest( final int number) {
        assertTrue((number%2) == 0);
    }

    static Stream<Integer> generateOddNumbers() {
        return Stream.of(2, 4, 6, 8, 10);
    }
}
