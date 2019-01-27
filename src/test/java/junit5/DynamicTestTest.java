package junit5;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTestTest {

    @TestFactory
    public Stream<DynamicTest> createTests() {
        return Stream.of(1, 3, 5, 7).map(number -> dynamicTest("Test if " + number + " is odd", () -> isOdd(number)));
    }

    public void isOdd ( final int number) {
        assertTrue ( (number%2) == 1);
    }
}
