package com.digitalfuturesacademy.addressbook.model;

import com.digitalfuturesacademy.addressbook.testdata.GeneralTestData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

public class ImmutableContactTest {
    @ExtendWith(MockitoExtension.class)
    private final GeneralTestData td = new GeneralTestData();


    @DisplayName("Constructor Tests")
    @Nested
    class CreateMethodTests{

        @Test
        @DisplayName("IC1, IC5, IC9: Test that constructor correctly assigns parameters")
        public void IC1_IC5_IC9() {
            IImmutableContact testContact = new ImmutableContact(td.VALID_NAME, td.VALID_PHONE_NUMBER, td.VALID_EMAIL_ADDRESS);
            //Assert
            assertAll(
                    () -> assertEquals(td.VALID_NAME, testContact.getName()), //IC1
                    () -> assertEquals(td.VALID_PHONE_NUMBER, testContact.getPhoneNumber()), //IC5
                    () -> assertEquals(td.VALID_EMAIL_ADDRESS, testContact.getEmailAddress()) //IC9
            );
        }

        @Test
        @DisplayName("IC2, IC8, IC12: Test that constructor trims parameters")
        public void IC2_IC8_IC12() {
            IImmutableContact testContact = new ImmutableContact(td.VALID_NAME.concat(" "), td.VALID_PHONE_NUMBER.concat(" "), td.VALID_EMAIL_ADDRESS.concat(" "));
            //Assert
            assertAll(
                    () -> assertEquals(td.VALID_NAME, testContact.getName()), //IC2
                    () -> assertEquals(td.VALID_PHONE_NUMBER, testContact.getPhoneNumber()), //IC8
                    () -> assertEquals(td.VALID_EMAIL_ADDRESS, testContact.getEmailAddress()) //IC12
            );
        }

        @Test
        @DisplayName("IC3, IC6, IC10: Test that constructor throws error for null String arguments")
        public void IC3_IC6_IC10() {
            //Assert
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(null, td.VALID_PHONE_NUMBER, td.VALID_EMAIL_ADDRESS)), //IC3
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(td.VALID_NAME, null, td.VALID_EMAIL_ADDRESS)), //IC6
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(td.VALID_NAME, td.VALID_PHONE_NUMBER, null)) //IC10
            );
        }

        @Test
        @DisplayName("IC4, IC7, IC11: Test that constructor throws error for empty String arguments")
        public void IC4_IC7_IC11() {
            //Assert
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact("  ", td.VALID_PHONE_NUMBER, td.VALID_EMAIL_ADDRESS)), //IC4
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(td.VALID_NAME, "  ", td.VALID_EMAIL_ADDRESS)), //IC7
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(td.VALID_NAME, td.VALID_PHONE_NUMBER, " ")) //IC11
            );
        }

        @Test
        @DisplayName("IC13-IC14: Test that constructor performs pattern validation on phone number and email")
        public void IC13_IC14() {
            //Assert
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(td.VALID_NAME, td.PHONE_NUMBER_WITH_INVALID_FIRST_CHAR, td.VALID_EMAIL_ADDRESS)), //IC13
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(td.VALID_NAME, td.VALID_PHONE_NUMBER, td.EMAIL_ADDRESS_WITHOUT_DOMAIN)) //IC14
            );
        }
    }

    @DisplayName("With Field Tests")
    @Nested
    class WithFieldTests{
        private IImmutableContact originalContact;
        @BeforeEach
        public void setUpWithFieldTests(){
            originalContact = new ImmutableContact(td.CONTACT_1_NAME, td.CONTACT_1_PHONE_NUMBER, td.CONTACT_1_EMAIL_ADDRESS);
        }

        @AfterEach
        public void cleanUpWithFieldTests(){
            originalContact = null;
        }

        @Test
        @DisplayName("IC15, IC16, IC17: Test with name")
        public void IC15_IC16_IC17() {
            //Act
            IImmutableContact newTestContact = originalContact.withName(td.CONTACT_2_NAME);
            //Assert
            assertAll(
                    () -> assertEquals(td.CONTACT_2_NAME, newTestContact.getName()), //IC15
                    ()-> assertAll( //IC16
                            () -> assertEquals(td.CONTACT_1_PHONE_NUMBER, newTestContact.getPhoneNumber()),
                            () -> assertEquals(td.CONTACT_1_EMAIL_ADDRESS, newTestContact.getEmailAddress())
                    ),
                    () -> assertEquals(td.CONTACT_1_NAME, originalContact.getName()) //IC17
            );
        }

        @Test
        @DisplayName("IC18, IC19, IC20: Test with phone number")
        public void IC18_IC19_IC20() {
            //Act
            IImmutableContact newTestContact = originalContact.withPhoneNumber(td.CONTACT_2_PHONE_NUMBER);
            //Assert
            assertAll(
                    () -> assertEquals(td.CONTACT_2_PHONE_NUMBER, newTestContact.getPhoneNumber()), //IC18
                    ()-> assertAll( //IC19
                            () -> assertEquals(td.CONTACT_1_NAME, newTestContact.getName()),
                            () -> assertEquals(td.CONTACT_1_EMAIL_ADDRESS, newTestContact.getEmailAddress())
                    ),
                    () -> assertEquals(td.CONTACT_1_PHONE_NUMBER, originalContact.getPhoneNumber()) //IC20
            );
        }

        @Test
        @DisplayName("IC21, IC22, IC23: Test with email address")
        public void IC21_IC22_IC23() {
            //Act
            IImmutableContact newTestContact = originalContact.withEmailAddress(td.CONTACT_2_EMAIL_ADDRESS);
            //Assert
            assertAll(
                    () -> assertEquals(td.CONTACT_2_EMAIL_ADDRESS, newTestContact.getEmailAddress()), //IC21
                    ()-> assertAll( //IC22
                            () -> assertEquals(td.CONTACT_1_NAME, newTestContact.getName()),
                            () -> assertEquals(td.CONTACT_1_PHONE_NUMBER, newTestContact.getPhoneNumber())
                    ),
                    () -> assertEquals(td.CONTACT_1_EMAIL_ADDRESS, originalContact.getEmailAddress()) //IC23
            );
        }
    }
}

