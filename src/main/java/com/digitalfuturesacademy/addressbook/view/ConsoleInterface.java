package com.digitalfuturesacademy.addressbook.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInterface implements IConsoleInterface {

    private Scanner scanner;

    private static Map<String, String> consoleColors = new HashMap<>();
    static{
        consoleColors.put("DEFAULT", "\u001B[0m");
        consoleColors.put("RED", "\u001B[31m");
        consoleColors.put("GREEN", "\u001B[32m");
    }



    public ConsoleInterface(Scanner scanner){
        this.scanner = scanner;
    }

    public void printMessage(String message){
        printMessage(message, consoleColors.get("DEFAULT"));
    }

    public void printErrorMessage(String message){
        printMessage(message, consoleColors.get("RED"));
    }

    public String getUserInput(String prompt){
        printMessage(prompt, consoleColors.get("DEFAULT"));
        return scanner.next();
    }

    private void printMessage(String message, String textColor){
        String formattedMessage = textColor + message + consoleColors.get("DEFAULT");
        System.out.println(formattedMessage);
    }
}
