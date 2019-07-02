package com.kantine;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
@Table(name = "factuur")
public class Factuur implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long Id;

    @Column(name = "factuur_datum", nullable = false)
    private LocalDate datum;

    @Column(name = "factuur_korting", nullable = false)
    private double korting;

    @Column(name = "factuur_totaal", nullable = false)
    private double totaal;

    @Column(name = "factuur_artikel_count", nullable = false)
    private int artikelCount;

    @Transient
    private ArrayList<FactuurRegel> regels = new ArrayList<>();

    public Factuur(){
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum){
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Bepaal totaalprijs en korting
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad klant){
        Iterator<Artikel> artikelen = klant.getArtikelen();
        Persoon klantPersoon = klant.getKlant();
        artikelCount = 0;

        //bepaal totaalprijs zonder korting
        while (artikelen.hasNext()){
            Artikel artikel = artikelen.next();
            regels.add(new FactuurRegel(this, artikel));
            totaal += artikel.getPrijs();
            artikelCount++;
        }

        //bepaal korting en haal het van de totaalprijs af
        if(klantPersoon instanceof KortingskaartHouder){
            korting = totaal * (((KortingskaartHouder) klantPersoon).getKortingsPercentage() / 100);
            if(((KortingskaartHouder) klantPersoon).hasMaximum() && korting > ((KortingskaartHouder) klantPersoon).getMaximum()){
                korting = ((KortingskaartHouder) klantPersoon).getMaximum();
            }
            totaal -= korting;
        }
    }

    /**
     * @return de toegepaste korting
     */
    public double getKorting() {
        return korting;
    }

    /**
     * @return het totaalbedrag
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     * @return hoeveelheid artikelen in factuur
     */
    public int getArtikelCount() {
        return artikelCount;
    }

    /**
     * @return alle factuurregels
     */
    public ArrayList<FactuurRegel> getRegels() {
        return regels;
    }

    /**
     * @return een printbare versie van de factuur
     */
    public String toString(){
        String stringRegels = "";
        for(FactuurRegel regel : regels){
            stringRegels += regel.toString();
            stringRegels += "\n|";
        }
        return "Id: " + Id + " datum: " + datum + " totaal: " + totaal + " korting: " + korting + " hoeveelheid artikelen: " + artikelCount
                + "\n|" + stringRegels;
    }
}
