package com.digitalfuturesacademy.addressbook.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookTest {
    private AddressBook testAddressBook;

    @BeforeEach
    public void setUpForAllTests(){
        testAddressBook = new AddressBook();
    }

    @AfterEach
    public void cleanUpForAllTests(){
        testAddressBook = null;
    }

    @DisplayName("Test Add Entry")
    @Nested
    class AddContactTests{

        private IImmutableContact testContact;
        @BeforeEach
        public void setUpForAddEntryTests(){
            testContact  = mock(IImmutableContact.class);
        }

        @AfterEach
        public void cleanUpForAddEntryTests(){
            testContact = null;
        }

        @Test
        @DisplayName("Should increment the size of contacts by 1 when new contract is added")
        public void AB1() {
            // Arrange
            int actual;
            int expected = testAddressBook.size() + 1;
            //Act
            testAddressBook.addContact(testContact);
            actual = testAddressBook.size();
            //Assert
            assertEquals(expected, actual);
        }
        @Test
        @DisplayName("Should include new contact in contacts when a new contract is added")
        public void AB2() {
            // Arrange
            boolean actual;
            //Act
            testAddressBook.addContact(testContact);
            actual = testAddressBook.getContacts().contains(testContact);
            //Assert
            assertTrue(actual);
        }
    }

}
