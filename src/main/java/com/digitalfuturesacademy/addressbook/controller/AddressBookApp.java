package com.digitalfuturesacademy.addressbook.controller;

import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.model.ImmutableContact;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AddressBookApp {

    private IUserInterface userInterface;
    private IAddressBook addressBook;
    private SortedMap<String, String> addressBookMenu = new TreeMap<>();
    private SortedMap<String, String> contactMenu = new TreeMap<>();
    {
        addressBookMenu.put("1", "Add a contact");
        addressBookMenu.put("2", "View all contacts");
        addressBookMenu.put("3", "Search contacts");

        contactMenu.put("1", "Update Contact");
        contactMenu.put("2", "Delete Contact");
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
        String userSelection;
        while(true){
            printMenu(addressBookMenu);
            userSelection = getUserSelectionFrom(addressBookMenu);
            if(userSelection.equals("e")) break;
            if(userSelection.equals("1")) createUserControl();
            if(userSelection.equals("2")) readAllContactsControl();
        }
    }

    private void createUserControl(){
        String nameInput = userInterface.getUserInput("Enter the contact's name:");
        String phoneNumberInput = userInterface.getUserInput("Enter the contact's phone number:");
        String emailAddressInput = userInterface.getUserInput("Enter the contact's email address:");
        try{
            addressBook.addContact(new ImmutableContact(nameInput, phoneNumberInput,emailAddressInput));
            userInterface.printSuccessMessage("Success: Contact added to address book");
        }catch(IllegalArgumentException ex){
            userInterface.printErrorMessage(ex.getMessage());
        }
    }

    private void readAllContactsControl(){
        List<IImmutableContact> contacts = addressBook.getContacts();
        if(contacts.isEmpty()){
            userInterface.printErrorMessage("No contacts found!");
            return;
        }
        SortedMap<String,String> contactsMenu = getContactsMenu(addressBook.getContacts());
        printMenu(contactsMenu);
        String userSelection = getUserSelectionFrom(contactsMenu);
        if(userSelection == "e") return;
        readContactControl(contacts.get(Integer.valueOf(userSelection) - 1));
    }



    private void readContactControl(IImmutableContact contactToRead){
        printContact(contactToRead);
        printMenu(contactMenu);
        String userSelection = getUserSelectionFrom(contactMenu);
        if(userSelection.equals("e")) return;
        if(userSelection.equals("1")) updateContactControl(contactToRead);
        if(userSelection.equals("2")) deleteContactControl(contactToRead);

    }

    private void updateContactControl(IImmutableContact contactToUpdate){
        String updatedNameInput = userInterface.getUserInput("Enter new name, or press enter to keep current name:");
        String updatedPhoneNumber = userInterface.getUserInput("Enter new phone number, or press enter to keep current phone number:");
        String updatedEmailAddress = userInterface.getUserInput("Enter new email address, or press enter to keep current email address:");
        IImmutableContact updatedContact = contactToUpdate;
        try{
            if(updatedNameInput != null && !updatedNameInput.trim().isEmpty()) updatedContact = updatedContact.withName(updatedNameInput);
            if(updatedPhoneNumber != null && !updatedPhoneNumber.trim().isEmpty()) updatedContact = updatedContact.withPhoneNumber(updatedPhoneNumber);
            if(updatedEmailAddress != null && !updatedEmailAddress.trim().isEmpty()) updatedContact = updatedContact.withEmailAddress(updatedEmailAddress);
            addressBook.replaceContact(contactToUpdate, updatedContact);
        }
        catch(IllegalArgumentException ex){
            userInterface.printErrorMessage(ex.getMessage());
        }
    }

    private void deleteContactControl(IImmutableContact contactToDelete){
        addressBook.deleteContact(contactToDelete);
        userInterface.printSuccessMessage("Contact deleted!");
    }

    private void printContact(IImmutableContact contactToPrint){
        StringBuilder contactStringBuilder = new StringBuilder();
        contactStringBuilder
                .append("Name:\t\t\t")
                .append(contactToPrint.getName())
                .append("\nPhone number:\t")
                .append(contactToPrint.getPhoneNumber())
                .append("\nEmail address:\t")
                .append(contactToPrint.getEmailAddress());
        userInterface.printMessage(contactStringBuilder.toString());
    }


    private SortedMap<String,String> getContactsMenu(List<IImmutableContact> contactsToPrint){
        SortedMap<String,String> contactsMenu = new TreeMap<>();
        for(int i = 0; i < contactsToPrint.size(); i++){
            contactsMenu.put(Integer.valueOf(i+1).toString(),contactsToPrint.get(i).getName());
        }
        return contactsMenu;
    }




    private void printMenu(SortedMap<String,String> menu){
        StringBuilder menuString = new StringBuilder();
        menuString.append("\n---------------------------\n");
        for(Map.Entry entry : menu.entrySet()){
            menuString.append(entry.getKey())
                    .append(":\t")
                    .append(entry.getValue())
                    .append("\n");
        }
        userInterface.printMessage(menuString.toString());
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



}
