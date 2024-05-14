package com.digitalfuturesacademy.addressbook.utils;

public final class StringValidation {
    private StringValidation(){}
    private static final String PHONE_NUMBER_VALIDATION_REGEX = "^[+\\d]\\d+$";
    private static final String EMAIL_ADDRESS_VALIDATION_REGEX = "^.+@.+\\..+$";


    /**
     * Check whether string has content, i.e., it is not null and
     * has characters which are not just whitespace.
     *
     * @param string string to validate
     * @return true when string has content else false
     */
    public static boolean hasContent (String string){
        return string != null && !string.trim().isEmpty();
    }

    /**
     * Check whether a phone number matches a loose validation
     * pattern. The first character should be a plus or digit and
     * subsequent chars must be digits.
     *
     * @param phoneNumber phone number to validate
     * @return true if the phone number is valid
     */
    public static boolean isValidPhoneNumber (String phoneNumber){
        return hasContent(phoneNumber) && phoneNumber.matches(PHONE_NUMBER_VALIDATION_REGEX);
    }

    /**
     * Check whether an email address matches a loose validation
     * pattern. The address should have an @ symbol and
     * a domain.
     *
     * @param emailAddress email address to validate
     * @return true if the email address is valid
     */
    public static boolean isValidEmailAddress (String emailAddress){
        return hasContent(emailAddress) && emailAddress.matches(EMAIL_ADDRESS_VALIDATION_REGEX);
    }

}
