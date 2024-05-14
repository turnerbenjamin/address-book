package com.digitalfuturesacademy.addressbook.controller;

public class AddressBookAppTestData {

    //TEST USER INPUT - GENERAL
    public final String SELECT_EXIT = "e";

    //MENU PROMPTS - SELECT AN OPTION
    public final String FOR_SELECT_FROM_MENU = "Select an option by number or 'e' to exit:";

    ////MENU PROMPTS - ADD CONTACT
    public final String FOR_PROMPT_TO_PROVIDE_NAME_FOR_ADD_CONTACT = "Enter the contact's name:";
    public final String FOR_PROMPT_TO_PROVIDE_PHONE_NUMBER_FOR_ADD_CONTACT = "Enter the contact's phone number:";
    public final String FOR_PROMPT_TO_PROVIDE_EMAIL_ADDRESS_FOR_ADD_CONTACT = "Enter the contact's email address:";

    ////MENU PROMPTS - UPDATE CONTACT
    public final String FOR_PROMPT_TO_PROVIDE_NAME_FOR_UPDATE_CONTACT = "Enter new name, or press enter to keep current name:";
    public final String FOR_PROMPT_TO_PROVIDE_PHONE_NUMBER_FOR_UPDATE_CONTACT = "Enter new phone number, or press enter to keep current phone number:";
    public final String FOR_PROMPT_TO_PROVIDE_EMAIL_ADDRESS_FOR_UPDATE_CONTACT = "Enter new email address, or press enter to keep current email address:";

    //SEARCH TERM PROMPT
    public final String FOR_TYPE_SEARCH_TERM = "Search by name:";

    //TEST USER INPUT - TOP LEVEL

    public final String SELECT_ADD_CONTACT = "1";
    public final String SELECT_READ_ALL_CONTACTS = "2";
    public final String SELECT_SEARCH_CONTACTS = "3";

    //TEST USER INPUT - CONTACTS MENU
    public final String SELECT_CONTACT_1 = "1";
    public final String SELECT_CONTACT_2 = "2";

    //TEST USER INPUT - CONTACT MENU
    public final String SELECT_UPDATE_CONTACT = "1";
    public final String SELECT_DELETE_CONTACT = "2";





    //USER DATA
    public final String USER_1_NAME = "Jane Doe";
    public final String USER_1_PHONE_NUMBER = "07712419278";
    public final String USER_1_EMAIL_ADDRESS = "janedoe98@gmail.com";

    public final String USER_2_NAME = "John Doe";
    public final String USER_2_PHONE_NUMBER = "07715416278";
    public final String USER_2_EMAIL_ADDRESS = "johndoe99@gmail.com";

    //VALID NEW/UPDATED USER DATA
    public final String VALID_NAME = "Fox Mulder";
    public final String VALID_PHONE_NUMBER = "991";
    public final String VALID_EMAIL_ADDRESS = "mulder.fox@fbi.gov";

    //INVALID DATA
    public final String INVALID_EMAIL_ADDRESS = "mulder.fox.fbi.gov";
}
