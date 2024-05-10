package com.digitalfuturesacademy.addressbook.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ImmutableContactTest {

    private String testName = "Jane Doe";

    @DisplayName("Create Method Tests")
    @Nested
    class CreateMethodTests{

        @Test
        @DisplayName("IC1, IC5, IC9: Test that create correctly assigns parameters")
        public void testThatCreateCorrectlyAssignsParameters() {
            IImmutableContact testContact = new ImmutableContact().create(testName);
            //Assert
            assertAll(
                   () -> assertEquals(testName, testContact.getName())
            );
        }

        @Test
        @DisplayName("IC2, IC8, IC12: Test that create trims parameters")
        public void testThatCreateTrimsParameters() {
            IImmutableContact testContact = new ImmutableContact().create(" " + testName + " ");
            //Assert
            assertAll(
                   () -> assertEquals(testName, testContact.getName())
            );
        }



    }
}
