package CurrCalc;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Parser {

    public Parser () {}

    public void parse (String filepath){

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(filepath));

            document.getDocumentElement().normalize();
            
            System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
            System.out.println("------");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Sorry, something went wrong.");
            e.printStackTrace();
        }
    }
}