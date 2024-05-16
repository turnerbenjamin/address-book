package com.digitalfuturesacademy.addressbook.controller;

import com.digitalfuturesacademy.addressbook.model.IAddressBook;
import com.digitalfuturesacademy.addressbook.model.IImmutableContact;
import com.digitalfuturesacademy.addressbook.model.ImmutableContact;
import com.digitalfuturesacademy.addressbook.utils.StringValidation;
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

    //Menu initializer
    {
        //Initialize address book options
        addressBookMenu.put("1", "Add a contact");
        addressBookMenu.put("2", "View all contacts");
        addressBookMenu.put("3", "Search contacts");
        addressBookMenu.put("4", "Delete all contacts");
        //Initialize contact menu options
        contactMenu.put("1", "Update Contact");
        contactMenu.put("2", "Delete Contact");
    }

    public AddressBookApp(IUserInterface userInterface, IAddressBook addressBook){
        if(userInterface == null || addressBook == null)
            throw new IllegalArgumentException("User interface and address book cannot be null");
        this.userInterface = userInterface;
        this.addressBook = addressBook;
    }

    /**
     * Runs address book application
     */
    public void run(){
        topLevelMenuControl();
    }

    // ************ CONTROLLERS ************ \\

    //*** TOP-LEVEL MENU CONTROLLERS

    //Control application flow for the top-level menu
    private void topLevelMenuControl(){
        String userSelection;
        while(true){
            userInterface.printMenu(addressBookMenu);
            userSelection = getUserSelectionFrom(addressBookMenu.keySet());
            if(userSelection.equals("e")) break;
            mapTopLevelMenuSelectionToController(userSelection);
        }
    }

    //Controls application flow for read all contacts
    private void readAllContactsControl(){
        readContactsControl(addressBook.getContacts());
    }

    //Controls application flow for search contacts
    private void searchContactsControl(){
        String searchTerm = userInterface.getUserInput("Search by name, phone number or email address:\t");
        if(!StringValidation.hasContent(searchTerm)){
            userInterface.printErrorMessage("Search term cannot be empty");
            return;
        }
        readContactsControl(addressBook.searchContacts(searchTerm));
    }

    //General controller for reading from a list of candidates.
    //Used by readAllContactsControl and searchContactsControl
    private void readContactsControl(List<IImmutableContact> contacts){
        if(checkIfReadContactsEmpty(contacts)) return;
        if(checkIfReadContactsSingle(contacts)) readContactControl(contacts.get(0));
        else contactsMenuControl(contacts);
    }

    //Controller responsible for delete all contacts option
    private void deleteAllContactsControl(){
        if (!confirmThereAreContactsToDelete()) return;
        userInterface.printWarningMessage("\nWARNING: You have selected \"Delete all contacts\". All contact data will be removed from your address book!!\n");
        boolean hasUserConfirmedDeleteAllContacts = getUserConfirmationForAction();
        carryOutUserDecisionToDeleteAllContacts(hasUserConfirmedDeleteAllContacts);

    }

    //***CONTACTS MENU CONTROLLER

    //Controller for contacts menu, i.e. where there is a printed list of contacts
    //and users select the contact they want to view
    private void contactsMenuControl(List<IImmutableContact> contacts){
        SortedMap<String,String> contactsMenu = getContactsMenu(contacts);
        userInterface.printMenu(contactsMenu);
        String userSelection = getUserSelectionFrom(contactsMenu.keySet());
        if(userSelection.equals("e")) return;
        readContactControl(contacts.get(Integer.parseInt(userSelection) - 1));
    }

    //***CONTACT MENU CONTROLLERS

    //Controller responsible for the flow of application where contact selected
    //from a list of contacts
    private void readContactControl(IImmutableContact contactToRead){
        userInterface.printContact(contactToRead);
        contactMenuControl(contactToRead);
    }

    //Controller for the flow of the contact menu, i.e. the options that can be performed on
    //a given contact
    private void contactMenuControl(IImmutableContact selectedContact){
        userInterface.printMenu(contactMenu);
        String userSelection = getUserSelectionFrom(contactMenu.keySet());
        if(userSelection.equals("e")) return;
        mapContactMenuSelectionToController(userSelection, selectedContact);
    }

    // Controller responsible for the flow of update contact
    private void updateContactControl(IImmutableContact contactToUpdate){
        try{
            userInterface.printMessage("\nUPDATING CONTACT");
            addressBook.replaceContact(contactToUpdate, updateContact(contactToUpdate));
        }
        catch(IllegalArgumentException ex){
            userInterface.printErrorMessage(ex.getMessage());
        }
    }

    // Controller responsible for the flow of delete contact
    private void deleteContactControl(IImmutableContact contactToDelete){
        addressBook.deleteContact(contactToDelete);
        userInterface.printSuccessMessage("\nSUCCESS: Contact deleted!");
    }

    // ************ CREATE CONTACT METHODS ************ \\
    private void createContact(){
        try{
            userInterface.printMessage("\nADDING NEW CONTACT");
            IImmutableContact newContact = new ImmutableContact(getNameForNewContact(), getPhoneNumberForNewContact(),getEmailAddressForNewContact());
            addressBook.addContact(newContact);
            userInterface.printSuccessMessage("\nSUCCESS: Contact added to address book");
        }catch(IllegalArgumentException ex){
            userInterface.printErrorMessage(ex.getMessage());
        }
    }

    private String getNameForNewContact(){
        String nameInput = userInterface.getUserInput("Enter the contact's name:\t");
        validateHasContent(nameInput, "Name");
        return nameInput;
    }

    private String getPhoneNumberForNewContact(){
        String phoneNumberInput = userInterface.getUserInput("Enter the contact's phone number:\t");
        validateHasContent(phoneNumberInput, "Phone number");
        validatePhoneNumber(phoneNumberInput);
        return phoneNumberInput;
    }

    private String getEmailAddressForNewContact(){
        String emailAddressInput = userInterface.getUserInput("Enter the contact's email address:\t");
        validateHasContent(emailAddressInput, "Email address");
        validateEmailAddress(emailAddressInput);
        return emailAddressInput;
    }


    // ************ UPDATE CONTACT METHODS ************ \\
    private IImmutableContact updateContact(IImmutableContact contactToUpdate){
        IImmutableContact updatedContact = updateContactName(contactToUpdate);
        updatedContact = updateContactPhoneNumber(updatedContact);
        updatedContact = updateContactEmailAddress(updatedContact);
        return updatedContact;
    }

    private IImmutableContact updateContactName(IImmutableContact contactToUpdate){
        String updatedNameInput = userInterface.getUserInput("Enter new name, or press enter to keep current name:\t");
        if(!StringValidation.hasContent(updatedNameInput)) return contactToUpdate;
        return contactToUpdate.withName(updatedNameInput);
    }

    private IImmutableContact updateContactPhoneNumber(IImmutableContact contactToUpdate){
        String updatedPhoneNumberInput = userInterface.getUserInput("Enter new phone number, or press enter to keep current phone number:\t");
        if(!StringValidation.hasContent(updatedPhoneNumberInput)) return contactToUpdate;
        validatePhoneNumber(updatedPhoneNumberInput);
        return contactToUpdate.withPhoneNumber(updatedPhoneNumberInput);
    }

    private IImmutableContact updateContactEmailAddress(IImmutableContact contactToUpdate){
        String updatedEmailAddressInput = userInterface.getUserInput("Enter new email address, or press enter to keep current email address:\t");
        if(!StringValidation.hasContent(updatedEmailAddressInput)) return contactToUpdate;
        validateEmailAddress(updatedEmailAddressInput);
        return contactToUpdate.withEmailAddress(updatedEmailAddressInput);
    }

    // ************ DELETE ALL CONTACTS METHODS ************ \\
    private boolean confirmThereAreContactsToDelete(){
        if(addressBook.getContacts().isEmpty()){
            userInterface.printErrorMessage("There are no contacts to delete!");
            return false;
        }
        return true;
    }

    private void carryOutUserDecisionToDeleteAllContacts(boolean hasUserConfirmedDeleteAllContacts){
        if(hasUserConfirmedDeleteAllContacts){
            addressBook.deleteAllContacts();
            userInterface.printSuccessMessage("\nSUCCESS: All contacts deleted!");
        }else{
            userInterface.printMessage("\nDelete all contacts cancelled");
        }
    }

    // ************ READ CONTACTS METHODS ************ \\

    private boolean checkIfReadContactsEmpty(List<IImmutableContact> contacts){
        if(contacts.isEmpty()){
            userInterface.printErrorMessage("No contacts found");
            return true;
        }
        return false;
    }

    private boolean checkIfReadContactsSingle(List<IImmutableContact> contacts){
        if(contacts.size() == 1){
            userInterface.printMessage("\nOne matching contact found:");
            return true;
        }
        return false;
    }

    // ************ VALIDATORS ************ \\
    private void validateHasContent(String stringToCheck, String paramName){
        if(!StringValidation.hasContent(stringToCheck))
            throw new IllegalArgumentException(paramName + " must not be empty");
    }

    private void validatePhoneNumber(String phoneNumberToCheck){
        if(!StringValidation.isValidPhoneNumber(phoneNumberToCheck))
            throw new IllegalArgumentException("Phone number must include only digits, other than the first character which may be a `+`!!");
    }

    private void validateEmailAddress(String emailAddressToCheck){
        if(!StringValidation.isValidEmailAddress(emailAddressToCheck))
            throw new IllegalArgumentException("Email must contain an @ symbol followed by a .domain!!");
    }


    // ************ MENU TO CONTROLLER MAPPING ************ \\
    //Mapping for top level menu
    private void mapTopLevelMenuSelectionToController(String userSelection){
        if(userSelection.equals("1")) createContact();
        if(userSelection.equals("2")) readAllContactsControl();
        if(userSelection.equals("3")) searchContactsControl();
        if(userSelection.equals("4")) deleteAllContactsControl();
    }

    //Mapping for contact menu
    private void mapContactMenuSelectionToController(String userSelection, IImmutableContact selectedContact){
        if(userSelection.equals("1")) updateContactControl(selectedContact);
        if(userSelection.equals("2")) deleteContactControl(selectedContact);
    }

    // ************ MENU GENERATORS ************ \\
    //Generates a contacts menu from a list of contacts
    private SortedMap<String,String> getContactsMenu(List<IImmutableContact> contactsToPrint){
        SortedMap<String,String> contactsMenu = new TreeMap<>();
        for(int i = 0; i < contactsToPrint.size(); i++){
            contactsMenu.put(Integer.valueOf(i+1).toString(),contactsToPrint.get(i).getName());
        }
        return contactsMenu;
    }

    // ************ USER SELECTION HANDLERS ************ \\

    //For a given menu, prompts users to provide a valid selection and returns the selection
    //to the calling method
    private String getUserSelectionFrom(Set<String> menu){
        String userInput;
        while(true){
            userInput = userInterface.getUserInput("Select an option by number or 'e' to exit:\t");
            if(userInput != null && (menu.contains(userInput) || userInput.equalsIgnoreCase("e"))) break;
            userInterface.printErrorMessage("Invalid selection!");
        }
        return userInput;
    }

    //Checks for a yes or no response to a given prompt to confirm some action.
    //returns true for "yes" and false for "no". Case-insensitive.
    private boolean getUserConfirmationForAction(){
        String userInput;
        while(true){
            userInput = userInterface.getUserInput("Are you sure you want to delete all contacts? Type either \"YES\" or \"NO\":\t");
            if(userInput == null) continue;
            if(userInput.equalsIgnoreCase("YES")) return true;
            if(userInput.equalsIgnoreCase("NO")) return false;
        }
    }
}
