package com.digitalfuturesacademy.addressbook.model;

/**
 * A data structure to hold state for a contact.
 * Implementations of this interface should ensure that
 * instances are immutable.
 *
 * @see ImmutableContact
 */
public interface IImmutableContact {

    String getName();
    String getPhoneNumber();
    String getEmailAddress();

    IImmutableContact withName(String newName);
    IImmutableContact withPhoneNumber(String newPhoneNumber);
    IImmutableContact withEmailAddress(String newEmailAddress);
}
