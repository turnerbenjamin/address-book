package com.digitalfuturesacademy.addressbook.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringValidationTest {

    private final String STRING_WITH_CONTENT = "String with content";
    private final String STRING_WITHOUT_CONTENT = "   ";
    private final String VALID_PHONE_NUMBER = "+441780410545";
    private final String INVALID_PHONE_NUMBER = "+44178041X545";
    private final String VALID_EMAIL_ADDRESS = "test@email.com";
    private final String EMAIL_ADDRESS_WITHOUT_AT_SYMBOL = "test_email.com";
    private final String EMAIL_ADDRESS_WITHOUT_DOMAIN = "test.me@email,com";

    @Test
    @DisplayName("HasContent should return true if argument is not empty or null")
    public void SV1_SV2_SV3(){
        assertAll(
                //SV1
                ()-> assertFalse(StringValidation.hasContent(null))
        );
    }


}
