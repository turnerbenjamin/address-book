package com.digitalfuturesacademy.addressbook.view;

public interface IUserInterface
{

     String getUserInput(String prompt);

     void printMessage(String message);

     void printErrorMessage(String message);

     void printSuccessMessage(String message);

     void printWarningMessage(String message);
}
