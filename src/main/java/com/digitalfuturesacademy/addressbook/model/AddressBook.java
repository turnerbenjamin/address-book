package com.digitalfuturesacademy.addressbook.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

    private List<IImmutableContact> contacts = new ArrayList<>();

    public boolean addContact(IImmutableContact contactToAdd){
        return contacts.add(contactToAdd);
    }

    public List<IImmutableContact> getContacts(){
        return new ArrayList<>(contacts);
    }

    public int size(){
        return contacts.size();
    }


}
