package junit5.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Files;
import java.nio.file.Path;

public class TempFile implements BeforeEachCallback, AfterEachCallback {

    private Path rootPath;
    private Path tempFile;

    public TempFile(TempFolder folder) {
        rootPath = folder.getRoot();
    }

    public Path getFile() {
        return tempFile;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("[AfterEach] TempFile");
        Files.delete(tempFile);
        System.out.println("File " + tempFile.toString() + " was deleted");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("[BeforeEach] TempFile");
        tempFile = Files.createTempFile(rootPath, "temp-file", "-test.txt");
        System.out.println("File " + tempFile.toString() + " was created");
    }
}
