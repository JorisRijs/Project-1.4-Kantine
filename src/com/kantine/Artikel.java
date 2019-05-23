package src.com.kantine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Artikel {
    private String naam;
    private BigDecimal prijs;

    public Artikel(){
        naam = "";
        prijs = new BigDecimal(0.00);
    }

    public Artikel(String naam, BigDecimal prijs) {
        this();
        this.naam = naam;
        this.prijs = prijs.setScale(2, RoundingMode.HALF_UP);
    }

    public String getNaam(){
        return naam;
    }

    public void setNaam(String naam){
        this.naam = naam;
    }

    public BigDecimal getPrijs(){
        return prijs;
    }

    public void setPrijs(BigDecimal prijs){
        this.prijs = prijs;
    }

    public String toString(){
        return naam + " " + prijs;
    }
}
