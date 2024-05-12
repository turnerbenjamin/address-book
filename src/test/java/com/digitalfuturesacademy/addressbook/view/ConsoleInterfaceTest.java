package com.digitalfuturesacademy.addressbook.view;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ConsoleInterfaceTest {

    private Scanner mockScanner;
    private IConsoleInterface testConsoleInterface;
    private PrintStream defaultOut;
    private ByteArrayOutputStream mockOut;
    private final String TEST_MESSAGE = "Test message";
    private final String lineSeparator = System.getProperty("line.separator");


    @BeforeEach
    public void setUp(){
        mockScanner = mock(Scanner.class);
        testConsoleInterface = new ConsoleInterface(mockScanner);
        defaultOut = System.out;
        mockOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockOut));
    }

    @AfterEach
    public void cleanUp(){
        mockScanner = null;
        testConsoleInterface = null;
        System.setOut(defaultOut);
        mockOut = null;
    }

    @Test
    @DisplayName("CI1: Should print passed message to console")
    public void CI1() {
        String expected = TEST_MESSAGE + lineSeparator;
        //Act
        testConsoleInterface.printMessage(TEST_MESSAGE);
        String output = new String(mockOut.toByteArray());
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("CI2: Should print passed prompt to console")
    public void CI2() {
        String expected = TEST_MESSAGE + lineSeparator;
        //Act
        testConsoleInterface.getUserInput(TEST_MESSAGE);
        String output = new String(mockOut.toByteArray());
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("CI3: Should return correct userInput")
    public void CI3() {
        //Arrange
        String testUserInput = "INPUT";
        when(mockScanner.next()).thenReturn(testUserInput);
        //Act
        String actualInput = testConsoleInterface.getUserInput(TEST_MESSAGE);
        assertEquals(testUserInput, actualInput);
    }

}
