package com.digitalfuturesacademy.addressbook.view;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class ConsoleInterfaceTest {

    private final String TEST_MESSAGE = "Test message";

    private PrintStream defaultOut;
    private ByteArrayOutputStream mockOut;
    private final String lineSeparator = System.getProperty("line.separator");

    @BeforeEach
    public void setUp()
    {
        defaultOut = System.out;
        mockOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockOut));
    }
    @AfterEach
    public void cleanUp()
    {
        System.setOut(defaultOut);
        mockOut = null;
    }

    @Test
    @DisplayName("CI1: Should print passed message to console")
    public void CI1() {
        String expected = TEST_MESSAGE + lineSeparator;
        //Act
        ConsoleInterface.printMessage(TEST_MESSAGE);
        String output = new String(mockOut.toByteArray());
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("CI2: Should print passed prompt to console")
    public void CI2() {
        String expected = TEST_MESSAGE + lineSeparator;
        //Act
        ConsoleInterface.getUserInput(TEST_MESSAGE);
        String output = new String(mockOut.toByteArray());
        assertEquals(expected, output);
    }
}
