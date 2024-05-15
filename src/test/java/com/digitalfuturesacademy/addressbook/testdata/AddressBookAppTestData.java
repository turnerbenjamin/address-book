package com.digitalfuturesacademy.addressbook.testdata;

public class AddressBookAppTestData extends GeneralTestData{

    //TEST USER INPUT - GENERAL
    public final String SELECT_EXIT = "e";

    //MENU PROMPTS - SELECT AN OPTION
    public final String FOR_SELECT_FROM_MENU = "Select an option by number or 'e' to exit:";

    //MENU PROMPTS - ADD CONTACT
    public final String FOR_PROMPT_TO_PROVIDE_NAME_FOR_ADD_CONTACT = "Enter the contact's name:";
    public final String FOR_PROMPT_TO_PROVIDE_PHONE_NUMBER_FOR_ADD_CONTACT = "Enter the contact's phone number:";
    public final String FOR_PROMPT_TO_PROVIDE_EMAIL_ADDRESS_FOR_ADD_CONTACT = "Enter the contact's email address:";

    //MENU PROMPTS - UPDATE CONTACT
    public final String FOR_PROMPT_TO_PROVIDE_NAME_FOR_UPDATE_CONTACT = "Enter new name, or press enter to keep current name:";
    public final String FOR_PROMPT_TO_PROVIDE_PHONE_NUMBER_FOR_UPDATE_CONTACT = "Enter new phone number, or press enter to keep current phone number:";
    public final String FOR_PROMPT_TO_PROVIDE_EMAIL_ADDRESS_FOR_UPDATE_CONTACT = "Enter new email address, or press enter to keep current email address:";

    //DELETE ALL CONTACTS PROMPT
    public final String FOR_PROMPT_TO_CONFIRM_DELETE_ALL_CONTACTS = "Are you sure you want to delete all contacts? Type either \"YES\" or \"NO\"";

    //SEARCH TERM PROMPT
    public final String FOR_TYPE_SEARCH_TERM = "Search by name:";

    //TEST USER INPUT - TOP LEVEL
    public final String SELECT_ADD_CONTACT = "1";
    public final String SELECT_READ_ALL_CONTACTS = "2";
    public final String SELECT_SEARCH_CONTACTS = "3";
    public final String SELECT_DELETE_ALL_CONTACTS = "4";

    //TEST USER INPUT - CONTACTS MENU
    public final String SELECT_CONTACT_1 = "1";

    //TEST USER INPUT - CONTACT MENU
    public final String SELECT_UPDATE_CONTACT = "1";
    public final String SELECT_DELETE_CONTACT = "2";

    //TEST USER INPUT - DELETE ALL CONTACTS
    public final String SELECT_CONFIRM_DELETE_ALL_CONTACTS = "Yes";
    public final String SELECT_CANCEL_DELETE_ALL_CONTACTS = "No";
    public final String SELECT_INVALID_RESPONSE_FOR_CONFIRMATION_TO_DELETE_ALL_CONTACTS = "x";




}
