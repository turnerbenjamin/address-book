package com.digitalfuturesacademy.addressbook.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AddressBook {

    private final List<IImmutableContact> contacts = new ArrayList<>();
    private final Set<String> phoneNumbers = new HashSet<>();
    private final Set<String> emailAddresses = new HashSet<>();


    public boolean addContact(IImmutableContact contactToAdd){
        if(contactToAdd == null) throw new IllegalArgumentException("Contact to add cannot be null");
        checkHasUniqueContactDetails(contactToAdd);
        return contacts.add(contactToAdd);
    }

    public List<IImmutableContact> getContacts(){
        return new ArrayList<>(contacts);
    }

    public int size(){
        return contacts.size();
    }

    private void checkHasUniqueContactDetails(IImmutableContact contactToCheck){
        if(phoneNumbers.contains(contactToCheck.getPhoneNumber()))
            throw new IllegalArgumentException("Phone number must be unique");
        if(emailAddresses.contains(contactToCheck.getEmailAddress()))
            throw new IllegalArgumentException("Email address must be unique");
        phoneNumbers.add(contactToCheck.getPhoneNumber());
        emailAddresses.add(contactToCheck.getEmailAddress());
    }

    private String formatStringForSearch(String str){
        return str.trim().toLowerCase();
    }

    public List<IImmutableContact> searchContacts(String searchTerm){
        String formattedSearchTerm = formatStringForSearch(searchTerm);
        return contacts.stream()
                .filter(contact->formatStringForSearch(contact.getName()).contains(formattedSearchTerm))
                .collect(Collectors.toList());
    }


    public IImmutableContact deleteContact(IImmutableContact contactToDelete){
        return contacts.remove(contactToDelete) ? contactToDelete : null;
    }



}
