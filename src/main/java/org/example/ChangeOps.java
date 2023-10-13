package org.example;

import org.w3c.dom.Node;

import static org.example.XmlUtil.asList;

public class ChangeOps {

    public static void changeTagName() {
        // Changes tag name
        for (Node n : asList(Main.doc.getElementsByTagName(Main.tagToChange))) {
            Main.doc.renameNode(n, null, Main.change);
        }
    }

    public static void changeTagContents() {
        // Changes tag content
        Node tagName = Main.doc.getElementsByTagName(Main.tagToChange).item(0);
        tagName.setTextContent(Main.change);
    }

}