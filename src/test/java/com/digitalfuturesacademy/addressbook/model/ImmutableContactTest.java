package com.digitalfuturesacademy.addressbook.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ImmutableContactTest {

    private String testName = "Jane Doe";

    @DisplayName("Constructor Tests")
    @Nested
    class CreateMethodTests{

        @Test
        @DisplayName("IC1, IC5, IC9: Test that constructor correctly assigns parameters")
        public void testThatConstructorCorrectlyAssignsParameters() {
            IImmutableContact testContact = new ImmutableContact(testName);
            //Assert
            assertAll(
                   () -> assertEquals(testName, testContact.getName())
            );
        }

        @Test
        @DisplayName("IC2, IC8, IC12: Test that constructor trims parameters")
        public void testThatConstructorTrimsParameters() {
            IImmutableContact testContact = new ImmutableContact(" " + testName + " ");
            //Assert
            assertAll(
                   () -> assertEquals(testName, testContact.getName())
            );
        }



    }
}
