package junit5.dynamictest;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LifecycleOfDynamicTest {

    @BeforeEach
    public void setUp() {
        System.out.println("[BeforeEach]");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("[AfterEach]");
    }

    @TestFactory
    public Stream<DynamicTest> dynamicTests() {
        List<Integer> data = IntStream.iterate(0, i -> i<10, i -> i+1)
                                    .map(i -> i*2)
                                    .collect(
                                            () -> new ArrayList<>(),
                                            (integers, value) -> integers.add(value),
                                            (integersLeft, integersRight) -> integersLeft.addAll(integersRight)
                                    );

        return DynamicTest.stream(data.iterator(), input -> "Testing " + input, input -> {
            Assertions.assertTrue(SimpleDynamicTest.isOdd(input));
            System.out.println("Testing " + input);
        });
    }

}
