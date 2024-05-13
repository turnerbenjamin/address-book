package com.digitalfuturesacademy.addressbook.utils;

public final class StringValidation {
    private StringValidation(){}
    private static final String PHONE_NUMBER_VALIDATION_REGEX = "^[+\\d]\\d+$";
    private static final String EMAIL_ADDRESS_VALIDATION_REGEX = "^.+@.+\\..+$";


    public static boolean hasContent (String string){
        return string != null && !string.trim().isEmpty();
    }

    public static boolean isValidPhoneNumber (String string){
        return hasContent(string) && string.matches(PHONE_NUMBER_VALIDATION_REGEX);
    }

    public static boolean isValidEmailAddress (String string){
        return hasContent(string) && string.matches(EMAIL_ADDRESS_VALIDATION_REGEX);
    }

}
