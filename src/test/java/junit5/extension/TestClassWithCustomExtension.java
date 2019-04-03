package junit5.extension;

import junit5.extension.parameter.ParameterResolverExtension;
import junit5.extension.parameter.RandomParam;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestClassTimeLogging
/** Declarative extension*/
@ExtendWith({ExceptionHandlingExtension.class, TimeLoggingTestMethod.class})
@ExtendWith({EnableConditionOddSumOfMinuteAndSecond.class})
public class TestClassWithCustomExtension {

    /** Programmatic extension*/
    @RegisterExtension
    static TempFolder tempFolder = new TempFolder("junit-test-folder-");

    @RegisterExtension
    static TempFile tempFile = new TempFile(tempFolder);

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("[BeforeAll] TestClassWithCustomException");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("[AfterAll] TestClassWithCustomException");
    }

    @Test
    void firstTest() {
        System.out.println("First test, name of folder " + tempFolder.getRoot().toString());
        System.out.println("First test, name of file " + tempFile.getFile().toString());
    }

    @Test
    void secondTest() {
        System.out.println("Second test, name of folder " + tempFolder.getRoot().toString());
        System.out.println("Second test, name of file " + tempFile.getFile().toString());
    }

    /** Extension for exception handling */
    @Test
    void thirdTestThrowingException() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Third test, name of folder " + tempFolder.getRoot().toString());
        System.out.println("Third test, name of file " + tempFile.getFile().toString());

        throw new NullPointerException();
    }


    /** Extension for method */
    @Test
    @ExtendWith(ParameterResolverExtension.class)
    public void testParamTest(@RandomParam String value) {
        System.out.println("Received parameter=\"" + value + "\"");
        Assertions.assertTrue(value.startsWith("Result"));
        Assertions.assertTrue(value.endsWith("."));
    }
}
