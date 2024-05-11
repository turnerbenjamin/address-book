package com.digitalfuturesacademy.addressbook.model;

import org.intellij.lang.annotations.RegExp;

public final class ImmutableContact implements IImmutableContact{

    private final String name;
    private final String phoneNumber;
    private final String emailAddress;

    public ImmutableContact(String name, String phoneNumber, String emailAddress){
        this.name = validateString(name);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = validateEmailAddress(emailAddress);
    };

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



    private String validateString(String string){
        if(string == null) throw new IllegalArgumentException("Arguments cannot be null");
        String trimmed = string.trim();
        if(trimmed.isEmpty()) throw new IllegalArgumentException("Arguments cannot be empty");
        return string.trim();
    }

    private String validateString(String string, String regex, String exceptionMessage){
        String trimmedString = validateString(string);
        if(!trimmedString.matches(regex))  throw new IllegalArgumentException(exceptionMessage);
        return  trimmedString;
    }

    private String validatePhoneNumber(String phoneNumber){
        String phoneNumberRegex = "^[\\+\\d]\\d+$";
        return validateString(phoneNumber, phoneNumberRegex, "Phone number must include only digits, other than the first character which may be a `+`");
    }

    private String validateEmailAddress(String emailAddress){
        String emailRegEx = "^.+@.+\\..+$";
        return validateString(emailAddress, emailRegEx, "Invalid email, must contain @ symbol and a domain");
    }


}
