package com.digitalfuturesacademy.addressbook.controller;

import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.model.ImmutableContact;
import com.digitalfuturesacademy.addressbook.view.IUserInterface;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class AddressBookApp {

    private final IUserInterface userInterface;
    private final IAddressBook addressBook;
    private final SortedMap<String, String> addressBookMenu = new TreeMap<>();
    private final SortedMap<String, String> contactMenu = new TreeMap<>();
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
            userInterface.printMenu(addressBookMenu);
            userSelection = getUserSelectionFrom(addressBookMenu.keySet());
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
        userInterface.printMenu(contactsMenu);
        String userSelection = getUserSelectionFrom(contactsMenu.keySet());
        if(userSelection.equals("e")) return;
        readContactControl(contacts.get(Integer.parseInt(userSelection) - 1));
    }



    private void readContactControl(IImmutableContact contactToRead){
        userInterface.printContact(contactToRead);
        userInterface.printMenu(contactMenu);
        String userSelection = getUserSelectionFrom(contactMenu.keySet());
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





    //Returns a SortedMap representing a menu for a list of contacts. The keys
    //are a 1-based index for each contact and the values the contact's name.
    private SortedMap<String,String> getContactsMenu(List<IImmutableContact> contactsToPrint){
        SortedMap<String,String> contactsMenu = new TreeMap<>();
        for(int i = 0; i < contactsToPrint.size(); i++){
            contactsMenu.put(Integer.valueOf(i+1).toString(),contactsToPrint.get(i).getName());
        }
        return contactsMenu;
    }

    //Gets user selection from a menu. The menu should be a set of valid
    //user inputs. "e" is treated as a valid input for exit
    private String getUserSelectionFrom(Set<String> menu){
        String userInput;
        while(true){
            userInput = userInterface.getUserInput("Select an option by number or 'e' to exit:");
            if(userInput != null && (menu.contains(userInput) || userInput.equalsIgnoreCase("e"))) break;
            userInterface.printErrorMessage("Invalid selection!");
        }
        return userInput;
    }


}
