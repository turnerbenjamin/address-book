package com.digitalfuturesacademy.addressbook.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ImmutableContactTest {

    private String testName = "Jane Doe";
    private String testPhoneNumber = "+0000000000";
    private String testEmailAddress = "a@b.com";

    @DisplayName("Constructor Tests")
    @Nested
    class CreateMethodTests{

        @Test
        @DisplayName("IC1, IC5, IC9: Test that constructor correctly assigns parameters")
        public void testThatConstructorCorrectlyAssignsParameters() {
            IImmutableContact testContact = new ImmutableContact(testName, testPhoneNumber, testEmailAddress);
            //Assert
            assertAll(
                    () -> assertEquals(testName, testContact.getName()),
                    () -> assertEquals(testPhoneNumber, testContact.getPhoneNumber()),
                    () -> assertEquals(testEmailAddress, testContact.getEmailAddress())
            );
        }

        @Test
        @DisplayName("IC2, IC8, IC12: Test that constructor trims parameters")
        public void testThatConstructorTrimsParameters() {
            IImmutableContact testContact = new ImmutableContact(testName.concat(" "), testPhoneNumber.concat(" "), testEmailAddress.concat(" "));
            //Assert
            assertAll(
                    () -> assertEquals(testName, testContact.getName()),
                    () -> assertEquals(testPhoneNumber, testContact.getPhoneNumber()),
                    () -> assertEquals(testEmailAddress, testContact.getEmailAddress())
            );
        }

        @Test
        @DisplayName("IC3, IC6, IC10: Test that constructor throws error for null String arguments")
        public void testThatConstructorThrowsErrorForNullStringArguments() {
            //Assert
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(null, testPhoneNumber, testEmailAddress)),
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(testName, null, testEmailAddress)),
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(testName, testPhoneNumber, null))
            );
        }

        @Test
        @DisplayName("IC4, IC7, IC11: Test that constructor throws error for empty String arguments")
        public void testThatConstructorThrowsErrorForEmptyStringArguments() {
            //Assert
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact("  ", testPhoneNumber, testEmailAddress)),
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(testName, "  ", testEmailAddress)),
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(testName, testPhoneNumber, " "))
            );
        }

        @Test
        @DisplayName("IC13-IC14: Test that constructor performs pattern validation on phone number and email")
        public void testThatConstructorPerformsPatternValidationOnPhoneNumberAndEmail() {
            //Assert
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(testName, "phoneNumber", testEmailAddress)),
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(testName, testPhoneNumber, "A@B,com"))
            );
        }
    }

    @DisplayName("With Field Tests")
    @Nested
    class WithFieldTests{

        private IImmutableContact originalContact;
        private final String OLD_NAME = "Old Name";
        private final String OLD_PHONE_NUMBER = "00000000";
        private final String OLD_EMAIL_ADDRESS = "old@b.c";
        private final String NEW_NAME = "New Name";

        @BeforeEach
        public void setUpWithFieldTests(){
            originalContact = new ImmutableContact(OLD_NAME, OLD_PHONE_NUMBER, OLD_EMAIL_ADDRESS);
        }

        @AfterEach
        public void cleanUpWithFieldTests(){
            originalContact = null;
        }

        @Test
        @DisplayName("IC15, IC16, IC17: Test with name")
        public void testWithName() {
            //Act
            IImmutableContact newTestContact = originalContact.withName(NEW_NAME);
            //Assert
            assertAll(
                    () -> assertEquals(NEW_NAME, newTestContact.getName()),
                    () -> assertEquals(OLD_PHONE_NUMBER, newTestContact.getPhoneNumber()),
                    () -> assertEquals(OLD_EMAIL_ADDRESS, newTestContact.getEmailAddress()),
                    () -> assertEquals(OLD_NAME, originalContact.getName())
            );
        }


    }
}
