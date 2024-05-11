package com.digitalfuturesacademy.addressbook.model;

public final class ImmutableContact implements IImmutableContact{

    private final String name;

    public ImmutableContact(String name){
        if(name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Arguments cannot be null or empty");
        this.name = name.trim();
    };

    public String getName() {
        return name;
    }


}
