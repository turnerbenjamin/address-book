package com.digitalfuturesacademy.addressbook.utils;

public final class StringValidation {
    private StringValidation(){}
    private static final String PHONE_NUMBER_VALIDATION_REGEX = "^[+\\d]\\d+$";
    private static final String EMAIL_ADDRESS_VALIDATION_REGEX = "^.+@.+\\..+$";


    /**
     * Check whether string has content, i.e., it is not null and
     * has characters which are not just whitespace.
     *
     * @param stringToCheck string to validate
     * @return true when string has content else false
     */
    public static boolean hasContent (String stringToCheck){
        return stringToCheck != null && !stringToCheck.trim().isEmpty();
    }

    /**
     * Check whether a phone number matches a loose validation
     * pattern. The first character should be a plus or digit and
     * subsequent chars must be digits.
     *
     * @param phoneNumberToCheck phone number to validate
     * @return true if the phone number is valid
     */
    public static boolean isValidPhoneNumber (String phoneNumberToCheck){
        return hasContent(phoneNumberToCheck) && phoneNumberToCheck.matches(PHONE_NUMBER_VALIDATION_REGEX);
    }

    /**
     * Check whether an email address matches a loose validation
     * pattern. The address should have an @ symbol and
     * a domain.
     *
     * @param emailAddressToCheck email address to validate
     * @return true if the email address is valid
     */
    public static boolean isValidEmailAddress (String emailAddressToCheck){
        return hasContent(emailAddressToCheck) && emailAddressToCheck.matches(EMAIL_ADDRESS_VALIDATION_REGEX);
    }

}
