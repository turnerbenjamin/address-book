package com.digitalfuturesacademy.addressbook.controller;

import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.model.ImmutableContact;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AddressBookAppTest {


    private AddressBookApp testAddressBookApp;
    private IUserInterface mockUserInterface;
    private IAddressBook mockAddressBook;
    private ArgumentCaptor<String> stringArgumentCaptor;
    private ArgumentCaptor<IImmutableContact> contactArgumentCaptor;
    private List<IImmutableContact> testContacts;

    private final String EXPECTED_TOP_LEVEL_INPUT_PROMPT = "Select an option by number or 'e' to exit:";
    private final String ADD_CONTACT_SELECTION = "1";
    private final String READ_ALL_CONTACTS_SELECTION = "2";
    private final String SEARCH_CONTACTS_SELECTION = "3";
    private final String EXIT_MENU_SELECTION = "e";


    @BeforeEach
    public void setUpAddressBookAppTests(){
        mockUserInterface = mock(IUserInterface.class);
        mockAddressBook = mock(IAddressBook.class);
        testAddressBookApp = new AddressBookApp(mockUserInterface, mockAddressBook);
        stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        contactArgumentCaptor = ArgumentCaptor.forClass(IImmutableContact.class);
        IImmutableContact testContactA = mock(IImmutableContact.class);
        when(testContactA.getName()).thenReturn("Jane Doe");
        when(testContactA.getPhoneNumber()).thenReturn("1111");
        when(testContactA.getEmailAddress()).thenReturn("a@b.c");
        IImmutableContact testContactB = mock(IImmutableContact.class);
        when(testContactB.getName()).thenReturn("John Doe");
        when(testContactB.getPhoneNumber()).thenReturn("1112");
        when(testContactB.getEmailAddress()).thenReturn("b@b.c");

        testContacts = new ArrayList<>();
        testContacts.add(testContactA);
        testContacts.add(testContactB);
    }

    @AfterEach
    public void cleanUpAddressBookAppTests(){
        mockUserInterface = null;
        mockAddressBook = null;
        testAddressBookApp = null;
        stringArgumentCaptor = null;
        contactArgumentCaptor = null;
        testContacts = null;
    }


    @DisplayName("Create Contact Tests")
    @Nested
    class CreateContactTests{
        @Test
        @DisplayName("APP1: Should call add contact, passing a contact object with the correct state")
        public void APP1() {
            // Arrange
            when(mockUserInterface.getUserInput(EXPECTED_TOP_LEVEL_INPUT_PROMPT)).thenReturn(ADD_CONTACT_SELECTION, EXIT_MENU_SELECTION);
            when(mockUserInterface.getUserInput("Enter the contact's name:")).thenReturn("Jane Doe");
            when(mockUserInterface.getUserInput("Enter the contact's phone number:")).thenReturn("11111");
            when(mockUserInterface.getUserInput("Enter the contact's email address:")).thenReturn("a@b.c");
            //Act
            testAddressBookApp.run();
            verify(mockAddressBook, times(1)).addContact(contactArgumentCaptor.capture());
            IImmutableContact contactCreated = contactArgumentCaptor.getValue();
            assertAll(
                    ()->assertEquals("Jane Doe", contactCreated.getName()),
                    ()->assertEquals("11111", contactCreated.getPhoneNumber()),
                    ()-> assertEquals("a@b.c", contactCreated.getEmailAddress())
            );
        }

        @Test
        @DisplayName("APP2: Should handle error when invalid input for new contact")
        public void APP2() {
            // Arrange
            when(mockUserInterface.getUserInput(EXPECTED_TOP_LEVEL_INPUT_PROMPT)).thenReturn(ADD_CONTACT_SELECTION, EXIT_MENU_SELECTION);
            //Act
            testAddressBookApp.run();
            verify(mockUserInterface, times(1)).printErrorMessage(stringArgumentCaptor.capture());
            verify(mockUserInterface, times(2)).printMessage(stringArgumentCaptor.capture());
            assertDoesNotThrow(()->testAddressBookApp.run());
        }

        @Test
        @DisplayName("APP3: Should call printMessage with each contact's name, prefixed with a 1-based index number and separated by newlines")
        public void APP3() {
            // Arrange
            when(mockUserInterface.getUserInput(EXPECTED_TOP_LEVEL_INPUT_PROMPT))
                    .thenReturn(READ_ALL_CONTACTS_SELECTION, EXIT_MENU_SELECTION, EXIT_MENU_SELECTION);
            String expected = "1:\tJane Doe\n2:\tJohn Doe\n";
            //Act
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            testAddressBookApp.run();
            verify(mockUserInterface, times(3)).printMessage(stringArgumentCaptor.capture());
            String actual = stringArgumentCaptor.getAllValues().get(1);
            assertEquals(expected, actual);
        }

    }





}
