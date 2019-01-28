package junit5.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class ExceptionHandlingExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        System.out.println("Found exception " + throwable.toString());

        if ( throwable instanceof NullPointerException) {
            System.out.println("Not this time :)");
        } else {
            throw throwable;
        }
    }
}
