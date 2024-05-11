package com.digitalfuturesacademy.addressbook.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

    private List<IImmutableContact> contacts = new ArrayList<>();

    public boolean addContact(IImmutableContact contactToAdd){
        if(contactToAdd == null) throw new IllegalArgumentException("Contact to add cannot be null");
        return contacts.add(contactToAdd);
    }

    public List<IImmutableContact> getContacts(){
        return new ArrayList<>(contacts);
    }

    public int size(){
        return contacts.size();
    }



    public List<IImmutableContact> searchContacts(String searchTerm){
        List<IImmutableContact> matchingContacts = new ArrayList<>();
        String formattedSearchTerm = formatStringForSearch(searchTerm);

        for(IImmutableContact candidateContact : contacts){
            String formattedStringToSearch = formatStringForSearch(candidateContact.getName());
            if(!formattedStringToSearch.contains(formattedSearchTerm)) continue;
            matchingContacts.add(candidateContact);
        }
        return matchingContacts;
    }

    private String formatStringForSearch(String str){
        return str.trim().toLowerCase();
    }



}
