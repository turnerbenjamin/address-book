package com.digitalfuturesacademy.addressbook.app;

import com.digitalfuturesacademy.addressbook.controller.AddressBookApp;
import com.digitalfuturesacademy.addressbook.model.AddressBook;
import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.view.ConsoleInterface;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;

import java.util.Scanner;

public class App {

    public static void main(String[] args){
        //Set up dependencies
        Scanner scanner = new Scanner(System.in);
        IUserInterface userInterface = new ConsoleInterface(scanner);
        IAddressBook addressBook = new AddressBook();

        //Run program
        AddressBookApp addressBookApp = new AddressBookApp(userInterface,addressBook);
        addressBookApp.run();
    }
}
