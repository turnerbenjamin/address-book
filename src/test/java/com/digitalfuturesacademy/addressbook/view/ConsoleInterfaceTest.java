package com.digitalfuturesacademy.addressbook.view;

import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.testdata.GeneralTestData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ConsoleInterfaceTest {
    @ExtendWith(MockitoExtension.class)

    private final GeneralTestData td = new GeneralTestData();
    private Scanner mockScanner;
    private IUserInterface testConsoleInterface;
    private PrintStream defaultOut;
    private final String lineSeparator = System.lineSeparator();
    private ByteArrayOutputStream mockOut;

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
        String expected = "\u001B[0m" + td.TEST_MESSAGE + "\u001B[0m" + lineSeparator;
        //Act
        testConsoleInterface.printMessage(td.TEST_MESSAGE);
        String output = mockOut.toString();
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("CI2: Should print passed prompt to console")
    public void CI2() {
        //Arrange
        String expected = "\u001B[0m" + td.TEST_MESSAGE + "\u001B[0m" + lineSeparator;
        //Act
        testConsoleInterface.getUserInput(td.TEST_MESSAGE);
        String output = mockOut.toString();
        //Assert
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("CI3: Should return correct userInput")
    public void CI3() {
        //Arrange
        String testUserInput = "INPUT";
        when(mockScanner.nextLine()).thenReturn(testUserInput);
        //Act
        String actualInput = testConsoleInterface.getUserInput(td.TEST_MESSAGE);
        //Assert
        assertEquals(testUserInput, actualInput);
    }

    @Test
    @DisplayName("CI4: Should print passed error message to console with red text")
    public void CI4() {
        //Arrange
        String expected = "\u001B[31m" + td.TEST_MESSAGE + "\u001B[0m" + lineSeparator;
        //Act
        testConsoleInterface.printErrorMessage(td.TEST_MESSAGE);
        String output = mockOut.toString();
        //Assert
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("CI5: Should print passed warning message to console with yellow text")
    public void CI5() {
        //Arrange
        String expected = "\u001B[33m" + td.TEST_MESSAGE + "\u001B[0m" + lineSeparator;
        //Act
        testConsoleInterface.printWarningMessage(td.TEST_MESSAGE);
        String output = mockOut.toString();
        //Assert
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("CI6: Should print passed success message to console with green text")
    public void CI6() {
        //Arrange
        String expected = "\u001B[32m" + td.TEST_MESSAGE + "\u001B[0m" + lineSeparator;
        //Act
        testConsoleInterface.printSuccessMessage(td.TEST_MESSAGE);
        String output = mockOut.toString();
        //Assert
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("CI7: Should print contact's name, phone number and email address when contact passed")
    public void CI7() {
        //Arrange
        String testName = "A", testNumber = "1", testEmail = "@";
        IImmutableContact mockContact = mock(IImmutableContact.class);
        when(mockContact.getName()).thenReturn(testName);
        when(mockContact.getPhoneNumber()).thenReturn(testNumber);
        when(mockContact.getEmailAddress()).thenReturn(testEmail);
        //Act
        testConsoleInterface.printContact(mockContact);
        String output = mockOut.toString();
        assertAll(
                ()->assertTrue(output.contains(testName)),
                ()->assertTrue(output.contains(testNumber)),
                ()->assertTrue(output.contains(testEmail))
        );
    }

    @Test
    @DisplayName("CI8: Should throw error where contact is null")
    public void CI8() {
        assertThrows(IllegalArgumentException.class, ()->testConsoleInterface.printContact(null));
    }

    @Test
    @DisplayName("CI9: Should print each key and value of passed menu")
    public void CI9() {
        //Arrange
        SortedMap<String,String> testMenu = new TreeMap<>();
        testMenu.put("1", "Option One");
        testMenu.put("2", "Option Two");
        //act
        testConsoleInterface.printMenu(testMenu);
        String output = mockOut.toString();

        assertAll(
                ()->assertTrue(output.contains("1")),
                ()->assertTrue(output.contains("2")),
                ()->assertTrue(output.contains("Option One")),
                ()->assertTrue(output.contains("Option Two"))
        );
    }

    @Test
    @DisplayName("C10 and CI11: Test invalid menu arguments")
    public void CI10_CI11() {
        //Arrange
        SortedMap<String,String> testMenu = new TreeMap<>();
        assertAll(
                ()->assertThrows(IllegalArgumentException.class, ()->testConsoleInterface.printMenu(null)), //CI10
                ()->assertThrows(IllegalArgumentException.class, ()->testConsoleInterface.printMenu(testMenu)) //CI11
        );
    }
}
