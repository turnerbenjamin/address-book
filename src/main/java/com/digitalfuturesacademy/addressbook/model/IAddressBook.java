package com.digitalfuturesacademy.addressbook.model;

import java.util.List;

public interface IAddressBook {

    boolean addContact(IImmutableContact contactToAdd);

    IImmutableContact deleteContact(IImmutableContact contactToDelete);

    List<IImmutableContact> getContacts();

    IImmutableContact replaceContact(IImmutableContact old, IImmutableContact updated);

    List<IImmutableContact> searchContacts(String searchTerm);

}
