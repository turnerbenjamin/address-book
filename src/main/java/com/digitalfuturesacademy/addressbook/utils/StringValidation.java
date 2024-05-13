package com.digitalfuturesacademy.addressbook.utils;

public final class StringValidation {
    private StringValidation(){};

    public static boolean hasContent (String string){
        return string != null && !string.trim().isEmpty();
    }

    public static boolean isValidPhoneNumber (String string){
        return hasContent(string) && string.matches("^[\\+\\d]\\d+$");
    }

    public static boolean isValidEmailAddress (String string){
        return hasContent(string);
    }

}
