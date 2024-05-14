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
            if(userSelection.equals("3")) searchContactsControl();
        }
    }

    private void createUserControl(){
        try{
            IImmutableContact newContact = new ImmutableContact(getNameForNewContact(), getPhoneNumberForNewContact(),getEmailAddressForNewContact());
            addressBook.addContact(newContact);
            userInterface.printSuccessMessage("Success: Contact added to address book");
        }catch(IllegalArgumentException ex){
            userInterface.printErrorMessage(ex.getMessage());
        }
    }

    private void readAllContactsControl(){
        readContactsControl(addressBook.getContacts());
    }

    private void searchContactsControl(){
        String searchTerm = userInterface.getUserInput("Search by name:");
        readContactsControl(addressBook.searchContacts(searchTerm));
    }

        private void readContactsControl(List<IImmutableContact> contacts){
        if(contacts.isEmpty()){
            userInterface.printErrorMessage("No contacts found!");
            return;
        }
        SortedMap<String,String> contactsMenu = getContactsMenu(contacts);
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
        try{
            IImmutableContact updatedContact = updateContactName(contactToUpdate);
            updatedContact = updateContactPhoneNumber(updatedContact);
            updatedContact = updateContactEmailAddress(updatedContact);
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


    private String getNameForNewContact(){
        String nameInput = userInterface.getUserInput("Enter the contact's name:");
        validateHasContent(nameInput, "Name");
        return nameInput;
    }

        private String getPhoneNumberForNewContact(){
        String phoneNumberInput = userInterface.getUserInput("Enter the contact's phone number:");
        validateHasContent(phoneNumberInput, "Phone number");
        validatePhoneNumber(phoneNumberInput);
        return phoneNumberInput;
    }

        private String getEmailAddressForNewContact(){
        String emailAddressInput = userInterface.getUserInput("Enter the contact's email address:");
        validateHasContent(emailAddressInput, "Email address");
        validateEmailAddress(emailAddressInput);
        return emailAddressInput;
    }

    private IImmutableContact updateContactName(IImmutableContact contactToUpdate){
        String updatedNameInput = userInterface.getUserInput("Enter new name, or press enter to keep current name:");
        if(!StringValidation.hasContent(updatedNameInput)) return contactToUpdate;
        return contactToUpdate.withName(updatedNameInput);
    }

    private IImmutableContact updateContactPhoneNumber(IImmutableContact contactToUpdate){
        String updatedPhoneNumberInput = userInterface.getUserInput("Enter new phone number, or press enter to keep current phone number:");
        if(!StringValidation.hasContent(updatedPhoneNumberInput)) return contactToUpdate;
        validatePhoneNumber(updatedPhoneNumberInput);
        return contactToUpdate.withPhoneNumber(updatedPhoneNumberInput);
    }

    private IImmutableContact updateContactEmailAddress(IImmutableContact contactToUpdate){
        String updatedEmailAddressInput = userInterface.getUserInput("Enter new email address, or press enter to keep current email address:");
        if(!StringValidation.hasContent(updatedEmailAddressInput)) return contactToUpdate;
        validateEmailAddress(updatedEmailAddressInput);
        return contactToUpdate.withEmailAddress(updatedEmailAddressInput);
    }

    private void validateHasContent(String stringToCheck, String paramName){
         if(!StringValidation.hasContent(stringToCheck))
            throw new IllegalArgumentException(paramName + " must not be empty");
    }

    private void validatePhoneNumber(String phoneNumber){
        if(!StringValidation.isValidPhoneNumber(phoneNumber))
            throw new IllegalArgumentException("Phone number must include only digits, other than the first character which may be a `+`!!");
    }

    private void validateEmailAddress(String phoneNumber){
         if(!StringValidation.isValidEmailAddress(phoneNumber))
            throw new IllegalArgumentException("Email must contain an @ symbol followed by a .domain!!");
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
