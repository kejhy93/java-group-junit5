package junit5.param;

import junit5.Person;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CustomArgumentSource implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return IntStream.iterate(0, i -> i+1).limit(10).mapToObj(i -> Person.generateRandomPerson()).map(Arguments::of);
    }
}