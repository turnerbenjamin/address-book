package com.digitalfuturesacademy.addressbook.app;

import com.digitalfuturesacademy.addressbook.controller.AddressBookApp;
import com.digitalfuturesacademy.addressbook.model.AddressBook;
import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.model.ImmutableContact;
import com.digitalfuturesacademy.addressbook.view.ConsoleInterface;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;

import java.util.Scanner;

public class App {

    public static void main(String[] args){
        //Set up dependencies
        Scanner scanner = new Scanner(System.in);
        IUserInterface userInterface = new ConsoleInterface(scanner);
        IAddressBook addressBook = new AddressBook();

        //Initialise demo data
        addressBook.addContact(new ImmutableContact("Jane Doe", "11111111111", "jane@test.com"));
        addressBook.addContact(new ImmutableContact("John Doe", "11111111112", "john@test.com"));

        //Run program
        try{
            AddressBookApp addressBookApp = new AddressBookApp(userInterface,addressBook);
            addressBookApp.run();
        }catch(Throwable ex){
            userInterface.printErrorMessage("Unexpected error occurred. Exiting application");
        }

    }
}
