package com.digitalfuturesacademy.addressbook.view;

import com.digitalfuturesacademy.addressbook.model.IImmutableContact;

import java.util.Scanner;
import java.util.SortedMap;

public class ConsoleInterface implements IUserInterface {

    private final Scanner scanner;
    public static final String ANSI_DEFAULT = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public ConsoleInterface(Scanner scanner)
    {
        this.scanner = scanner;
    }

    /**
     * Prints a message to the console with
     * the default text color
     *
     * @param message message to print
     */
    public void printMessage(String message){
        printMessage(message, ANSI_DEFAULT);
    }

    /**
     * Prints a message to the console
     * with red text
     *
     * @param message message to print
     */
    public void printErrorMessage(String message){
        printMessage(message, ANSI_RED);
    }

    /**
     * Prints a message to the console with
     * green text
     *
     * @param message message to print
     */
    public void printSuccessMessage(String message){
        printMessage(message, ANSI_GREEN);
    }

    /**
     * Prints a message to the console with
     * yellow text
     *
     * @param message message to print
     */
    public void printWarningMessage(String message){
        printMessage(message, ANSI_YELLOW);
    }

    /**
     * Get user input from the console
     *
     * @param prompt message to prompt users to input data
     * @return
     */
    public String getUserInput(String prompt){
        printMessage(prompt, ANSI_DEFAULT);
        return scanner.nextLine();
    }

    /**
     * Prints the details of a contact to the console including
     * their name, phone number and email address.
     *
     * @param contact contact to print
     */
    public void printContact(IImmutableContact contact){
        if( contact == null) throw new IllegalArgumentException("contact cannot be null");
        String contactString = String.format(
                "Name:\t\t\t%s\nPhone number:\t%s\nEmail address:\t%s",
                contact.getName(), contact.getPhoneNumber(), contact.getEmailAddress()
        );
        printMessage(contactString);
    }

    /**
     * Prints a menu to the console. The menu should be
     * represented by a SortedMap with the keys being valid user
     * inputs and the values being descriptions for each option.
     *
     * @param menu menu to print
     */
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

    // ************ PRIVATE METHODS ************ \\

    //Prints a message to the console with the text color provided.
    private void printMessage(String message, String AnsiColor){
        String formattedMessage = AnsiColor + message + ANSI_DEFAULT;
        System.out.println(formattedMessage);
    }




}
