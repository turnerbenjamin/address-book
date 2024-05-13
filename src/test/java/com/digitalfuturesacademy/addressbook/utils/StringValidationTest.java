package com.digitalfuturesacademy.addressbook.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringValidationTest {

    private final String STRING_WITH_CONTENT = "String with content";
    private final String STRING_WITHOUT_CONTENT = "   ";
    private final String VALID_PHONE_NUMBER = "+441780410545";
    private final String INVALID_PHONE_NUMBER = "+44178041X545";
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
                ()-> assertFalse(StringValidation.isValidPhoneNumber(STRING_WITHOUT_CONTENT)) //SV6
        );
    }


}
