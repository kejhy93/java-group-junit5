package junit5.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TimeLoggingTestClass implements BeforeAllCallback, AfterAllCallback {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create("timeLoggingTestClass", "junit5");
    public static final String EMPTY_STRING = "";

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("[BeforeAll] TimeLoggingExtension");
        double initialTime = System.currentTimeMillis();
        String name = getTestClassName(context);

        context.getStore(NAMESPACE).put(name, initialTime);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("[AfterAll] TimeLoggingExtension");
        double finalTime = System.currentTimeMillis();
        String name = getTestClassName(context);

        double initialTime = context.getStore(NAMESPACE).get(name, Double.class);

        double diff = finalTime - initialTime;

        System.out.println("TestClass " + name + " finished in " + diff + " ms");
    }


    private String getTestClassName(ExtensionContext context) {
        return context.getTestClass().map(Class::getName).orElse(EMPTY_STRING);
    }
}
