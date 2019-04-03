package junit5.extension.parameter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Random;

public class ParameterResolverExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        System.out.println("Is parameter annotated with @RandomParam for test " + extensionContext.getDisplayName());
        return parameterContext.getParameter().isAnnotationPresent(RandomParam.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        System.out.println("Annotated parameter @RandomParam found for test " + extensionContext.getDisplayName());
        return "Result=" + new Random().nextInt() + ".";
    }
}
