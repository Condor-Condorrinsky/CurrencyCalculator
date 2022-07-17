package CurrCalc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

public class MainTest {

    static final String FILEPATH = "test_resources/prices.xml";
    static final int NR_OF_ENTRIES = 31;
    static final String[] TEST_CURRENCIES = {"pln", "jpy", "chf", "czk", "DKK", "Usd", "aUD", "gBp"};
    static final String[] MALFORMED_CURR = {"płn", "U5D", "randomString"};

    @Test
    public void testReadDatabase(){

        ArrayList<Currency> testList = Main.readDatabase();

        assertFalse(testList.isEmpty());
        assertTrue(testList.size() == NR_OF_ENTRIES);

        for (Currency curr : testList){
            assertNotNull(curr);
            assertNotNull(curr.getName());
            assertNotNull(curr.getPrice());
        }
    }

    @Test
    public void testFindCurrency(){

        ArrayList<Currency> testList = Main.readDatabase();

        for (String str : TEST_CURRENCIES){
            Currency test = Main.findCurrency(str, testList);
            assertNotNull(test);
            assertNotNull(test.getName());
            assertNotNull(test.getPrice());
        }

        for (String str : MALFORMED_CURR) {
            Currency test = Main.findCurrency(str, testList);
            assertNull(test);
        }
    }

    @Test
    public void testCalculateValue(){
        BigDecimal money = new BigDecimal("500.00");
        Currency currency = new Currency("someName", new BigDecimal("4.25"));
        BigDecimal expected = new BigDecimal("2125.00");
        assertTrue(Main.calculateValue(money, currency).compareTo(expected) == 0);
    }
}
