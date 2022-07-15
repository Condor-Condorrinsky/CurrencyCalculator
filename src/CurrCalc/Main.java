package CurrCalc;

public class Main {
    public static void main(String[] args) {
        String path = "../../resources/prices.xml";
        Parser parser = new Parser();
        parser.parse(path);
    }
}