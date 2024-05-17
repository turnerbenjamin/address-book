package com.digitalfuturesacademy.addressbook.utils;

public enum UserPrompt{
    FOR_SEARCH_TERM("Search by name, phone number or email address:\t"),
    FOR_NEW_CONTACT_NAME("Enter the contact's name:\t"),
    FOR_NEW_CONTACT_PHONE_NUMBER("Enter the contact's phone number:\t"),
    FOR_NEW_CONTACT_EMAIL_ADDRESS("Enter the contact's email address:\t"),
    FOR_UPDATE_CONTACT_NAME("Enter new name, or press enter to keep current name:\t"),
    FOR_UPDATE_CONTACT_PHONE_NUMBER("Enter new phone number, or press enter to keep current phone number:\t"),
    FOR_UPDATE_CONTACT_EMAIL_ADDRESS("Enter new email address, or press enter to keep current email address:\t"),
    FOR_SELECT_FROM_MENU("Select an option by number or 'e' to exit:\t"),
    FOR_CONFIRM_DELETE_ALL_CONTACTS("Are you sure you want to delete all contacts? Type either \"YES\" or \"NO\":\t");

    public final String label;

    UserPrompt(String label) {
        this.label = label;
    }
}
