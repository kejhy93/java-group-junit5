package junit5.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class TimeLoggingTestMethod implements BeforeEachCallback, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create("timeLoggingTestMethod", "junit5");
    public static final String EMPTY_STRING = "";

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("[AfterEach] TimeLoggingTestMethod");
        double finalTime = System.currentTimeMillis();
        String name = getTestMethodName(context);

        double initialTime = context.getStore(NAMESPACE).get(name, Double.class);

        double diff = finalTime - initialTime;


        System.out.println("TestMethod " + name + " finished in " + diff + " ms");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("[BeforeEach] TimeLoggingTestMethod");
        double initialTime = System.currentTimeMillis();
        String name = getTestMethodName(context);

        context.getStore(NAMESPACE).put(name, initialTime);
    }


    private String getTestMethodName(ExtensionContext context) {
        return context.getTestMethod().map(Method::getName).orElse(EMPTY_STRING);
    }
}
