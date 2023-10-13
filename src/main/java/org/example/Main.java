package org.example;

import java.util.Scanner;
import org.w3c.dom.Document;

public class Main {

    static Document doc;
    static String tagToChange;
    static String change = null;
    public static String filepath;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int option = 0;

        System.out.println("Please provide the filepath for the XML file to be edited or enter \"Q\" to quit: ");

        filepath = in.nextLine();

        System.out.println("Choose \"1\" to change tag names or \"2\" to change tag contents: ");

        try {
            option = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println("Enter the name of the tag to be edited: ");

        tagToChange = in.nextLine();

        switch (option) {

            case 1:
                System.out.println("Enter a new name for the tag: ");
                change = in.nextLine();

                FileOps.docBuild();
                ChangeOps.changeTagName();
                FileOps.fileWriter();
                break;

            case 2:
                System.out.println("Enter the text to replace the contents of the tag: ");
                change = in.nextLine();

                FileOps.docBuild();
                ChangeOps.changeTagContents();
                FileOps.fileWriter();
                break;

            default:
                System.out.println("ERROR");
                break;

        }
    }
}