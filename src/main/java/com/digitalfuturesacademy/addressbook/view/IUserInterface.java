package com.digitalfuturesacademy.addressbook.view;

import com.digitalfuturesacademy.addressbook.model.IImmutableContact;

public interface IUserInterface
{

     String getUserInput(String prompt);

     void printMessage(String message);

     void printErrorMessage(String message);

     void printSuccessMessage(String message);

     void printWarningMessage(String message);

     void printContact(IImmutableContact contact);
}
