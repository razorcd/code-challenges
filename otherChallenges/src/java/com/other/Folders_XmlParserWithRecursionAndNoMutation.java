package com.other;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Folders_XmlParserWithRecursionAndNoMutation {

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                        "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                        "<folder name=\"install information\" />" +
                        "</folder>" +
                        "<folder name=\"users\" />" +
                        "<folder name=\"system\" />" +
                        "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }

    /**
     * Parses a string containing XML and searches all elements <folder name="somename"></folder>
     * that have name starting with char `startingLetter`
     *
     * @param xml the xml string
     * @param startingLetter the character to search by
     * @return [{@code Collection<String>}] collection of folders starting with `startingLetter`
     * @throws Exception if XML parsing fails
     */
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
        Document doc = parseXml(xml);
        return recursiveParsingWithoutMutation(doc.getFirstChild(), startingLetter);
    }

    /**
     * Parses recursively the XML DOM tree starting from Node and returns folders that start with `startingLetter`
     *
     * @param node the starting Node
     * @param startingLetter the starting letter to search by
     */
    private static Collection<String> recursiveParsingWithoutMutation(Node node, char startingLetter) {
        Collection<String> response = new ArrayList<>();     // initializes response collection for current method call

        String folder = node.getAttributes().getNamedItem("name").getNodeValue(); // read node attribute
        if (folder.charAt(0) == startingLetter) response.add(folder);  // filter

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Collection<String> result = recursiveParsingWithoutMutation(nodeList.item(i), startingLetter);  // recursive call
            response = Stream.concat(response.stream(), result.stream()).collect(Collectors.toList());     // builds up response collection from recursion response
        }
        return response;
    }

    /**
     * Parses xml string and returns route node that has the tree
     *
     * @param xml the xml as string
     * @return [Document] the root node
     */
    private static Document parseXml(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return db.parse(new InputSource(new StringReader(xml)));
    }
}
