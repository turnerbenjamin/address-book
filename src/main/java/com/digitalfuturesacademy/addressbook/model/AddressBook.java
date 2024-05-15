package com.digitalfuturesacademy.addressbook.model;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook implements  IAddressBook{

    private final List<IImmutableContact> contacts = new ArrayList<>();
    private final Set<String> storedPhoneNumbers = new HashSet<>();
    private final Set<String> storedEmailAddresses = new HashSet<>();

    /**
     *
     * @return a list of all contacts in the address book
     */
    public List<IImmutableContact> getContacts(){
        return new ArrayList<>(contacts);
    }

    /**
     * @return the total number of contacts in the address book
     */
    public int size(){
        return contacts.size();
    }

    /**
     * Add a contact to the address book
     *
     * @param contactToAdd the contact to be added to address book
     * @return whether contact has been added
     */
    public boolean addContact(IImmutableContact contactToAdd){
        if(contactToAdd == null) throw new IllegalArgumentException("Contact to add cannot be null");
        checkHasUniqueContactDetails(contactToAdd);
        addContactDetailsToStoredPhoneNumbersAndEmailAddresses(contactToAdd);
        int indexAtWhichToStoreNewContact = getIndexAtWhichToStoreNewContact(contactToAdd.getName());
        contacts.add(indexAtWhichToStoreNewContact,contactToAdd);
        return true;
    }

    /**
     * Searches contacts by a given search term.
     *
     * @param searchTerm the term to match contacts against
     * @return a list of matching contacts
     */
    public List<IImmutableContact> searchContacts(String searchTerm){
        String formattedSearchTerm = formatStringForSearch(searchTerm);
        return contacts.stream()
                .filter(contact->getContactSearchString(contact).contains(formattedSearchTerm))
                .collect(Collectors.toList());
    }

    /**
     * Replace one contact in the address book with another
     *
     * @param originalContact a contact in the address book
     * @param updatedContact a contact to replace the original contact with
     * @return updated contact or null if the original contact is not found
     */
    public IImmutableContact replaceContact(IImmutableContact originalContact, IImmutableContact updatedContact){
        if(deleteContact(originalContact) == null) return null;
        try {
            addContact(updatedContact);
        }
        catch(IllegalArgumentException ex){
            addContact(originalContact);
            throw ex;
        }
        return updatedContact;
    }

    /**
     * Deletes a contact from the address book
     *
     * @param contactToDelete the contact to be removed from address book
     * @return The contact removed or null
     */
    public IImmutableContact deleteContact(IImmutableContact contactToDelete){
        boolean wasRemoved = contacts.remove(contactToDelete);
        if(!wasRemoved) return null;
        removeContactDetailsFromStoredPhoneNumbersAndEmailAddresses(contactToDelete);
        return contactToDelete;
    }

    /**
     * Deletes all contacts from the address book
     */
    public void deleteAllContacts(){
        contacts.clear();
        storedPhoneNumbers.clear();
        storedEmailAddresses.clear();
    }


    // ************ PRIVATE METHODS ************ \\

    private void checkHasUniqueContactDetails(IImmutableContact contactToCheck){
        checkHasUniquePhoneNumber(contactToCheck);
        checkHasUniqueEmailAddress(contactToCheck);
    }

    private void checkHasUniquePhoneNumber(IImmutableContact contactToCheck){
        if(storedPhoneNumbers.contains(contactToCheck.getPhoneNumber()))
            throw new IllegalArgumentException("Phone number must be unique");
    }

    private void checkHasUniqueEmailAddress(IImmutableContact contactToCheck){
        if(storedEmailAddresses.contains(contactToCheck.getEmailAddress()))
            throw new IllegalArgumentException("Email address must be unique");
    }

    private void addContactDetailsToStoredPhoneNumbersAndEmailAddresses(IImmutableContact contactToAdd){
        storedPhoneNumbers.add(contactToAdd.getPhoneNumber());
        storedEmailAddresses.add(contactToAdd.getEmailAddress());
    }

    private void removeContactDetailsFromStoredPhoneNumbersAndEmailAddresses(IImmutableContact contactToDelete){
        storedPhoneNumbers.remove(contactToDelete.getPhoneNumber());
        storedEmailAddresses.remove(contactToDelete.getEmailAddress());
    }

    private String formatStringForSearch(String stringToFormat){
        return stringToFormat.trim().toLowerCase();
    }

    //Joins the searchable fields of a contact in a string used for the search method
    private String getContactSearchString(IImmutableContact contact){
        String fieldSeparator = "*!*";
        String searchString = contact.getName() + fieldSeparator + contact.getPhoneNumber() + fieldSeparator + contact.getEmailAddress();
        return formatStringForSearch(searchString);
    }

    //Find the index at which to place the next contact to
    // keep contacts sorted in alphabetical order.
    private int getIndexAtWhichToStoreNewContact(String name){
        if(contacts.isEmpty()) return 0;
        int left = 0, right = contacts.size()-1, i;
        while(left < right){
            i = (int)(Math.floor(((double) right - (double) left)/2)) + left;
            if(contacts.get(i).getName().compareTo(name) >= 0) right = i - 1;
            else left = i + 1;
        }
        return contacts.get(left).getName().compareTo(name) > 0 ? left : left + 1;
    }

}
