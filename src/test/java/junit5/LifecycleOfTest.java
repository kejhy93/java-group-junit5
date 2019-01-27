package junit5;

import org.junit.jupiter.api.*;

public class LifecycleOfTest {

    static {
        System.out.println("[Zavedeni tridy]");
    }
    {
        System.out.println("[Vytvoreni instance]");
    }

    @BeforeAll
    public static void setUpBeforeAll() {
        System.out.println("[BeforeAll]");
    }

    @BeforeEach
    public void setUpBeforeEachTest() {
        System.out.println("    [BeforeEach]");
    }

    @AfterEach
    public void tearDownAfterEachTest() {
        System.out.println("    [AfterEach]");
    }

    @AfterAll
    public static void tearDownAfterAll() {
        System.out.println("[AfterAll]");
    }

    {
        System.out.println("[Instance vytvorena]");
    }

    static {
        System.out.println("[Trida zavedena]");
    }


    @Test
    public void firstNormalTest() {
        System.out.println("Execution of first test");
    }

    @Test
    public void secondNormalTest() {
        System.out.println("Execution of second test");
    }
}
