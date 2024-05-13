package com.digitalfuturesacademy.addressbook.model;

import com.digitalfuturesacademy.addressbook.utils.StringValidation;

public final class ImmutableContact implements IImmutableContact{

    private final String name;
    private final String phoneNumber;
    private final String emailAddress;

    public ImmutableContact(String name, String phoneNumber, String emailAddress){
        this.name = validateString(name);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = validateEmailAddress(emailAddress);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public IImmutableContact withName(String newName){
        return new ImmutableContact(newName, this.phoneNumber, this.emailAddress);
    }

    public IImmutableContact withPhoneNumber(String newPhoneNumber){
        return new ImmutableContact(this.name, newPhoneNumber, this.emailAddress);
    }

    public IImmutableContact withEmailAddress(String newEmailAddress){
        return new ImmutableContact(this.name, this.phoneNumber, newEmailAddress);
    }



    private String validateString(String string){
        if(!StringValidation.hasContent(string))
            throw new IllegalArgumentException("Arguments cannot be null");
        return string.trim();
    }


    private String validatePhoneNumber(String phoneNumber){
        if(!StringValidation.isValidPhoneNumber(validateString(phoneNumber)))
            throw new IllegalArgumentException("Phone number must include only digits, other than the first character which may be a `+`");
        return phoneNumber.trim();
    }

    private String validateEmailAddress(String emailAddress){
         if(!StringValidation.isValidEmailAddress(validateString(emailAddress)))
            throw new IllegalArgumentException("Invalid email, must contain @ symbol and a domain");
        return emailAddress.trim();
    }
}
