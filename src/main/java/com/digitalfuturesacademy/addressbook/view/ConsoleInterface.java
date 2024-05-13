package com.digitalfuturesacademy.addressbook.view;

import com.digitalfuturesacademy.addressbook.model.IImmutableContact;

import java.util.Scanner;
import java.util.SortedMap;

public class ConsoleInterface implements IUserInterface {

    private Scanner scanner;
    public static final String ANSI_DEFAULT = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public ConsoleInterface(Scanner scanner){
        this.scanner = scanner;
    }

    public void printMessage(String message){
        printMessage(message, ANSI_DEFAULT);
    }

    public void printErrorMessage(String message){
        printMessage(message, ANSI_RED);
    }

    public void printSuccessMessage(String message){
        printMessage(message, ANSI_GREEN);
    }

    public void printWarningMessage(String message){
        printMessage(message, ANSI_YELLOW);
    }

    public String getUserInput(String prompt){
        printMessage(prompt, ANSI_DEFAULT);
        return scanner.nextLine();
    }

    public void printContact(IImmutableContact contact){
        if( contact == null) throw new IllegalArgumentException("contact cannot be null");
        String contactString = String.format(
                "Name:\t\t\t%s\nPhone number:\t%s\nEmail address:\t%s",
                contact.getName(), contact.getPhoneNumber(), contact.getEmailAddress()
        );
        printMessage(contactString);
    }

    //Calls print message on userInterface with the passed menu as a String.
    //Menu should be a SortedMap with the keys being valid user inputs
    //and the values being descriptions for each option.
    public void printMenu(SortedMap<String, String> menu){
        if(menu == null || menu.isEmpty()) throw new IllegalArgumentException("Menu cannot be null or empty");
        StringBuilder menuString = new StringBuilder();
        menuString.append("\n---------------------------\n");
        menu.forEach((key, value) -> menuString
                .append(key)
                .append(":\t")
                .append(value)
                .append("\n"));
        printMessage(menuString.toString());
    }

    private void printMessage(String message, String textColor){
        String formattedMessage = textColor + message + ANSI_DEFAULT;
        System.out.println(formattedMessage);
    }




}
