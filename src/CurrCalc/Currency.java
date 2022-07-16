package CurrCalc;

import java.math.BigDecimal;

public class Currency {
    
    private String name;
    private BigDecimal price;

    public Currency(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal newPrice){
        this.price = newPrice;
    }
}
