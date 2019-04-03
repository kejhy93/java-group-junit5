package junit5.param;


import junit5.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    /** Method Source with nice display name */
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

    /** General source of data */
    @ParameterizedTest
    @ArgumentsSource(CustomArgumentSource.class)
    void testCustomArgumentSource( Person person) {
        assertEquals(5, person.getFirstName().length());
        assertEquals(7, person.getLastName().length());
        assertTrue( 0 <= person.getAge() && person.getAge() <= 100);
    }

    /** ------------------------------------ */
    /** ------ CONVERSION ------------------ */
    /** ------------------------------------ */

    @ParameterizedTest
    @ValueSource(strings = { "true" })
    void testImplicitArgumentConversion( boolean param ) {
        assertTrue(param);
    }

    /** Automatic conversion in case factory method or factory constructor */
    /** factory method is non-private, static method declared in class that accepts a single
     * String */
    /** factory constructor is non-private constructor declared in class that accepts a single
     * String */
    /** if more factory methods found they will be ignored */
    /** if factory method and factory constructor are found, factory method is used */
    @ParameterizedTest
    @ValueSource(strings = { "ABCDE FGHIJKL 10", "12345 6789012 5" })
    void testAnotherImplicitArgumentConversion( Person person ) {
        assertEquals(5, person.getFirstName().length());
        assertEquals(7, person.getLastName().length());
        assertTrue ( 0 <= person.getAge() && person.getAge() <= 100);
    }

    /** Explicit conversion */
    @ParameterizedTest
    @ValueSource(strings = { "ABCDE FGHIJKL 10", "12345 6789012 5" })
    void testExplicitFromStringArgumentConversion( @ConvertWith(ToPersonArgumentConvertor.class) Person person ) {
        assertEquals(5, person.getFirstName().length());
        assertEquals(7, person.getLastName().length());
        assertTrue ( 0 <= person.getAge() && person.getAge() <= 100);
    }

    /** Argument aggregation */
    @ParameterizedTest
    @CsvSource({
            "ABCDE, FGHIJKL, 10", "12345, 6789012, 5"
    })
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
        Person person = new Person(arguments.getString(0),
                arguments.getString(1),
                arguments.getInteger(2));

        assertEquals(5, person.getFirstName().length());
        assertEquals(7, person.getLastName().length());
        assertTrue ( 0 <= person.getAge() && person.getAge() <= 100);
    }


    /** Nicer argument aggregation */
    @ParameterizedTest
    @CsvSource({
            "ABCDE, FGHIJKL, 10", "12345, 6789012, 5"
    })
    void testWithArgumentsAccessor(@AggregateWith(PersonAggregator.class) Person person) {
        assertEquals(5, person.getFirstName().length());
        assertEquals(7, person.getLastName().length());
        assertTrue ( 0 <= person.getAge() && person.getAge() <= 100);
    }


    /** Nicer argument aggregation with annotation */
    @ParameterizedTest
    @CsvSource({
            "ABCDE, FGHIJKL, 10", "12345, 6789012, 5"
    })
    void testWithArgumentsAccessorAnnotation(@CsvToPerson Person person) {
        assertEquals(5, person.getFirstName().length());
        assertEquals(7, person.getLastName().length());
        assertTrue ( 0 <= person.getAge() && person.getAge() <= 100);
    }
}
