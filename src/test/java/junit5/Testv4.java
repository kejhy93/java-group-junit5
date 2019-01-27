package junit5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testv4 {

	@Test
	public void testStringHasOneChar() {
		String text = "a";

		assertEquals(1, text.length());
	}
}