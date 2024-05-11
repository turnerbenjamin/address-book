package com.digitalfuturesacademy.addressbook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {

    private final List<IImmutableContact> contacts = new ArrayList<>();

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
