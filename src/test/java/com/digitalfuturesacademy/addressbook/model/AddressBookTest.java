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
        @DisplayName("AB5-6: Test search where search term matches one candidate")
        public void testSearchWhereSearchTermMatchesOneCandidate() {
            //Arrange
            IImmutableContact testContact = testContacts[0];

            String searchTermMatchingOneCandidateByName = testContact.getName().substring(0,4);
            //Assert
            assertAll(
                    () -> assertEquals(1,testAddressBook.searchContacts(searchTermMatchingOneCandidateByName).size()),
                    () -> assertTrue(testAddressBook.searchContacts(searchTermMatchingOneCandidateByName).contains(testContact))
            );
        }

        @Test
        @DisplayName("AB7: Test search where search term does not match any candidates")
        public void testSearchWhereSearchTermDoesNotMatchAnyCandidates() {
            //Arrange
            String testSearchTerm = "SEARCH TERM";
            //Assert
            assertEquals(0,testAddressBook.searchContacts(testSearchTerm).size());
        }

        @Test
        @DisplayName("AB8-9: Test search where search term matches two candidates")
        public void testSearchWhereSearchTermMatchesTwoCandidates() {
            //Arrange
            List<IImmutableContact> matchingCandidates = new ArrayList<>();
            matchingCandidates.add(testContacts[0]);
            matchingCandidates.add(testContacts[1]);
            String searchTermMatchingTwoCandidatesByName = "Doe";
            //Assert
            assertAll(
                    () -> assertEquals(2,testAddressBook.searchContacts(searchTermMatchingTwoCandidatesByName).size()),
                    () -> assertTrue(testAddressBook.searchContacts(searchTermMatchingTwoCandidatesByName).containsAll(matchingCandidates))
            );
        }

        @Test
        @DisplayName("AB10-11: Test search trimmed and case-insensitive")
        public void testSearchTermTrimmedAndCaseInsensitive() {
            //Arrange
            IImmutableContact testContact = testContacts[0];
            String searchTermMatchingOneCandidateByName = testContact.getName().substring(0,4);
            //Assert
            assertAll(
                    () -> assertTrue(testAddressBook.searchContacts(searchTermMatchingOneCandidateByName.toUpperCase()).contains(testContact)),
                    () -> assertTrue(testAddressBook.searchContacts(searchTermMatchingOneCandidateByName.concat("  ")).contains(testContact))
            );
        }


    }
}
