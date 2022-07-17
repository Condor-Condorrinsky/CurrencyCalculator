package CurrCalc;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static final String DATABASE_PATH = "resources/prices.xml";
    public static final int DECIMAL_PLACES = 2;

    // args[0] - kwota w EUR, args[1] - waluta
    public static void main(String[] args) {
        
        if (args.length != 2){
            System.err.println("Wrong number of arguments, quitting");
        }


        ArrayList<Currency> database = readDatabase();
        Currency chosen = findCurrency(args[1], database);
        BigDecimal result = calculateValue(new BigDecimal(args[0]), chosen);

        System.out.printf("Given sum: %s EUR\n", args[0]);
        System.out.printf("Exchange rate: %s\n", chosen.getPrice());
        System.out.printf("Calculated sum after exchange: %s %s\n", result, chosen.getName());
    }

    public static ArrayList<Currency> readDatabase(){

        Parser parser = new Parser();
        String data = null;

        try {
            data = parser.getResourceFileAsString(DATABASE_PATH);
        } catch (IOException ioException){
            System.err.println("Couldn't find database");
            ioException.printStackTrace();
        }

        return parser.parse(data);
    }

    public static Currency findCurrency(String chosen, ArrayList<Currency> prices){

        String chosenUppercase = chosen.toUpperCase();
        Currency result = new Currency(null, null);

        for (Currency curr : prices){
            if (Objects.equals(chosenUppercase, curr.getName())) {
                result = curr;
                break;
            }
        }

        if (result.getName() == null) {
            System.err.println("The given currency is not present in the database");
            return null;
        }

        return result;
    }

    public static BigDecimal calculateValue (BigDecimal money, Currency curr){

        return money.multiply(curr.getPrice()).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
    }
}