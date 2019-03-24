package junit5.testInterfaces;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface EqualsContract<T> extends Testable<T> {

    T createNotEqualValue();

    @Test
    default void testValueEqualsItself() {
        T value = createValue();
        assertEquals(value, value);
    }

    @Test
    default void testValueDoesNotEqualNull() {
        T value = createValue();
        assertNotEquals(null, value);
    }

    @Test
    default void testValueDoesNotEqualDifferentValue() {
        T value = createValue();
        T diffValue = createNotEqualValue();

        assertNotEquals(value, diffValue);
    }
}
