package src.com.kantine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Artikel {
    private String naam;
    private double prijs;

    public Artikel(){
        naam = "";
        prijs = 0.00;
    }

    public Artikel(String naam, double prijs) {
        this();
        this.naam = naam;
        this.prijs = prijs;
    }

    public String getNaam(){
        return naam;
    }

    public void setNaam(String naam){
        this.naam = naam;
    }

    public double getPrijs(){
        return prijs;
    }

    public void setPrijs(double prijs){
        this.prijs = prijs;
    }

    public String toString(){
        return naam + " " + prijs;
    }
}
