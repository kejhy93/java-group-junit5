package junit5.param;

import junit5.Person;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.platform.commons.util.Preconditions;

import java.util.Objects;

public class ToPersonArgumentConvertor extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        Preconditions.condition(source.getClass() == String.class, "Convert only from String");
        Preconditions.condition(targetType == Person.class, "Convert to Person");

            String[] parsedInput = ((String) source).split(" ");

            Objects.requireNonNull(parsedInput);
            Preconditions.condition(3 == parsedInput.length, "It has to be 3 params :(");

            return new Person(parsedInput[0], parsedInput[1], Integer.parseInt(parsedInput[2]));
    }
}
