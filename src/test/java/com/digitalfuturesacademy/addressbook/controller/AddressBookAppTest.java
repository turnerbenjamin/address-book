package com.digitalfuturesacademy.addressbook.controller;

import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
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
    private final String EXPECTED_TOP_LEVEL_MENU_STRING = "1:\tAdd a contact\n2:\tView all contacts\n3:\tSearch contacts\n";
    private final String EXPECTED_TOP_LEVEL_INPUT_PROMPT = "Select an option by number or 'e' to exit:";
    private final String EXPECTED_INVALID_SELECTION_MESSAGE = "Invalid selection!";

    @BeforeEach
    public void setUpAddressBookAppTests(){
        mockUserInterface = mock(IUserInterface.class);
        mockAddressBook = mock(IAddressBook.class);
        testAddressBookApp = new AddressBookApp(mockUserInterface, mockAddressBook);
        stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        contactArgumentCaptor = ArgumentCaptor.forClass(IImmutableContact.class);
    }

    @AfterEach
    public void cleanUpAddressBookAppTests(){
        mockUserInterface = null;
        mockAddressBook = null;
        testAddressBookApp = null;
        stringArgumentCaptor = null;
        contactArgumentCaptor = null;
    }


    @DisplayName("Create Contact Tests")
    @Nested
    class CreateContactTests{
        @Test
        @DisplayName("APP1: Should call add contact, passing a contact object with the correct state")
        public void APP1() {
            // Arrange
            when(mockUserInterface.getUserInput(EXPECTED_TOP_LEVEL_INPUT_PROMPT)).thenReturn("1", "e");
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
            when(mockUserInterface.getUserInput(EXPECTED_TOP_LEVEL_INPUT_PROMPT)).thenReturn("1", "e");
            //Act
            testAddressBookApp.run();
                verify(mockUserInterface, times(1)).printErrorMessage(stringArgumentCaptor.capture());
                verify(mockUserInterface, times(2)).printMessage(stringArgumentCaptor.capture());
            assertDoesNotThrow(()->testAddressBookApp.run());
        }

    }





}
