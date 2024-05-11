package com.digitalfuturesacademy.addressbook.model;

import org.intellij.lang.annotations.RegExp;

public final class ImmutableContact implements IImmutableContact{

    private final String name;
    private final String phoneNumber;
    private final String emailAddress;

    public ImmutableContact(String name, String phoneNumber, String emailAddress){
        this.name = trimStringIfNotNullOrEmpty(name);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = trimStringIfNotNullOrEmpty(emailAddress);
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


    private String trimStringIfNotNullOrEmpty(String str){
        if(str == null) throw new IllegalArgumentException("Arguments cannot be null");
        String trimmed = str.trim();
        if(trimmed.isEmpty()) throw new IllegalArgumentException("Arguments cannot be empty");
        return str.trim();
    }


    private String validatePhoneNumber(String phoneNumber){
        String trimmedPhoneNumber = trimStringIfNotNullOrEmpty(phoneNumber);
        if(!trimmedPhoneNumber.matches("^[\\+\\d]\\d+$"))
          throw new IllegalArgumentException("Phone number must include only digits, other than the first character which may be a `+`");
        return trimmedPhoneNumber;
    }


}
