package src.com.kantine;

import java.math.BigDecimal;

public class Artikel {
    private String naam;
    private BigDecimal prijs;

    public Artikel(){
        this.naam = "";
        this.prijs = new BigDecimal(0.0);
    }

    public Artikel(String naam, BigDecimal prijs) {
        this.naam = naam;
        this.prijs = prijs;
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
}
