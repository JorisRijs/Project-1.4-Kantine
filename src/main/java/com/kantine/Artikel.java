package com.kantine;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Artikel implements Serializable {
    private String naam;
    private Double prijs;

    public Artikel(){
        naam = "";
        prijs = 0.00;
    }

    public Artikel(String naam, Double prijs) {
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

    public Double getPrijs(){
        return prijs;
    }

    public void setPrijs(Double prijs){
        this.prijs = prijs;
    }



    /**
     * Methode om de waarden van de klasse artikel te krijgen als string
     * @return string
     */
    public String toString(){
        return naam + " " + prijs;
    }
}
