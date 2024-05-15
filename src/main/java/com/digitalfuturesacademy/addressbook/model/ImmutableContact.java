package com.digitalfuturesacademy.addressbook.model;

import com.digitalfuturesacademy.addressbook.utils.StringValidation;

public final class ImmutableContact implements IImmutableContact {

    private final String name;
    private final String phoneNumber;
    private final String emailAddress;

    public ImmutableContact(String name, String phoneNumber, String emailAddress) {
        this.name = validateString(name);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = validateEmailAddress(emailAddress);
    }

    public String getName(){
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmailAddress(){
        return emailAddress;
    }

    /**
     * Provides a new contact object with the name updated
     *
     * @param newName name to set on the new object
     * @return a clone of this object with the name set to new name
     */
    public IImmutableContact withName(String newName) {
        return new ImmutableContact(newName, this.phoneNumber, this.emailAddress);
    }

    /**
     * Provides a new contact object with the phone number updated
     *
     * @param newPhoneNumber phone number to set on the new object
     * @return a clone of this object with the phone number set to new phone number
     */
    public IImmutableContact withPhoneNumber(String newPhoneNumber) {
        return new ImmutableContact(this.name, newPhoneNumber, this.emailAddress);
    }

    /**
     * Provides a new contact object with the email address updated
     *
     * @param newEmailAddress email address to set on the new object
     * @return a clone of this object with the email address set to new email address
     */
    public IImmutableContact withEmailAddress(String newEmailAddress) {
        return new ImmutableContact(this.name, this.phoneNumber, newEmailAddress);
    }


    // ************ PRIVATE METHODS ************ \\

    //Checks that the string has content and returns a trimmed copy of the string
    private String validateString(String stringToValidate) {
        if (!StringValidation.hasContent(stringToValidate))
            throw new IllegalArgumentException("Arguments cannot be null");
        return stringToValidate.trim();
    }

    //Checks that the phone number matches a validation pattern and returns a trimmed copy of the phone number
    private String validatePhoneNumber(String phoneNumberToValidate) {
        if (!StringValidation.isValidPhoneNumber(validateString(phoneNumberToValidate)))
            throw new IllegalArgumentException("Phone number must include only digits, other than the first character which may be a `+`");
        return phoneNumberToValidate.trim();
    }

    //Checks that the email address matches a validation pattern and returns a trimmed copy of the email address
    private String validateEmailAddress(String emailAddressToValidate) {
        if (!StringValidation.isValidEmailAddress(validateString(emailAddressToValidate)))
            throw new IllegalArgumentException("Invalid email, must contain @ symbol and a domain");
        return emailAddressToValidate.trim();
    }
}
