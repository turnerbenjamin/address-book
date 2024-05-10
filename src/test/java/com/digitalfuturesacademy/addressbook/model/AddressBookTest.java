package com.digitalfuturesacademy.addressbook.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        @DisplayName("AB1-AB3: Test adding a valid contact")
        public void testAddingValidElement() {
            // Arrange
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


    }

}
