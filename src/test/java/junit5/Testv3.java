package junit5;

import junit.framework.TestCase;

public class Testv3 extends TestCase {

	public void testStringHasOneChar() {
		String text = "a";

		assertEquals(1, text.length());
	}
}