package com.digitalfuturesacademy.addressbook.model;

import java.util.List;

public interface IAddressBook {

    boolean addContact(IImmutableContact contactToAdd);

    void deleteAllContacts();

    IImmutableContact deleteContact(IImmutableContact contactToDelete);

    List<IImmutableContact> getContacts();

    IImmutableContact replaceContact(IImmutableContact originalContact, IImmutableContact updatedContact);

    List<IImmutableContact> searchContacts(String searchTerm);



}
