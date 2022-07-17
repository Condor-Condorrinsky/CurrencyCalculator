package CurrCalc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class ParserTest {

    static final String FILEPATH = "test_resources/prices.xml";
    // zawiera 3 elementy
    static final String EXAMPLE_DATA = "<gesmes:Envelope><gesmes:subject>Reference rates</gesmes:subject><gesmes:Sender><gesmes:name>European Central Bank</gesmes:name></gesmes:Sender><Cube><Cube time=\"2022-07-14\"><Cube currency=\"USD\" rate=\"1.0005\"/><Cube currency=\"JPY\" rate=\"139.04\"/><Cube currency=\"GBP\" rate=\"0.84560\"/></Cube></Cube></gesmes:Envelope>";
    static final int NR_OF_ENTRIES = 3;
    
    @Test
    public void testParse(){

        Parser parser = new Parser();
        ArrayList<Currency> testList = parser.parse(EXAMPLE_DATA);
        Currency dollars = new Currency("USD", new BigDecimal("1.0005"));
        Currency yens = new Currency("JPY", new BigDecimal("139.04"));
        Currency pounds = new Currency("GBP", new BigDecimal("0.84560"));

        assertFalse(testList.isEmpty());
        assertTrue(testList.size() == NR_OF_ENTRIES);
        
        assertTrue(testList.stream().anyMatch(c -> Objects.equals(c.getName(), dollars.getName())));
        assertTrue(testList.stream().anyMatch(c -> Objects.equals(c.getName(), yens.getName())));
        assertTrue(testList.stream().anyMatch(c -> Objects.equals(c.getName(), pounds.getName())));
    }

    @Test
    @SuppressWarnings("unused")
    public void testGetResourceFileAsString() throws Exception{
        
        Parser parser = new Parser();
        String data = null;

        data = parser.getResourceFileAsString(FILEPATH);

        assertNotNull(data);
        assertFalse(data.isEmpty());
            
        //test whether DocumentBuilder can parse the string or throws any exception
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(data));
        Document doc = dBuilder.parse(is);
    }
}
