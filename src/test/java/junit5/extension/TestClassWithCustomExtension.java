package junit5.extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class TestClassWithCustomExtension {

    @RegisterExtension
    static TempFolder tempFolder = new TempFolder("junit-test-folder-");

    @RegisterExtension
    static TempFile tempFile = new TempFile(tempFolder);

    @Test
    public void firstTest() {
        System.out.println("First test, name of folder " + tempFolder.getRoot().toString());
        System.out.println("First test, name of file " + tempFile.getFile().toString());
    }

    @Test
    public void secondTest() {
        System.out.println("Second test, name of folder " + tempFolder.getRoot().toString());
        System.out.println("Second test, name of file " + tempFile.getFile().toString());
    }
}
