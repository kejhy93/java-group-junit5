package junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testv5 {

	@Test
	public void testStringHasOneChar() {
		String text = "a";

		assertEquals(1, text.length());
	}
}