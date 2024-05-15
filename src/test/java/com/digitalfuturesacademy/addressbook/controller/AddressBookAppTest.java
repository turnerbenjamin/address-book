package com.digitalfuturesacademy.addressbook.controller;


import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.testdata.AddressBookAppTestData;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AddressBookAppTest {
    @ExtendWith(MockitoExtension.class)

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



    @Captor
    private ArgumentCaptor<SortedMap<String,String>> menuCaptor;


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
        testContacts = Arrays.asList(testContact1, testContact2);
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
    class CreateContactTests {
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
                    () -> assertEquals(td.VALID_NAME, contactCreated.getName()),
                    () -> assertEquals(td.VALID_PHONE_NUMBER, contactCreated.getPhoneNumber()),
                    () -> assertEquals(td.VALID_EMAIL_ADDRESS, contactCreated.getEmailAddress())
            );
        }

        @Test
        @DisplayName("APP2: Should handle error when invalid input for new contact")
        public void APP2() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU)).thenReturn(td.SELECT_ADD_CONTACT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_NAME_FOR_ADD_CONTACT)).thenReturn(td.VALID_NAME);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_PHONE_NUMBER_FOR_ADD_CONTACT)).thenReturn(td.VALID_PHONE_NUMBER);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_PROVIDE_EMAIL_ADDRESS_FOR_ADD_CONTACT)).thenReturn(td.EMAIL_ADDRESS_WITHOUT_AT_SYMBOL);
            //Act
            testAddressBookApp.run();
            assertDoesNotThrow(() -> testAddressBookApp.run());
        }

    }

    @DisplayName("Read Contact Tests")
    @Nested
    class ReadContactTests {

        @Test
        @DisplayName("APP3: Should call printMenu with a 1-based index for keys mapped to usernames as values")
        public void APP3() {

            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_READ_ALL_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            when(testContact1.getName()).thenReturn(td.CONTACT_1_NAME);
            when(testContact2.getName()).thenReturn(td.CONTACT_2_NAME);
            //Act
            testAddressBookApp.run();
            verify(mockUserInterface, times(3)).printMenu(menuCaptor.capture());
            SortedMap<String, String> mapPassed = menuCaptor.getAllValues().get(1);

            assertAll(
                    () -> assertTrue(mapPassed.containsKey("1")),
                    () -> assertTrue(mapPassed.containsKey("2")),
                    () -> assertEquals(mapPassed.get("1"), testContact1.getName()),
                    () -> assertEquals(mapPassed.get("2"), testContact2.getName())
            );

        }

        @Test
        @DisplayName("APP4: Should print error message where no contact's found")
        public void APP4() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_READ_ALL_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface).printErrorMessage(stringArgumentCaptor.capture());

        }

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

    @DisplayName("Update Contact Tests")
    @Nested
    class UpdateContactTests {
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
            assertDoesNotThrow(() -> testAddressBookApp.run());
        }
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

    @DisplayName("Search Contacts Tests")
    @Nested
    class SearchContactsTests {
        @Test
        @DisplayName("APP9: Should call printMessage with each matching contact's name, prefixed with a 1-based index number and separated by newlines where multiple matches")
        public void APP9() {
            // Arrange
            String searchTerm = "searchTerm";
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_SEARCH_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_TYPE_SEARCH_TERM))
                    .thenReturn(searchTerm);
            when(mockAddressBook.searchContacts(searchTerm)).thenReturn(testContacts);
            //Act
            testAddressBookApp.run();

            //Assert
            verify(mockUserInterface, times(3)).printMenu(menuCaptor.capture());
            SortedMap<String,String> mapPassed = menuCaptor.getAllValues().get(1);
            assertAll(
                    ()-> assertTrue(mapPassed.containsKey("1")),
                    ()-> assertTrue(mapPassed.containsKey("2")),
                    ()-> assertEquals(mapPassed.get("1"), testContact1.getName()),
                    ()-> assertEquals(mapPassed.get("2"), testContact2.getName())
            );
        }

        @Test
        @DisplayName("APP10: Should call print contact with matching contact where one match")
        public void APP10() {
            // Arrange
            String searchTerm = "searchTerm";
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_SEARCH_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_TYPE_SEARCH_TERM))
                    .thenReturn(searchTerm);
            when(mockAddressBook.searchContacts(searchTerm)).thenReturn(Collections.singletonList(testContact1));
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface).printContact(testContact1);
        }

        @Test
        @DisplayName("APP11: Should print warning message that no matching contacts found where no matches")
        public void APP11() {
            // Arrange
            String searchTerm = "searchTerm";
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_SEARCH_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_TYPE_SEARCH_TERM))
                    .thenReturn(searchTerm);
            when(mockAddressBook.searchContacts(searchTerm)).thenReturn(new ArrayList<>());
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface).printErrorMessage(any(String.class));
        }
        @Test
        @DisplayName("APP12: Should print error message where search term has no content")
        public void APP12() {
            // Arrange
            String searchTerm = "  ";
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_SEARCH_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_TYPE_SEARCH_TERM))
                    .thenReturn(searchTerm);
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface).printErrorMessage(any(String.class));
        }
    }

    @DisplayName("Delete All Contacts Tests")
    @Nested
    class DeleteAllContactsTests {
        @Test
        @DisplayName("APP13-14: Delete all contacts where user confirms deletion")
        public void APP13_AP14() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_DELETE_ALL_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_CONFIRM_DELETE_ALL_CONTACTS))
                    .thenReturn(td.SELECT_CONFIRM_DELETE_ALL_CONTACTS);
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface).printWarningMessage(any(String.class)); //APP13
            verify(mockAddressBook).deleteAllContacts(); //APP14
        }

        @Test
        @DisplayName("APP15: Delete all contacts where user cancels deletion")
        public void APP15() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_DELETE_ALL_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_CONFIRM_DELETE_ALL_CONTACTS))
                    .thenReturn(td.SELECT_CANCEL_DELETE_ALL_CONTACTS);
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockAddressBook,times(0)).deleteAllContacts();
        }

        @Test
        @DisplayName("APP16: Should re-prompt user for confirmation where invalid input received.")
        public void APP16() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_DELETE_ALL_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            when(mockUserInterface.getUserInput(td.FOR_PROMPT_TO_CONFIRM_DELETE_ALL_CONTACTS))
                    .thenReturn(td.SELECT_INVALID_RESPONSE_FOR_CONFIRMATION_TO_DELETE_ALL_CONTACTS, td.SELECT_CONFIRM_DELETE_ALL_CONTACTS);
            when(mockAddressBook.getContacts()).thenReturn(testContacts);
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface,times(2)).getUserInput(td.FOR_PROMPT_TO_CONFIRM_DELETE_ALL_CONTACTS);
            verify(mockAddressBook,times(1)).deleteAllContacts();
        }

        @Test
        @DisplayName("APP17: Should print error where there are no contacts to delete.")
        public void APP17() {
            // Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_DELETE_ALL_CONTACTS, td.SELECT_EXIT, td.SELECT_EXIT);
            //Act
            testAddressBookApp.run();
            //Assert
            verify(mockUserInterface,times(1)).printErrorMessage(any(String.class));
        }

        @Test
        @DisplayName("APP18: Should re-prompt user for input where invalid top-level menu selection")
        public void APP18(){
            //Arrange
            when(mockUserInterface.getUserInput(td.FOR_SELECT_FROM_MENU))
                    .thenReturn(td.SELECT_INVALID_INPUT_FOR_TOP_LEVEL_MENU, td.SELECT_EXIT);
            //Act
            testAddressBookApp.run();
            //assert
            verify(mockUserInterface,times(2)).getUserInput(td.FOR_SELECT_FROM_MENU);
        }

        @Test
        @DisplayName("APP19-20: Null arguments for dependencies")
        public void APP19_APP20(){
            assertThrows(IllegalArgumentException.class, ()->new AddressBookApp(mockUserInterface, null )); //AP19
        }


    }


}
