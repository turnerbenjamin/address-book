package com.digitalfuturesacademy.addressbook.model;

public final class ImmutableContact implements IImmutableContact{

    private final String name;
    private final String phoneNumber;
    private final String emailAddress;

    public ImmutableContact(String name, String phoneNumber, String emailAddress){
        if(name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Arguments cannot be null or empty");
        if(phoneNumber == null || phoneNumber.trim().isEmpty()) throw new IllegalArgumentException("Arguments cannot be null or empty");
        if(emailAddress == null || emailAddress.trim().isEmpty()) throw new IllegalArgumentException("Arguments cannot be null or empty");
        this.name = name.trim();
        this.phoneNumber = phoneNumber.trim();
        this.emailAddress = emailAddress.trim();
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


}
