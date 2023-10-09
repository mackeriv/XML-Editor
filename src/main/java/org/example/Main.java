package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import static org.example.XmlUtil.asList;

public class Main {

    static Document doc;
    static String tagToChange;
    static String change = null;
    static String filepath;

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

                docBuild();
                changeTagName();
                break;

            case 2:
                System.out.println("Enter the text to replace the contents of the tag: ");
                change = in.nextLine();

                docBuild();
                changeTagContents();
                break;

            default:
                System.out.println("error");

        }

        try {

            // Overwrites the XML file with the changes applied
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Done");

        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static boolean docBuild() {

        // Prepare a XML document for editing
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(filepath);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private static boolean changeTagName() {
        // Changes tag name
        for (Node n : asList(doc.getElementsByTagName(tagToChange))) {
            doc.renameNode(n, null, change);
        }
        return true;
    }

    private static boolean changeTagContents() {
        // Changes tag content
        Node startdate = doc.getElementsByTagName(tagToChange).item(0);
        startdate.setTextContent(change);

        return true;
    }



}