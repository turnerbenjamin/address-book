package com.digitalfuturesacademy.addressbook.utils;

import com.digitalfuturesacademy.addressbook.testdata.GeneralTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

public class StringValidationTest {
    @ExtendWith(MockitoExtension.class)
    private final GeneralTestData td = new GeneralTestData();

    @Test
    @DisplayName("Has Content tests")
    public void SV1_SV2_SV3(){
        assertAll(
                ()-> assertTrue(StringValidation.hasContent(td.VALID_NAME)), //SV1
                ()-> assertFalse(StringValidation.hasContent(null)), //SV2
                ()-> assertFalse(StringValidation.hasContent(td.STRING_WITHOUT_CONTENT)) //SV3
        );
    }

    @Test
    @DisplayName("Is Valid Phone Number Tests")
    public void SV4_SV5_SV6_SV7_SV8(){
        assertAll(
                ()-> assertTrue(StringValidation.isValidPhoneNumber(td.VALID_PHONE_NUMBER)), //SV4
                ()-> assertFalse(StringValidation.isValidPhoneNumber(null)), //SV5
                ()-> assertFalse(StringValidation.isValidPhoneNumber(td.STRING_WITHOUT_CONTENT)), //SV6
                ()-> assertFalse(StringValidation.isValidPhoneNumber(td.PHONE_NUMBER_WITH_INVALID_LATER_CHAR)), //SV7
                ()-> assertFalse(StringValidation.isValidPhoneNumber(td.PHONE_NUMBER_WITH_INVALID_FIRST_CHAR)) //SV8
        );
    }

    @Test
    @DisplayName("Is Valid Email Address Tests")
    public void SV9_SV10_SV11_SV12_SV13(){
        assertAll(
                ()-> assertTrue(StringValidation.isValidEmailAddress(td.VALID_EMAIL_ADDRESS)), //SV9
                ()-> assertFalse(StringValidation.isValidEmailAddress(null)), //SV10
                ()-> assertFalse(StringValidation.isValidEmailAddress(td.STRING_WITHOUT_CONTENT)), //SV11
                ()-> assertFalse(StringValidation.isValidEmailAddress(td.EMAIL_ADDRESS_WITHOUT_AT_SYMBOL)), //SV12
                ()-> assertFalse(StringValidation.isValidEmailAddress(td.EMAIL_ADDRESS_WITHOUT_DOMAIN)) //SV13
        );
    }
}
