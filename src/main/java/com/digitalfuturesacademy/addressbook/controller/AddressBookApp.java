package com.digitalfuturesacademy.addressbook.controller;

import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.ImmutableContact;
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
        topLevelMenuControl();
    }

    private void topLevelMenuControl(){

        String userSelection = null;
        while(true){
            printAddressBookMenu();
            userSelection = getUserSelectionFrom(addressBookMenu);
            if(userSelection == null || userSelection.equals("e")) break;
            switch (userSelection){
                case "1":
                    createUserControl();
                    break;
            }

        }
    }

    private void printAddressBookMenu(){
        StringBuilder addressBookMenuStringBuilder = new StringBuilder();
        for(Map.Entry entry : addressBookMenu.entrySet()){
            addressBookMenuStringBuilder.append(entry.getKey())
                    .append(":\t")
                    .append(entry.getValue())
                    .append("\n");
        }
        userInterface.printMessage(addressBookMenuStringBuilder.toString());
    }

    private String getUserSelectionFrom(SortedMap<String, String> menu){
        String userInput = null;
        while(userInput == null || !menu.containsKey(userInput)){
            userInput = userInterface.getUserInput("Select an option by number or 'e' to exit:");
            if(userInput != null && userInput.toLowerCase().equals("e")) break;
            if(!menu.containsKey(userInput))  userInterface.printErrorMessage("Invalid selection!");
        }
        return userInput;
    }

    private void createUserControl(){
        String nameInput = userInterface.getUserInput("Enter the contact's name:");
        String phoneNumberInput = userInterface.getUserInput("Enter the contact's phone number:");
        String emailAddressInput = userInterface.getUserInput("Enter the contact's email address:");
        addressBook.addContact(new ImmutableContact(nameInput, phoneNumberInput,emailAddressInput));
        userInterface.printSuccessMessage("Success: Contact added to address book");
    }

}
