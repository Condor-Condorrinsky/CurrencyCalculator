package CurrCalc;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Klasa odpowiedzialna za parsowanie plików xml
 */
public class Parser {

    public static final String TAG_NAME = "Cube";
    public static final String ATTRB_CURRENCY = "currency";
    public static final String ATTRB_RATE = "rate";

    /**
     * Constructor
     */
    public Parser () {}

    /**
     * A method to parse given string containing data from 
     * an xml file.
     * @param   xmlString   string with contents of an xml file
     * @return  a list with read Currency objects
     */
    public ArrayList<Currency> parse (String xmlString){

        ArrayList<Currency> currencies = new ArrayList<Currency>();

        try {
            Document document = loadXMLFromString(xmlString);
            document.getDocumentElement().normalize();
            NodeList list = document.getElementsByTagName(TAG_NAME);

            currencies.ensureCapacity(list.getLength());

            for (int i = 0; i < list.getLength(); i++){

                Node node = list.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE){

                    Element element = (Element) node;
                    String currency = element.getAttribute(ATTRB_CURRENCY);
                    String price = element.getAttribute(ATTRB_RATE);

                    if(currency == null || currency.trim().isEmpty() ||
                    price == null || price.trim().isEmpty()) continue;

                    currencies.add(new Currency(currency, new BigDecimal(price)));
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("Sorry, something went wrong.");
            e.printStackTrace();
        }

        return currencies;
    }

    /**
     * A helper method to properly build a document from a string 
     * with data from an xml.
     * @param   xml         string with xml data
     * @return  a wrc Document object with parsed data
     */
    private Document loadXMLFromString(String xml)
    throws ParserConfigurationException, IOException, SAXException, IllegalArgumentException{

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    /**
     * Reads contents of a given file as a resource and 
     * converts it to String.
     * @param   fileName    a path to a file to be parsed
     * @return  String with data from a file
     * @throws  IOException When InputStream, InputStreamReader or 
     *                      BufferedReader encounters any problem
     */
    public String getResourceFileAsString(String fileName) throws IOException {
        
        try (InputStream is = this.getClass().getResourceAsStream(fileName))  {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}