package com.digitalfuturesacademy.addressbook.controller;

import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddressBookAppTest {


    private AddressBookApp testAddressBookApp;
    private IUserInterface mockUserInterface;
    private IAddressBook mockAddressBook;
    private ArgumentCaptor<String> stringArgumentCaptor;
    private final String EXPECTED_TOP_LEVEL_MENU_STRING = "1:\tAdd a contact\n2:\tView all contacts\n3:\tSearch contacts\n";

    @BeforeEach
    public void setUpAddressBookAppTests(){
        mockUserInterface = mock(IUserInterface.class);
        mockAddressBook = mock(IAddressBook.class);
        testAddressBookApp = new AddressBookApp(mockUserInterface, mockAddressBook);
        stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
    }

    @AfterEach
    public void cleanUpAddressBookAppTests(){
        mockUserInterface = null;
        mockAddressBook = null;
        testAddressBookApp = null;
        stringArgumentCaptor = null;
    }

    @DisplayName("Top-Level Menu Tests")
    @Nested
    class TopLevelMenuTests{


        @Test
        @DisplayName("ABA1: Should call printMessage with top-level menu options preceded by 1-based index and separated by newlines")
        public void AB1() {
            // Arrange
            String actual;
            //Act
            testAddressBookApp.run();
            verify(mockUserInterface).printMessage(stringArgumentCaptor.capture());
            actual = stringArgumentCaptor.getValue();
            //Assert
            assertEquals(EXPECTED_TOP_LEVEL_MENU_STRING, actual);
        }

    }



}
