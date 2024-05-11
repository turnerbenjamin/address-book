package com.digitalfuturesacademy.addressbook.model;

public final class ImmutableContact implements IImmutableContact{

    private final String name;

    public ImmutableContact(String name){
        this.name = name.trim();
    };

    public String getName() {
        return name;
    }


}
