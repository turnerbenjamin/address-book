package com.digitalfuturesacademy.addressbook.model;

public interface IImmutableContact {

    IImmutableContact create(String name);
    String getName();
}
