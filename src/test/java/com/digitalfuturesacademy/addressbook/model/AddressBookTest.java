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
            when(testContact.getPhoneNumber()).thenReturn(String.format("%011d",i));
            when(testContact.getEmailAddress()).thenReturn(String.format("%d@b.c",i));
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

        @Test
        @DisplayName("AB15: Test deleting a contact not in contacts")
        public void testDeletingAContactNotInContacts() {
            //Arrange
            int startingContactsSize = testAddressBook.size();
            IImmutableContact testContactNotInContactsToDelete = mock(IImmutableContact.class);
            IImmutableContact actual;
            //Act
            actual = testAddressBook.deleteContact(testContactNotInContactsToDelete);
            //Assert
            assertNull(actual);
        }
    }

    @DisplayName("Test Duplicate Checks")
    @Nested
    class DuplicateTestChecks{
        private IImmutableContact testContactA;
        private IImmutableContact testContactB;
        private String testContactAPhoneNumber;
        private String testContactAEmail;

        @BeforeEach
        public void setUpForDuplicateChecks(){
            testContactA = mock(IImmutableContact.class);
            testContactB = mock(IImmutableContact.class);
            when(testContactA.getPhoneNumber()).thenReturn(testContactAPhoneNumber);
            when(testContactA.getEmailAddress()).thenReturn(testContactAEmail);
            when(testContactB.getPhoneNumber()).thenReturn("11111111111");
            when(testContactB.getEmailAddress()).thenReturn("unique@email.com");
        }



        @Test
        @DisplayName("AB16: Test throws error when adding contact with a duplicate phone number.")
        public void AB16() {
            //Arrange
            when(testContactB.getPhoneNumber()).thenReturn(testContactAPhoneNumber);
            //Act
            testAddressBook.addContact(testContactA);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(testContactB));
        }

        @Test
        @DisplayName("AB17: Test throws error when adding contact with a duplicate email address.")
        public void AB17() {
            //Arrange
            when(testContactB.getEmailAddress()).thenReturn(testContactAEmail);
            //Act
            testAddressBook.addContact(testContactA);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(testContactB));
        }

        @Test
        @DisplayName("AB18: Should add a contact where another contact, removed from contacts, has the same phone number")
        public void AB18() {
            //Arrange
            when(testContactB.getPhoneNumber()).thenReturn(testContactAPhoneNumber);
            //Act
            testAddressBook.addContact(testContactA);
            testAddressBook.deleteContact(testContactA);
            //Assert
            assertTrue(testAddressBook.addContact(testContactB));
        }

        @Test
        @DisplayName("AB19: Should add a contact where another contact, removed from contacts, has the same email address")
        public void AB19() {
            //Arrange
            when(testContactB.getEmailAddress()).thenReturn(testContactAEmail);
            //Act
            testAddressBook.addContact(testContactA);
            testAddressBook.deleteContact(testContactA);
            //Assert
            assertTrue(testAddressBook.addContact(testContactB));
        }


    }

    @DisplayName("Update Contact Checks")
    @Nested
    class UpdateContactTest{
        private IImmutableContact originalContact;
        private IImmutableContact newContact;
        private final String NEW_CONTACT_PHONE_NUMBER = "00000000";
        private final String NEW_CONTACT_EMAIL_ADDRESS = "new@b.c";

        @BeforeEach
        public void setUpForDuplicateChecks(){
            originalContact = mock(IImmutableContact.class);
            newContact = mock(IImmutableContact.class);
            when(originalContact.getPhoneNumber()).thenReturn("11111111111");
            when(originalContact.getEmailAddress()).thenReturn("old@email.com");
            when(newContact.getPhoneNumber()).thenReturn(NEW_CONTACT_PHONE_NUMBER);
            when(newContact.getEmailAddress()).thenReturn(NEW_CONTACT_EMAIL_ADDRESS);
        }

        @Test
        @DisplayName("AB20-21: Standard case, updating a contact")
        public void AB20_AB21() {
            //Act
            testAddressBook.addContact(originalContact);
            IImmutableContact actual = testAddressBook.replaceContact(originalContact,newContact);
            //Assert
            assertAll(
                    () -> assertEquals(newContact, testAddressBook.getContacts().get(0)),
                    () -> assertEquals(newContact, actual)
            );
        }
        @Test
        @DisplayName("AB22: Updating a contact with a duplicate phone number")
        public void AB22() {
            //Arrange
            IImmutableContact contactWithDuplicatePhoneNumber = mock(IImmutableContact.class);
            when(contactWithDuplicatePhoneNumber.getPhoneNumber()).thenReturn(NEW_CONTACT_PHONE_NUMBER);
            //Act
            testAddressBook.addContact(contactWithDuplicatePhoneNumber);
            testAddressBook.addContact(originalContact);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.replaceContact(originalContact,newContact));
        }

        @Test
        @DisplayName("AB23: Should throw exception where another contact has the same emailAddress")
        public void AB23() {
            //Arrange
            IImmutableContact contactWithDuplicateEmailAddress = mock(IImmutableContact.class);
            when(contactWithDuplicateEmailAddress.getEmailAddress()).thenReturn(NEW_CONTACT_EMAIL_ADDRESS);
            //Act
            testAddressBook.addContact(contactWithDuplicateEmailAddress);
            testAddressBook.addContact(originalContact);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.replaceContact(originalContact,newContact));
        }

        @Test
        @DisplayName("AB24: Should add a contact where another contact, added with the same phone number, is updated with a new number")
        public void AB24() {
            //Arrange
            IImmutableContact originalContactWithDuplicatePhoneNumber = mock(IImmutableContact.class);
            when(originalContactWithDuplicatePhoneNumber.getPhoneNumber()).thenReturn(NEW_CONTACT_PHONE_NUMBER);
            IImmutableContact newContactWithDuplicatePhoneNumberReplaced = mock(IImmutableContact.class);
            when(newContactWithDuplicatePhoneNumberReplaced.getPhoneNumber()).thenReturn("02010");
            //Act
            testAddressBook.addContact(originalContactWithDuplicatePhoneNumber);
            testAddressBook.replaceContact(originalContactWithDuplicatePhoneNumber, newContactWithDuplicatePhoneNumberReplaced);
            testAddressBook.addContact(originalContact);
            //Assert
            assertEquals(newContact, testAddressBook.replaceContact(originalContact,newContact));
        }

        @Test
        @DisplayName("AB25: Should add a contact where another contact, added with the same email address, is updated with a new email address")
        public void AB25() {
            //Arrange
            IImmutableContact originalContactWithDuplicateEmailAddress = mock(IImmutableContact.class);
            when(originalContactWithDuplicateEmailAddress.getPhoneNumber()).thenReturn(NEW_CONTACT_EMAIL_ADDRESS);
            IImmutableContact newContactWithDuplicateEmailAddressReplaced = mock(IImmutableContact.class);
            when(newContactWithDuplicateEmailAddressReplaced.getPhoneNumber()).thenReturn("02010");
            //Act
            testAddressBook.addContact(originalContactWithDuplicateEmailAddress);
            testAddressBook.replaceContact(originalContactWithDuplicateEmailAddress, newContactWithDuplicateEmailAddressReplaced);
            testAddressBook.addContact(originalContact);
            //Assert
            assertEquals(newContact, testAddressBook.replaceContact(originalContact,newContact));
        }

        @Test
        @DisplayName("AB26: Should return null if contact to replace not found")
        public void AB26() {
            //Arrange
            IImmutableContact contactNotInContacts = mock(IImmutableContact.class);
            when(contactNotInContacts.getPhoneNumber()).thenReturn("NOT IN CONTACTS");

            //Act
            testAddressBook.addContact(originalContact);
            IImmutableContact actual = testAddressBook.replaceContact(contactNotInContacts, newContact);
            //Assert
            assertNull(actual);
        }

        @Test
        @DisplayName("AB27: Should include contact in contacts if error thrown")
        public void AB27() {
            //Arrange
            IImmutableContact contactWithDuplicateEmailAddress = mock(IImmutableContact.class);
            when(contactWithDuplicateEmailAddress.getEmailAddress()).thenReturn(NEW_CONTACT_EMAIL_ADDRESS);
            boolean actual;
            //Act
            testAddressBook.addContact(contactWithDuplicateEmailAddress);
            testAddressBook.addContact(originalContact);
            try{
                testAddressBook.replaceContact(originalContact,newContact);
            }catch(Exception ex){
            }
            finally {
                actual = testAddressBook.getContacts().contains(originalContact);
            }
            //Assert
            assertTrue(actual);
        }
    }

    @DisplayName("View AllContactsTests")
    @Nested
    class ViewAllContactsTests{

        @Test
        @DisplayName("AB28: Should return collection with one element where one contact")
        public void AB28() {
            //Act
            testAddressBook.addContact(testContacts[0]);
            //Assert
            assertEquals(1, testAddressBook.getContacts().size());
        }

        @Test
        @DisplayName("AB29: Should return collection with all contacts where more than one contact")
        public void AB29() {
            //Act
            testAddressBook.addContact(testContacts[0]);
            testAddressBook.addContact(testContacts[1]);
            //Assert
            assertEquals(2, testAddressBook.getContacts().size());
        }


    }
}
