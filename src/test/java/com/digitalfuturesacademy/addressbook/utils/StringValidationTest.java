package com.digitalfuturesacademy.addressbook.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringValidationTest {

    private final String STRING_WITH_CONTENT = "String with content";
    private final String STRING_WITHOUT_CONTENT = "   ";
    private final String VALID_PHONE_NUMBER = "+441780410545";
    private final String PHONE_NUMBER_WITH_INVALID_FIRST_CHAR = "X441780410545";
    private final String PHONE_NUMBER_WITH_INVALID_LATER_CHAR = "+4417804105+5";
    private final String VALID_EMAIL_ADDRESS = "test@email.com";
    private final String EMAIL_ADDRESS_WITHOUT_AT_SYMBOL = "test_email.com";
    private final String EMAIL_ADDRESS_WITHOUT_DOMAIN = "test.me@email,com";

    @Test
    @DisplayName("Has Content tests")
    public void SV1_SV2_SV3(){
        assertAll(
                ()-> assertTrue(StringValidation.hasContent(STRING_WITH_CONTENT)), //SV1
                ()-> assertFalse(StringValidation.hasContent(null)), //SV2
                ()-> assertFalse(StringValidation.hasContent(STRING_WITHOUT_CONTENT)) //SV3
        );
    }

    @Test
    @DisplayName("Is Valid Phone Number Tests")
    public void SV4_SV5_SV6_SV7_SV8(){
        assertAll(
                ()-> assertTrue(StringValidation.isValidPhoneNumber(VALID_PHONE_NUMBER)), //SV4
                ()-> assertFalse(StringValidation.isValidPhoneNumber(null)), //SV5
                ()-> assertFalse(StringValidation.isValidPhoneNumber(STRING_WITHOUT_CONTENT)), //SV6
                ()-> assertFalse(StringValidation.isValidPhoneNumber(PHONE_NUMBER_WITH_INVALID_LATER_CHAR)), //SV7
                ()-> assertFalse(StringValidation.isValidPhoneNumber(PHONE_NUMBER_WITH_INVALID_FIRST_CHAR)) //SV8
        );
    }

    @Test
    @DisplayName("Is Valid Email Address Tests")
    public void SV9_SV10_SV11_SV12_SV13(){
        assertAll(
                ()-> assertTrue(StringValidation.isValidEmailAddress(VALID_EMAIL_ADDRESS)), //SV9
                ()-> assertFalse(StringValidation.isValidEmailAddress(null)), //SV10
                ()-> assertFalse(StringValidation.isValidEmailAddress(STRING_WITHOUT_CONTENT)), //SV11
                ()-> assertFalse(StringValidation.isValidEmailAddress(EMAIL_ADDRESS_WITHOUT_AT_SYMBOL)) //SV12
        );
    }



}
