package com.digitalfuturesacademy.addressbook.controller;

import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AddressBookApp {

    private IUserInterface userInterface;
    private IAddressBook addressBook;
    private SortedMap<String, String> addressBookMenu = new TreeMap<>();
    {
        addressBookMenu.put("1", "Add a contact");
        addressBookMenu.put("2", "View all contacts");
        addressBookMenu.put("3", "Search contacts");
    }

    public AddressBookApp(IUserInterface userInterface, IAddressBook addressBook){
        if(userInterface == null || addressBook == null)
            throw new IllegalArgumentException("User interface and address book cannot be null");
        this.userInterface = userInterface;
        this.addressBook = addressBook;
    }

    public void run(){
        printAddressBookMenu();
        getUserSelectionFrom(addressBookMenu);
    }

    private void printAddressBookMenu(){
        StringBuilder addressBookMenuStringBuilder = new StringBuilder();
        for(Map.Entry entry : addressBookMenu.entrySet()){
            addressBookMenuStringBuilder.append(entry.getKey());
            addressBookMenuStringBuilder.append(":\t");
            addressBookMenuStringBuilder.append(entry.getValue());
            addressBookMenuStringBuilder.append("\n");
        }
        userInterface.printMessage(addressBookMenuStringBuilder.toString());
    }

    private String getUserSelectionFrom(SortedMap<String, String> menu){
        String userInput = userInterface.getUserInput("Select an option by number or 'e' to exit \n");
        return userInput;
    }

}
