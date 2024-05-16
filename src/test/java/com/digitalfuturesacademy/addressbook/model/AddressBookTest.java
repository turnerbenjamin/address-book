package com.digitalfuturesacademy.addressbook.model;

import com.digitalfuturesacademy.addressbook.testdata.GeneralTestData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookTest {

    @ExtendWith(MockitoExtension.class)

    private AddressBook testAddressBook;
    private List<IImmutableContact> testContacts;
    private IImmutableContact testContact1;
    private IImmutableContact testContact2;
    private final GeneralTestData td = new GeneralTestData();

    @BeforeEach
    public void setUpForAllTests(){
        testContact1 = mock(IImmutableContact.class);
        testContact2 = mock(IImmutableContact.class);
        testAddressBook = new AddressBook();
        testContacts = Arrays.asList(testContact1,testContact2);
    }

    @AfterEach
    public void cleanUpForAllTests(){
        testAddressBook = null;
        testContacts = null;
        testContact1 = null;
        testContact2 = null;
    }

    @DisplayName("Test Add Entry")
    @Nested
    class AddContactTests{
        @Test
        @DisplayName("AB1-AB3: Test adding a valid contact")
        public void AB1_AB2_AB3() {
            // Arrange
            IImmutableContact testContact = testContact1;
            int startingAddressBookSize = testAddressBook.size();
            //Act
            boolean result = testAddressBook.addContact(testContact);
            //Assert
            assertAll(
                    () -> assertEquals(startingAddressBookSize + 1 , testAddressBook.size()),// AB1
                    () -> assertTrue(testAddressBook.getContacts().contains(testContact)), //AB2
                    () -> assertTrue(result) //AB3
            );
        }
        @Test
        @DisplayName("AB4: Should throw exception when a contact with a value of null is added")
        public void AB4() {
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(null));
        }
    }

    @DisplayName("Test Search Contacts")
    @Nested
    class SearchContactTests{

        @BeforeEach
        public void setUpForSearchContactTests(){
            when(testContact1.getName()).thenReturn(td.CONTACT_1_NAME);
            when(testContact2.getName()).thenReturn(td.CONTACT_2_NAME);
            when(testContact1.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(testContact2.getPhoneNumber()).thenReturn(td.CONTACT_2_PHONE_NUMBER);
            when(testContact1.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            when(testContact2.getEmailAddress()).thenReturn(td.CONTACT_2_EMAIL_ADDRESS);
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContact2);
        }

        @Test
        @DisplayName("AB5-9 and AB32-41: Search Results Tests")
        public void AB5_AB6_AB7_AB8_AB9_AB32_AB33_AB34_AB35_AB36_AB37_AB38_AB39_AB40_AB41() {
            //Assert
            assertAll(
                    () -> assertEquals(1,testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_NAME).size()), //AB5
                    () -> assertTrue(testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_NAME).contains(testContact1)), //AB6
                    ()->assertEquals(0,testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_ZERO_CONTACTS_BY_NAME).size()), //AB7
                    () -> assertEquals(2,testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_NAME).size()), //AB8
                    () -> assertTrue(testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_NAME).containsAll(testContacts)), //AB9

                    () -> assertEquals(1,testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_PHONE_NUMBER).size()), //AB32
                    () -> assertTrue(testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_PHONE_NUMBER).contains(testContact1)), //AB33
                    () -> assertEquals(0, testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_ZERO_CONTACTS_BY_PHONE_NUMBER).size()), //AB34
                    () -> assertEquals(2, testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_PHONE_NUMBER).size()), //AB35
                    () -> assertTrue(testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_PHONE_NUMBER).containsAll(testContacts)), //AB36

                    () -> assertEquals(1,testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_EMAIL_ADDRESS).size()), //AB37
                    () -> assertTrue(testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_EMAIL_ADDRESS).contains(testContact1)), //AB38
                    () -> assertEquals(0, testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_ZERO_CONTACTS_BY_EMAIL_ADDRESS).size()), //AB39
                    () -> assertEquals(2, testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_EMAIL_ADDRESS).size()), //AB40
                    () -> assertTrue(testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_EMAIL_ADDRESS).containsAll(testContacts)) //AB41
            );
        }

        @Test
        @DisplayName("AB10-11: Test search trimmed and case-insensitive")
        public void AB10_AB11() {
            //Assert
            assertAll(
                    () -> assertTrue(testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_NAME.toUpperCase()).contains(testContact1)), //AB10
                    () -> assertTrue(testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_NAME.concat("  ")).contains(testContact1)) //AB11
            );
        }
    }


    @DisplayName("Test Delete Contact")
    @Nested
    class DeleteContactTests{
        @BeforeEach
        public void setUpForDeleteContactTests(){
            when(testContact1.getName()).thenReturn(td.CONTACT_1_NAME);
            when(testContact2.getName()).thenReturn(td.CONTACT_2_NAME);
            when(testContact1.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(testContact2.getPhoneNumber()).thenReturn(td.CONTACT_2_PHONE_NUMBER);
            when(testContact1.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            when(testContact2.getEmailAddress()).thenReturn(td.CONTACT_2_EMAIL_ADDRESS);
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContact2);
        }

        @Test
        @DisplayName("AB12-14: Test deleting a contact in contacts")
        public void AB12_AB13_AB14() {
            //Arrange
            int startingContactsSize = testAddressBook.size();
            IImmutableContact testContactToDelete = testContact1;
            IImmutableContact actual;
            //Act
            actual = testAddressBook.deleteContact(testContactToDelete);
            //Assert
            assertAll(
                    () -> assertEquals(startingContactsSize - 1,testAddressBook.size()), //AB12
                    () -> assertFalse(testAddressBook.getContacts().contains(testContactToDelete)), //AB13
                    () -> assertEquals(testContactToDelete,actual) //AB14
            );
        }

        @Test
        @DisplayName("AB15: Test deleting a contact not in contacts")
        public void AB15() {
            //Arrange
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

        @BeforeEach
        public void setUpForDuplicateChecks(){
            when(testContact1.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(testContact1.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            testAddressBook.addContact(testContact1);
        }

        @Test
        @DisplayName("AB16: Test throws error when adding contact with a duplicate phone number.")
        public void AB16() {
            //Arrange
            when(testContact2.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(testContact2));
        }

        @Test
        @DisplayName("AB17: Test throws error when adding contact with a duplicate email address.")
        public void AB17() {
            //Arrange
            when(testContact2.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.addContact(testContact2));
        }

        @Test
        @DisplayName("AB18: Should add a contact where another contact, removed from contacts, has the same phone number")
        public void AB18() {
            //Arrange
            when(testContact2.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            //Act
            testAddressBook.deleteContact(testContact1);
            //Assert
            assertTrue(testAddressBook.addContact(testContact2));
        }

        @Test
        @DisplayName("AB19: Should add a contact where another contact, removed from contacts, has the same email address")
        public void AB19() {
            //Arrange
            when(testContact2.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            //Act
            testAddressBook.deleteContact(testContact1);
            //Assert
            assertTrue(testAddressBook.addContact(testContact2));
        }


    }

    @DisplayName("Update Contact Checks")
    @Nested
    class UpdateContactTest{

        @BeforeEach
        public void setUpForUpdateContactChecks(){
            when(testContact1.getName()).thenReturn(td.CONTACT_1_NAME);
            when(testContact2.getName()).thenReturn(td.CONTACT_2_NAME);
            when(testContact1.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(testContact2.getPhoneNumber()).thenReturn(td.CONTACT_2_PHONE_NUMBER);
            when(testContact1.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            when(testContact2.getEmailAddress()).thenReturn(td.CONTACT_2_EMAIL_ADDRESS);
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContact2);
        }

        @Test
        @DisplayName("AB20-21: Standard case, updating a contact")
        public void AB20_AB21() {
            //Act
            IImmutableContact updatedContact = mock(IImmutableContact.class);
            when(updatedContact.getName()).thenReturn(td.VALID_NAME);
            when(updatedContact.getPhoneNumber()).thenReturn(td.VALID_PHONE_NUMBER);
            when(updatedContact.getEmailAddress()).thenReturn(td.VALID_EMAIL_ADDRESS);

            IImmutableContact actual = testAddressBook.replaceContact(testContact1,updatedContact);
            //Assert
            assertAll(
                    () -> assertAll( //AB20
                            () -> assertTrue(testAddressBook.getContacts().contains(updatedContact)),
                            () -> assertFalse(testAddressBook.getContacts().contains(testContact1))
                    ),

                    () -> assertEquals(updatedContact, actual) //AB21
            );
        }
        @Test
        @DisplayName("AB22: Updating a contact with a duplicate phone number")
        public void AB22() {
            //Arrange
            IImmutableContact updatedContact = mock(IImmutableContact.class);
            when(updatedContact.getPhoneNumber()).thenReturn(td.CONTACT_2_PHONE_NUMBER);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.replaceContact(testContact1,updatedContact));
        }

        @Test
        @DisplayName("AB23: Should throw exception where another contact has the same Email Address")
        public void AB23() {
            //Arrange
            IImmutableContact updatedContact = mock(IImmutableContact.class);
            when(updatedContact.getEmailAddress()).thenReturn(td.CONTACT_2_EMAIL_ADDRESS);
            //Assert
            assertThrows(IllegalArgumentException.class, ()->testAddressBook.replaceContact(testContact1,updatedContact));
        }

        @Test
        @DisplayName("AB24: Should add a contact where another contact, added with the same phone number, is updated with a new number")
        public void AB24() {
            //Arrange
            IImmutableContact updatedContact = mock(IImmutableContact.class);
            when(updatedContact.getName()).thenReturn(td.CONTACT_1_NAME);
            when(updatedContact.getPhoneNumber()).thenReturn(td.VALID_PHONE_NUMBER);
            when(updatedContact.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);

            IImmutableContact newContact = mock(IImmutableContact.class);
            when(newContact.getName()).thenReturn(td.CONTACT_1_NAME);
            when(newContact.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(newContact.getEmailAddress()).thenReturn(td.VALID_EMAIL_ADDRESS);

            //Act
            testAddressBook.replaceContact(testContact1, updatedContact);
            //Assert
            assertDoesNotThrow(()->testAddressBook.addContact(newContact));
        }

        @Test
        @DisplayName("AB25: Should add a contact where another contact, added with the same email address, is updated with a new email address")
        public void AB25() {
            //Arrange
            IImmutableContact updatedContact = mock(IImmutableContact.class);
            when(updatedContact.getName()).thenReturn(td.CONTACT_1_NAME);
            when(updatedContact.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(updatedContact.getEmailAddress()).thenReturn(td.VALID_EMAIL_ADDRESS);

            IImmutableContact newContact = mock(IImmutableContact.class);
            when(newContact.getName()).thenReturn(td.VALID_NAME);
            when(newContact.getPhoneNumber()).thenReturn(td.VALID_PHONE_NUMBER);
            when(newContact.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);

            //Act
            testAddressBook.replaceContact(testContact1, updatedContact);
            //Assert
            assertDoesNotThrow(()->testAddressBook.addContact(newContact));
        }

        @Test
        @DisplayName("AB26: Should return null if contact to replace not found")
        public void AB26() {
            //Arrange
            IImmutableContact contactNotInContacts = mock(IImmutableContact.class);
            //when(contactNotInContacts.getPhoneNumber()).thenReturn("NOT IN CONTACTS");
            //Act
            IImmutableContact actual = testAddressBook.replaceContact(contactNotInContacts, testContact1);
            //Assert
            assertNull(actual);
        }

        @Test
        @DisplayName("AB27: Should include contact in contacts if error thrown")
        public void AB27() {
            //Arrange
            IImmutableContact updatedContact = mock(IImmutableContact.class);
            when(updatedContact.getPhoneNumber()).thenReturn(td.CONTACT_2_PHONE_NUMBER);
            boolean actual;
            try{
                testAddressBook.replaceContact(testContact1,updatedContact);
            }catch(Exception ignored){
            }
            finally {
                actual = testAddressBook.getContacts().contains(testContact1);
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
            testAddressBook.addContact(testContact1);
            //Assert
            assertEquals(1, testAddressBook.getContacts().size());
        }

        @Test
        @DisplayName("AB29: Should return collection with all contacts where more than one contact")
        public void AB29() {
            //Arrange
            when(testContact1.getName()).thenReturn(td.CONTACT_1_NAME);
            when(testContact2.getName()).thenReturn(td.CONTACT_2_NAME);
            when(testContact2.getPhoneNumber()).thenReturn(td.CONTACT_2_PHONE_NUMBER);
            when(testContact2.getEmailAddress()).thenReturn(td.CONTACT_2_EMAIL_ADDRESS);
            //Act
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContact2);

            //Assert
            assertEquals(2, testAddressBook.getContacts().size());
        }

        @Test
        @DisplayName("AB30: Should return empty collection where no contacts")
        public void AB30() {
            assertEquals(0, testAddressBook.getContacts().size());
        }

        @Test
        @DisplayName("AB31: Adding an element to the returned collection should not add an element to the contacts in Address Book")
        public void AB31() {
            //Act
            testAddressBook.addContact(testContact1);
            List<IImmutableContact> results = testAddressBook.getContacts();
            results.add(testContact1);
            //Assert
            assertNotEquals(testAddressBook.size(), results.size());
        }
    }

    @DisplayName("View AllContactsTests")
    @Nested
    class SortingResults {
        private IImmutableContact testContactAlphabeticallyBetweenContacts1and2;

        @BeforeEach
        public void setUp(){
            testContactAlphabeticallyBetweenContacts1and2 = mock(IImmutableContact.class);
            when(testContactAlphabeticallyBetweenContacts1and2.getName()).thenReturn(td.CONTACT_ALPHABETICALLY_BETWEEN_CONTACT_1_AND_2_NAME);
            when(testContactAlphabeticallyBetweenContacts1and2.getPhoneNumber()).thenReturn(td.VALID_PHONE_NUMBER);
            when(testContactAlphabeticallyBetweenContacts1and2.getEmailAddress()).thenReturn(td.VALID_EMAIL_ADDRESS);
            when(testContact1.getName()).thenReturn(td.CONTACT_1_NAME);
            when(testContact1.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(testContact1.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            when(testContact2.getName()).thenReturn(td.CONTACT_2_NAME);
            when(testContact2.getPhoneNumber()).thenReturn(td.CONTACT_2_PHONE_NUMBER);
            when(testContact2.getEmailAddress()).thenReturn(td.CONTACT_2_EMAIL_ADDRESS);
        }

        @AfterEach
        public void cleanUp(){
            testContactAlphabeticallyBetweenContacts1and2 = null;
        }

        @Test
        @DisplayName("AB42: Results of search should be in alphabetical order")
        public void AB42() {
            //Act
            testAddressBook.addContact(testContact2);
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContactAlphabeticallyBetweenContacts1and2);
            List<IImmutableContact> results = testAddressBook.searchContacts("");
            //Assert
            assertAll(
                    () -> assertEquals(testContact1, results.get(0)),
                    () -> assertEquals(testContactAlphabeticallyBetweenContacts1and2, results.get(1)),
                    () -> assertEquals(testContact2, results.get(2))
            );
        }

        @Test
        @DisplayName("AB46: Results of search remain in alphabetical order after a contact is updated")
        public void AB46(){
            //Arrange
            testAddressBook.addContact(testContact2);
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContactAlphabeticallyBetweenContacts1and2);
            when(testContact2.getName()).thenReturn("a");
            when(testContact1.getName()).thenReturn("b");
            when(testContactAlphabeticallyBetweenContacts1and2.getName()).thenReturn("c");
            //Act
            testAddressBook.replaceContact(testContact2,testContact2);
            testAddressBook.replaceContact(testContact1,testContact1);
                        testAddressBook.replaceContact(testContactAlphabeticallyBetweenContacts1and2,testContactAlphabeticallyBetweenContacts1and2);
             List<IImmutableContact> results = testAddressBook.searchContacts("");
            //Assert
            assertAll(
                    () -> assertEquals(testContact2, results.get(0)),
                    () -> assertEquals(testContact1, results.get(1)),
                    () -> assertEquals(testContactAlphabeticallyBetweenContacts1and2, results.get(2))
            );

        }

        @Test
        @DisplayName("AB47: Results of search remain in alphabetical order after a contact is deleted")
        public void AB47(){
            //Arrange
            testAddressBook.addContact(testContact2);
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContactAlphabeticallyBetweenContacts1and2);
            //Act
            testAddressBook.deleteContact(testContact1);
             List<IImmutableContact> results = testAddressBook.searchContacts("");
            //Assert
            assertAll(
                    () -> assertEquals(testContactAlphabeticallyBetweenContacts1and2, results.get(0)),
                    () -> assertEquals(testContact2, results.get(1))
            );
        }

        @Test
        @DisplayName("AB48: Search term should be trimmed before searching")
        public void AB48(){
            //Arrange
            testAddressBook.addContact(testContact2);
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContactAlphabeticallyBetweenContacts1and2);
            //Act
             List<IImmutableContact> results = testAddressBook.searchContacts("  " + td.SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_NAME + "  ");
            //Assert
            assertAll(
                    () -> assertEquals(testContact1, results.get(0)),
                    () -> assertEquals(testContact2, results.get(1))
            );
        }

        @Test
        @DisplayName("AB49: Search should be case-insensitive")
        public void AB49(){
            //Arrange
            testAddressBook.addContact(testContact2);
            testAddressBook.addContact(testContact1);
            testAddressBook.addContact(testContactAlphabeticallyBetweenContacts1and2);
            //Act
             List<IImmutableContact> results = testAddressBook.searchContacts(td.SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_NAME.toUpperCase());
            //Assert
            assertAll(
                    () -> assertEquals(testContact1, results.get(0)),
                    () -> assertEquals(testContact2, results.get(1))
            );
        }
    }

    @DisplayName("Delete All Contacts Tests")
    @Nested
    class DeleteAllContactsTests{

        @BeforeEach
        public void setUpForDeleteAllContactsTests(){
            when(testContact1.getName()).thenReturn(td.CONTACT_1_NAME);
            when(testContact2.getName()).thenReturn(td.CONTACT_2_NAME);
            when(testContact1.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(testContact2.getPhoneNumber()).thenReturn(td.CONTACT_2_PHONE_NUMBER);
            when(testContact1.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            when(testContact2.getEmailAddress()).thenReturn(td.CONTACT_2_EMAIL_ADDRESS);
           testAddressBook.addContact(testContact1);
           testAddressBook.addContact(testContact2);
        }

        @Test
        @DisplayName("AB43: Contacts should be an empty list after deletion")
        public void AB43() {
            //Act
            testAddressBook.deleteAllContacts();
            //Assert
            assertEquals(0, testAddressBook.getContacts().size());
        }

        @Test
        @DisplayName("AB44: Should add contact where phone number is the same as a contact removed by delete all")
        public void AB44() {
            //Arrange
            IImmutableContact newContact = mock(IImmutableContact.class);
            when(newContact.getPhoneNumber()).thenReturn(td.CONTACT_1_PHONE_NUMBER);
            when(newContact.getEmailAddress()).thenReturn(td.VALID_EMAIL_ADDRESS);
            //Act
            testAddressBook.deleteAllContacts();
            //Assert
            assertDoesNotThrow(()->testAddressBook.addContact(newContact));
        }

        @Test
        @DisplayName("AB45: Should add contact where email address is the same as a contact removed by delete all")
        public void AB45() {
            //Arrange
            IImmutableContact newContact = mock(IImmutableContact.class);
            when(newContact.getPhoneNumber()).thenReturn(td.VALID_EMAIL_ADDRESS);
            when(newContact.getEmailAddress()).thenReturn(td.CONTACT_1_EMAIL_ADDRESS);
            //Act
            testAddressBook.deleteAllContacts();
            //Assert
            assertDoesNotThrow(()->testAddressBook.addContact(newContact));
        }
    }
}
