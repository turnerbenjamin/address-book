package com.digitalfuturesacademy.addressbook.utils;

public final class StringValidation {
    private StringValidation(){};

    public static boolean hasContent (String string){
        return string != null && !string.trim().isEmpty();
    }

    public static boolean isValidPhoneNumber (String string){
        return true;
    }

}
