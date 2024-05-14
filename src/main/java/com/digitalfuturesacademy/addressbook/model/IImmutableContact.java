package com.digitalfuturesacademy.addressbook.model;

public interface IImmutableContact {
    /*
    Implementations should ensure that instances are immutable.
    */

    String getName();
    String getPhoneNumber();
    String getEmailAddress();

    IImmutableContact withName(String newName);
    IImmutableContact withPhoneNumber(String newPhoneNumber);
    IImmutableContact withEmailAddress(String newEmailAddress);
}
