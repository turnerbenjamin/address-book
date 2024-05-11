package com.digitalfuturesacademy.addressbook.model;

public final class ImmutableContact implements IImmutableContact{

    private final String name;
    private final String phoneNumber;

    public ImmutableContact(String name, String phoneNumber){
        if(name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Arguments cannot be null or empty");
         if(phoneNumber == null) throw new IllegalArgumentException("Arguments cannot be null or empty");
        this.name = name.trim();
        this.phoneNumber = phoneNumber;
    };

    public String getName() {
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }


}
