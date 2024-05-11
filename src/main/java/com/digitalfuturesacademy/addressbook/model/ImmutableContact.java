package com.digitalfuturesacademy.addressbook.model;

public final class ImmutableContact implements IImmutableContact{

    private final String name;

    public ImmutableContact(String name){
        if(name == null) throw new IllegalArgumentException("Arguments cannot be null");
        this.name = name.trim();
    };

    public String getName() {
        return name;
    }


}
