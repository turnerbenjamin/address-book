package com.digitalfuturesacademy.addressbook.model;

public interface IImmutableContact {

    String getName();
    String getPhoneNumber();
    String getEmailAddress();

    IImmutableContact withName(String newName);
}
