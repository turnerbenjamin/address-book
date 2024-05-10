package com.digitalfuturesacademy.addressbook.model;

public final class ImmutableContact implements IImmutableContact{

    private String name = null;

    //Factory method design pattern - This is to allow the UI to create instances
    // of this class while maintaining loose coupling
    public ImmutableContact(){};
    private ImmutableContact(String name){
        this.name = name;
    };

    public IImmutableContact create(String name){
        return new ImmutableContact(name.trim());
    }

    public String getName() {
        return name;
    }
}
