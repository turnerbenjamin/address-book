package com.digitalfuturesacademy.addressbook.controller;


import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AddressBookAppTest {

    private final AddressBookAppTestData td = new AddressBookAppTestData();
    private AddressBookApp testAddressBookApp;
    private IUserInterface mockUserInterface;
    private IAddressBook mockAddressBook;
    private ArgumentCaptor<String> stringArgumentCaptor;
    private ArgumentCaptor<IImmutableContact> contactArgumentCaptor;
    private List<IImmutableContact> testContacts;
    private IImmutableContact testContact1;
    private IImmutableContact testContact2;
    private IImmutableContact updatedContact;

    @BeforeEach
    public void setUpTestAddressBookAndMockDependencies(){
        mockUserInterface = mock(IUserInterface.class);
        mockAddressBook = mock(IAddressBook.class);
        testAddressBookApp = new AddressBookApp(mockUserInterface, mockAddressBook);

    }

    @BeforeEach
    public void setUpArgumentCaptors(){
        stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        contactArgumentCaptor = ArgumentCaptor.forClass(IImmutableContact.class);
    }

    @BeforeEach
    public void setUpTestUsers(){
        //UPDATED CONTACT
        updatedContact = mock(IImmutableContact.class);
        testContact1 = mock(IImmutableContact.class);
        testContact2 = mock(IImmutableContact.class);
        testContacts = Arrays.asList(testContact1, testContact2, updatedContact);
    }



    @AfterEach
    public void cleanUpTestAddressBookAndMockDependencies() {
        mockUserInterface = null;
        mockAddressBook = null;
        testAddressBookApp = null;
    }

    @AfterEach
    public void cleanUpArgumentCaptors(){
        stringArgumentCaptor = null;
        contactArgumentCaptor = null;
    }

    @AfterEach
    public void cleanUpTestUsers(){
        testContact1 = null;
        testContact2 = null;
        updatedContact = null;
        testContacts = null;
    }


    @DisplayName("Create Contact Tests")
    @Nested
    class CreateContactTests{
        @Test
        @DisplayName("APP1: Should call add contact, passing a contact object with the correct state")
        public void APP1() {
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU)).thenReturn(td.SELECT_ADD_CONTACT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_NAME_FOR_ADD_CONTACT)).thenReturn(td.VALID_NAME);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_PHONE_NUMBER_FOR_ADD_CONTACT)).thenReturn(td.VALID_PHONE_NUMBER);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_EMAIL_ADDRESS_FOR_ADD_CONTACT)).thenReturn(td.VALID_EMAIL_ADDRESS);

            //Act
            testAddressBookApp.run();
            verify(mockAddressBook, times(1)).addContact(contactArgumentCaptor.capture());
            IImmutableContact contactCreated = contactArgumentCaptor.getValue();

            //Assert
            assertAll(
                    ()->assertEquals(td.VALID_NAME, contactCreated.getName()),
                    ()->assertEquals(td.VALID_PHONE_NUMBER, contactCreated.getPhoneNumber()),
                    ()-> assertEquals(td.VALID_EMAIL_ADDRESS, contactCreated.getEmailAddress())
            );
        }

        @Test
        @DisplayName("APP2: Should handle error when invalid input for new contact")
        public void APP2() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU)).thenReturn(td.SELECT_ADD_CONTACT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_NAME_FOR_ADD_CONTACT)).thenReturn(td.VALID_NAME);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_PHONE_NUMBER_FOR_ADD_CONTACT)).thenReturn(td.VALID_PHONE_NUMBER);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_EMAIL_ADDRESS_FOR_ADD_CONTACT)).thenReturn(td.INVALID_EMAIL_ADDRESS);
            //Act
            testAddressBookApp.run();
            assertDoesNotThrow(()->testAddressBookApp.run());
        }
        @ExtendWith(MockitoExtension.class)
        @Captor
        private ArgumentCaptor<SortedMap<String,String>> menuCaptor;
        @Test
        @DisplayName("APP3: Should call printMenu with a 1-based index for keys mapped to usernames as values")
        public void APP3() {

            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_READ_ALL_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            when(testContact1.getName()).thenReturn(td.USER_1_NAME);
            when(testContact2.getName()).thenReturn(td.USER_2_NAME);
            //Act
            testAddressBookApp.run();
            verify(mockUserInterface, times(3)).printMenu(menuCaptor.capture());
            SortedMap<String,String> mapPassed = menuCaptor.getAllValues().get(1);

            assertAll(
                    ()-> assertTrue(mapPassed.containsKey("1")),
                    ()-> assertTrue(mapPassed.containsKey("2")),
                    ()-> assertTrue(mapPassed.get("1").equals(testContact1.getName())),
                    ()-> assertTrue(mapPassed.get("2").equals(testContact2.getName()))
            );

        }

        @Test
        @DisplayName("APP4: Should call printMessage with the contact's name, phone number and email address when contact selected")
        public void APP4() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_READ_ALL_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface).printErrorMessage(stringArgumentCaptor.capture());
        }

        @Test
        @DisplayName("APP5: Should call printContact with the selected contact")
        public void APP5() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_READ_ALL_CONTACTS, td.SELECT_CONTACT_1, td.SELECT_EXIT, td.SELECT_EXIT);//Act
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface).printContact(testContact1);

        }

        @Test
        @DisplayName("APP6: Should update contact in contacts with correct values")
        public void APP6() {
            // Arrange
            IImmutableContact contactToUpdate = testContacts.get(0);
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_READ_ALL_CONTACTS, td.SELECT_CONTACT_1, td.SELECT_UPDATE_CONTACT, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_NAME_FOR_UPDATE_CONTACT)).thenReturn(td.VALID_NAME);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_PHONE_NUMBER_FOR_UPDATE_CONTACT)).thenReturn(td.VALID_PHONE_NUMBER);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_EMAIL_ADDRESS_FOR_UPDATE_CONTACT)).thenReturn(td.VALID_EMAIL_ADDRESS);
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            when(contactToUpdate.withName(any(String.class))).thenReturn(updatedContact);
            when(updatedContact.withPhoneNumber(any(String.class))).thenReturn(updatedContact);
            when(updatedContact.withEmailAddress(any(String.class))).thenReturn(updatedContact);
            //Act
            testAddressBookApp.run();
            //Assert
            verify(contactToUpdate).withName(td.VALID_NAME);
            verify(updatedContact).withPhoneNumber(td.VALID_PHONE_NUMBER);
            verify(updatedContact).withEmailAddress(td.VALID_EMAIL_ADDRESS);
            verify(mockAddressBook).replaceContact(any(IImmutableContact.class), contactArgumentCaptor.capture());
            assertEquals(updatedContact, contactArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("APP7: Should handle error when invalid input for contact updates")
        public void APP7() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_READ_ALL_CONTACTS, td.SELECT_CONTACT_1, td.SELECT_UPDATE_CONTACT, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockAddressBook.replaceContact(any(IImmutableContact.class), any(IImmutableContact.class))).thenThrow(new IllegalArgumentException());
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            //Assert
            assertDoesNotThrow(()->testAddressBookApp.run());
        }

        @Test
        @DisplayName("APP8: Should remove contact from contacts")
        public void APP8() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_READ_ALL_CONTACTS, td.SELECT_CONTACT_1, td.SELECT_DELETE_CONTACT, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            testAddressBookApp.run();
            //Assert
            verify(mockAddressBook).deleteContact(testContact1);
        }


    }





}
