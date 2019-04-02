package junit5.testInterfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface ComparableContract<T extends Comparable<T>> extends Testable<T> {

    T createSmallerValue();
    T createLargerValue();

    @Test
    default void testReturnsZeroWhenComparedToItself() {
        T value = createValue();

        assertEquals(0, value.compareTo(value));
    }

    @Test
    default void testReturnsPositiveNumberWhenComparedToSmallerValue() {
        T value = createValue();
        T smallerValue = createSmallerValue();

        assertTrue(value.compareTo(smallerValue) > 0 );
    }

    @Test
    default void testReturnsNegativeNumberWhenComparedToLargerValue() {
        T value = createValue();
        T largerValue = createLargerValue();

        assertTrue(value.compareTo(largerValue) < 0 );
    }
}
