package CurrCalc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;

public class ParserTest {

    static final String FILEPATH = "resources/";
    private Path workingDir;

    // @Rule
    // public ResourceRule<String> textResource = givenResource()
    //         .text("/com/adelean/junit/jupiter/resource.txt")
    //         .withCharset(StandardCharsets.UTF_8);

    @Before
    public void init() {
        this.workingDir = Paths.get("", FILEPATH);
    }
    
    @Test
    public void checkContents(){
        Parser parser = new Parser();
        Path input = this.workingDir.resolve("prices.xml");
        String file = Thread.currentThread().getContextClassLoader().getResource("resources/prices.xml").getFile();
        String xmlString = null;
        try {
            xmlString = parser.getResourceFileAsString(file);
        } catch (IOException e){
            e.printStackTrace();
        }

        ArrayList<Currency> list = parser.parse(xmlString);
        for (Currency curr : list){
            System.out.println(curr.getName());
            System.out.println(curr.getPrice());
        }
    }
}
