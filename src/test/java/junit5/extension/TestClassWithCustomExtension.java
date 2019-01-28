package junit5.extension;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@TestClassTimeLogging
@ExtendWith({ExceptionHandlingExtension.class, TimeLoggingTestMethod.class})
public class TestClassWithCustomExtension {

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

    @Test
    void thirdTestThrowingException() {
        System.out.println("Third test, name of folder " + tempFolder.getRoot().toString());
        System.out.println("Third test, name of file " + tempFile.getFile().toString());

        throw new NullPointerException();
    }
}
