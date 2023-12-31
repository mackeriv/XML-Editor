package org.example;

import java.util.Scanner;
import org.w3c.dom.Document;

public class Main {

    static Document doc;
    static String tagToChange;
    static String change;
    public static String filepath;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String optQuit;
        int option;

        System.out.println("Please provide the filepath for the XML file to be edited or enter \"Q\" to quit: ");

        filepath = in.nextLine();

        while (true) {

            //quits the application
            if (filepath.equalsIgnoreCase("Q")) {
                System.out.println("\nGoodbye!");
                break;
            }

            System.out.println("\nChoose \"1\" to change tag names or \"2\" to change tag contents: ");

            optQuit = in.nextLine();

            //quits the application
            if (optQuit.equalsIgnoreCase("Q")) {
                System.out.println("\nGoodbye!");
                break;
            }

            // "input.nextInt()" can't be used in this context, so this is a workaround for parsing the input to int
            // workaround: https://stackoverflow.com/a/13102066/22175138
            try {
                option = Integer.parseInt(optQuit);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input.");
                continue;
            }

            if (option != 1 && option != 2) {
                System.out.println("\nInvalid input.");
                continue;
            }

            System.out.println("\nEnter the name of the tag to be edited: ");

            tagToChange = in.nextLine();

            //quits the application
            if (tagToChange.equalsIgnoreCase("Q")) {
                System.out.println("\nGoodbye!");
                break;
            }

            switch (option) {

                case 1:
                    System.out.println("\nEnter a new name for the tag: ");
                    change = in.nextLine();

                    if (change.equalsIgnoreCase("Q")) {
                        System.out.println("\nGoodbye!");
                        break;
                    }

                    FileOps.buildDocument();
                    ChangeOps.changeTagName();
                    FileOps.fileWriter();
                    continue;

                case 2:
                    System.out.println("\nEnter the text to replace the contents of the tag: ");
                    change = in.nextLine();

                    if (change.equalsIgnoreCase("Q")) {
                        System.out.println("\nGoodbye!");
                        break;
                    }

                    FileOps.buildDocument();
                    ChangeOps.changeTagContents();
                    FileOps.fileWriter();
            }
        }
    }
}