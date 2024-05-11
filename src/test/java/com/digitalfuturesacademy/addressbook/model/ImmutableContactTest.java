package com.digitalfuturesacademy.addressbook.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ImmutableContactTest {

    private String testName = "Jane Doe";
    private String testPhoneNumber = "00000000000";

    @DisplayName("Constructor Tests")
    @Nested
    class CreateMethodTests{

        @Test
        @DisplayName("IC1, IC5, IC9: Test that constructor correctly assigns parameters")
        public void testThatConstructorCorrectlyAssignsParameters() {
            IImmutableContact testContact = new ImmutableContact(testName, testPhoneNumber);
            //Assert
            assertAll(
                    () -> assertEquals(testName, testContact.getName()),
                    () -> assertEquals(testPhoneNumber, testContact.getPhoneNumber())
            );
        }

        @Test
        @DisplayName("IC2, IC8, IC12: Test that constructor trims parameters")
        public void testThatConstructorTrimsParameters() {
            IImmutableContact testContact = new ImmutableContact(" " + testName + " ", testPhoneNumber);
            //Assert
            assertAll(
                    () -> assertEquals(testName, testContact.getName())
            );
        }

        @Test
        @DisplayName("IC3, IC6, IC10: Test that constructor throws error for null String arguments")
        public void testThatConstructorThrowsErrorForNullStringArguments() {
            //Assert
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(null, testPhoneNumber)),
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact(testName, null))
            );
        }

        @Test
        @DisplayName("IC4, IC7, IC11: Test that constructor throws error for empty String arguments")
        public void testThatConstructorThrowsErrorForEmptyStringArguments() {
            //Assert
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, ()->new ImmutableContact("  ", testPhoneNumber))
            );
        }



    }
}
