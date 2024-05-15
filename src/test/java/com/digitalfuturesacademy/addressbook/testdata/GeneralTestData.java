package com.digitalfuturesacademy.addressbook.testdata;

public class GeneralTestData {
    //USER DATA
    public final String CONTACT_1_NAME = "Jane Doe";
    public final String CONTACT_1_PHONE_NUMBER = "07712419278";
    public final String CONTACT_1_EMAIL_ADDRESS = "janedoe98@gmail.com";

    public final String CONTACT_2_NAME = "John Doe";
    public final String CONTACT_2_PHONE_NUMBER = "07715416278";
    public final String CONTACT_2_EMAIL_ADDRESS = "johndoe99@gmail.com";

    //SEARCH TERMS
    public final String SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_NAME = "Jane Doe";
    public final String SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_NAME = "Doe";
    public final String SEARCH_TERM_MATCHING_ZERO_CONTACTS_BY_NAME = "X";
    public final String SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_PHONE_NUMBER = "07712";
    public final String SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_PHONE_NUMBER = "0771";
    public final String SEARCH_TERM_MATCHING_ZERO_CONTACTS_BY_PHONE_NUMBER = "0000";
    public final String SEARCH_TERM_MATCHING_CONTACT_1_ONLY_BY_EMAIL_ADDRESS = "doe98";
    public final String SEARCH_TERM_MATCHING_BOTH_CONTACTS_BY_EMAIL_ADDRESS = "gmail";
    public final String SEARCH_TERM_MATCHING_ZERO_CONTACTS_BY_EMAIL_ADDRESS = ".io";


    //VALID NEW/UPDATED USER DATA
    public final String VALID_NAME = "Fox Mulder";
    public final String VALID_PHONE_NUMBER = "991";
    public final String VALID_EMAIL_ADDRESS = "mulder.fox@fbi.gov";

    //ALPHABETICAL SORTING TEST CONTACT
    public final String CONTACT_ALPHABETICALLY_BETWEEN_CONTACT_1_AND_2_NAME = "Jill Doe";

    //INVALID DATA
    public final String PHONE_NUMBER_WITH_INVALID_FIRST_CHAR = "X441780410545";
    public final String PHONE_NUMBER_WITH_INVALID_LATER_CHAR = "+4417804105+5";
    public final String EMAIL_ADDRESS_WITHOUT_AT_SYMBOL = "test_email.com";
    public final String EMAIL_ADDRESS_WITHOUT_DOMAIN = "test.me@email,com";
    public final String STRING_WITHOUT_CONTENT = "    ";

    //TEST MESSAGE
    public final String TEST_MESSAGE = "Hello World!";
}
