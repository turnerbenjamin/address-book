package com.digitalfuturesacademy.addressbook.view;

import java.util.Scanner;

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
        return scanner.next();
    }

    private void printMessage(String message, String textColor){
        String formattedMessage = textColor + message + ANSI_DEFAULT;
        System.out.println(formattedMessage);
    }
}
