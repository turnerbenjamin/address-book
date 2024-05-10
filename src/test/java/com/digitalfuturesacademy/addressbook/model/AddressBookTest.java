package com.digitalfuturesacademy.addressbook.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookTest {
    @DisplayName("Test Add Entry")
    @Nested
    class AddContactTests{
        @Test
        @DisplayName("Should increment the size of contacts by 1 when new contract is added")
        public void AB1() {
        // Arrange
        IImmutableContact testContact = mock(IImmutableContact.class);
        AddressBook testAddressBook = new AddressBook();
        int actual;
        int expected = testAddressBook.size() + 1;
        //Act
        testAddressBook.addContact(testContact);
        actual = testAddressBook.size();
        //Assert
        assertEquals(expected, actual);
    }
    }

}
