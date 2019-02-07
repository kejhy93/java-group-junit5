package junit5.param;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Basic parameterized test")
public class BaseParameterizedTest {

    @ParameterizedTest()
    @ValueSource( ints = { 1, 2, 3, 10, 11, 12, 15})
    void testParameterizedIntegers( final int number) {
        assertTrue(number < 20);
    }

    @ParameterizedTest()
    @EnumSource( TimeUnit.class)
    void testAllEnumValues( TimeUnit unit) {
        System.out.println(unit.toString());
    }

    @ParameterizedTest(name="Test prime number \"{0}\"")
    @MethodSource("generatePrimeNumbers")
    void testPrimeNumbers(final int number) {
        assertTrue ( BigInteger.valueOf(number).isProbablePrime(1), "Number " + number + " is prime number");
    }


    static Collection<Integer> generatePrimeNumbers() {
        return List.of(3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37);
    }

    @ParameterizedTest(name="Name test first name is \"{0}\", last name is \"{1}\"")
    @MethodSource("generateNames")
    void testNamesMethodSource(String firstName, String lastName) {
        assertTrue(!firstName.isBlank());
        assertTrue(!lastName.isBlank());
    }

    static Stream<Arguments> generateNames() {
        return Stream.of(
                Arguments.of ( "A", "B"),
                Arguments.of ( "C", "D"),
                Arguments.of ( "E", "F")
        );
    }

    @ParameterizedTest(name="CSV parameter test")
    @CsvSource( value = {"A, B", "C, D", "E, F", "G, H", "A, 'B, C, D'"})
    void testNamesCsv(String firstName, String lastName) {
        assertTrue(!firstName.isBlank());
        assertTrue(!lastName.isBlank());
    }

    @ParameterizedTest(name="CSV file parameter test")
    @CsvFileSource(resources =  { "/test-input-1"})
    void testFileNamesCsv(String firstName, String lastName) {
        assertTrue(!firstName.isBlank());
        assertTrue(!lastName.isBlank());
    }
}
