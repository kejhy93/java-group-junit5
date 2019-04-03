package junit5.dynamictest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class SimpleDynamicTest {


    /**
     * JUnit 4 ------------
     */
    @Test
    public void dynamicTestInJUnit4() {
        List<Integer> testData = createTestData(5);

        for ( Integer testCase : testData ) {
            Assertions.assertTrue(isOdd(testCase));
        }
    }

    private List<Integer> createTestData(int counter) {
        return Arrays.asList(0, 2, 6, 4, 10);
    }

    public static boolean isOdd(Integer testedNumber) {
        return (testedNumber%2) == 0;
    }

    /**
     * JUnit 4 ------------
     */

    /**
     * JUnit 5 ------------
     */

    @TestFactory
    Stream<DynamicTest> dynamicTestInJUnit5FirstVersion() {
        List<Integer> testData = createTestData(5);

        return testData.stream().map(input -> DynamicTest.dynamicTest("Testing " + input, () -> Assertions.assertTrue(isOdd(input))));
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestInJUnit5SecondVersion() {
        return DynamicTest.stream(createTestData(5).iterator(), input -> "Testing " + input, input -> Assertions.assertTrue(isOdd(input)));
    }
    /**
     * JUnit 5 ------------
     */
}
