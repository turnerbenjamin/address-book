package com.digitalfuturesacademy.addressbook.model;

import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookTest {
    private AddressBook testAddressBook;
    private IImmutableContact[] testContacts;

    @BeforeEach
    public void setUpForAllTests(){
        String[] testNames = new String[] {"Jane Doe", "John Doe", "Fox Mulder"};
        testAddressBook = new AddressBook();
        testContacts = new IImmutableContact[testNames.length];
        for(int i = 0; i<testNames.length; i++){
            IImmutableContact testContact = mock(IImmutableContact.class);
            when(testContact.getName()).thenReturn(testNames[i]);
            testContacts[i] = testContact;
        }
    }

    @AfterEach
    public void cleanUpForAllTests(){
        testAddressBook = null;
        testContacts = null;
    }

    @DisplayName("Test Add Entry")
    @Nested
    class AddContactTests{

        @Test
        @DisplayName("AB1-AB3: Test adding a valid contact")
        public void testAddingValidElement() {
            // Arrange
            IImmutableContact testContact = testContacts[0];
            int startingAddressBookSize = testAddressBook.size();
            //Act
            boolean result = testAddressBook.addContact(testContact);
            //Assert
            assertAll(
                    () -> assertEquals(startingAddressBookSize + 1 , testAddressBook.size()),
                    () -> assertTrue(testAddressBook.getContacts().contains(testContact)),
                    () -> assertTrue(result)
            );
        }
        @Test
        @DisplayName("AB4: Should throw exception when a contact with a value of null is added")
        public void testAddingNull() {
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(null));
        }
    }

    @DisplayName("Test Search Contacts")
    @Nested
    class SearchContactTests{

        @BeforeEach
        public void setUpForSearchContactTests(){
            testAddressBook.addContact(testContacts[0]);
            testAddressBook.addContact(testContacts[1]);
            testAddressBook.addContact(testContacts[2]);
        }

        @Test
        @DisplayName("AB5-6: Test search where search term matches one contact")
        public void testSearchWhereSearchTermMatchesOneContact() {
            //Arrange
            IImmutableContact testContact = testContacts[0];

            String searchTermMatchingOneContactByName = testContact.getName().substring(0,4);
            //Assert
            assertAll(
                    () -> assertEquals(1,testAddressBook.searchContacts(searchTermMatchingOneContactByName).size()),
                    () -> assertTrue(testAddressBook.searchContacts(searchTermMatchingOneContactByName).contains(testContact))
            );
        }

        @Test
        @DisplayName("AB7: Test search where search term does not match any contacts")
        public void testSearchWhereSearchTermDoesNotMatchAnyContacts() {
            //Arrange
            String testSearchTerm = "SEARCH TERM";
            //Assert
            assertEquals(0,testAddressBook.searchContacts(testSearchTerm).size());
        }

        @Test
        @DisplayName("AB8-9: Test search where search term matches two contacts")
        public void testSearchWhereSearchTermMatchesTwoContacts() {
            //Arrange
            List<IImmutableContact> matchingContacts = new ArrayList<>();
            matchingContacts.add(testContacts[0]);
            matchingContacts.add(testContacts[1]);
            String searchTermMatchingTwoContactsByName = "Doe";
            //Assert
            assertAll(
                    () -> assertEquals(2,testAddressBook.searchContacts(searchTermMatchingTwoContactsByName).size()),
                    () -> assertTrue(testAddressBook.searchContacts(searchTermMatchingTwoContactsByName).containsAll(matchingContacts))
            );
        }

        @Test
        @DisplayName("AB10-11: Test search trimmed and case-insensitive")
        public void testSearchTermTrimmedAndCaseInsensitive() {
            //Arrange
            IImmutableContact testContact = testContacts[0];
            String searchTermMatchingOneContactByName = testContact.getName().substring(0,4);
            //Assert
            assertAll(
                    () -> assertTrue(testAddressBook.searchContacts(searchTermMatchingOneContactByName.toUpperCase()).contains(testContact)),
                    () -> assertTrue(testAddressBook.searchContacts(searchTermMatchingOneContactByName.concat("  ")).contains(testContact))
            );
        }
    }

    @DisplayName("Test Delete Contact")
    @Nested
    class DeleteContactTests{

        @BeforeEach
        public void setUpForDeleteContactTests(){
            testAddressBook.addContact(testContacts[0]);
            testAddressBook.addContact(testContacts[1]);
        }

        @Test
        @DisplayName("AB12-14: Test deleting a contact in contacts")
        public void testDeletingAContactInContacts() {
            //Arrange
            int startingContactsSize = testAddressBook.size();
            IImmutableContact testContactToDelete = testContacts[0];
            IImmutableContact actual;
            //Act
            actual = testAddressBook.deleteContact(testContactToDelete);
            //Assert
            assertAll(
                    () -> assertEquals(startingContactsSize - 1,testAddressBook.size()),
                    () -> assertFalse(testAddressBook.getContacts().contains(testContactToDelete)),
                    () -> assertEquals(testContactToDelete,actual)
            );
        }

    }
}
