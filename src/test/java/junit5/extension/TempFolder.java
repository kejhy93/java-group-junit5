package junit5.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class TempFolder implements AfterAllCallback {

    private Path root;

    public TempFolder(String path) {
        try {
            root = Files.createTempDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Create folder " + root.toString());
    }

    public Path getRoot() {
        return root;
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Files.walk(root).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        System.out.println("Folder is deleted " + root.toString());
    }
}
