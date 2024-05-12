package com.digitalfuturesacademy.addressbook.view;

import java.util.Scanner;

public class ConsoleInterface implements IConsoleInterface {

    private Scanner scanner;

    public ConsoleInterface(Scanner scanner){
        this.scanner = scanner;
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public String getUserInput(String prompt){
        System.out.println(prompt);
        return scanner.next();
    }
}
