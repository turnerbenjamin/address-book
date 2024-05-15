package com.digitalfuturesacademy.addressbook.app;

import com.digitalfuturesacademy.addressbook.controller.AddressBookApp;
import com.digitalfuturesacademy.addressbook.model.AddressBook;
import com.digitalfuturesacademy.addressbook.model.IAddressBook;
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
        addressBook.addContact(new ImmutableContact("Broderick Nielsen", "+441270138662", "broderick@gmail.com"));
        addressBook.addContact(new ImmutableContact("Annie Carr", "+441700142026", "Annie@aol.co.uk"));
        addressBook.addContact(new ImmutableContact("Jesse Maxwell", "+445548597325", "Jesse@gmail.com"));
        addressBook.addContact(new ImmutableContact("Brandy Santiago", "+441330111865", "Brandy@me.com"));
        addressBook.addContact(new ImmutableContact("Lizzie Mckee", "+441832381003", "Lizzie@me.com"));

        //Run program
        try{
            AddressBookApp addressBookApp = new AddressBookApp(userInterface,addressBook);
            addressBookApp.run();
        }catch(Throwable ex){
            userInterface.printErrorMessage("Unexpected error occurred. Exiting application");
        }

    }
}
