package com.kantine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

public class Kassa {
    //kassa rij
    private KassaRij rij;

    //hoeveelheid artikelen verkocht sinds laatste reset
    private int artikelCount;

    //hoeveelheid geld in de kassa
    private Double totaalWaarde;

    //ANSI kleuren voor prints
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        this.rij = kassarij;
        totaalWaarde = 0.00;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op.
     * Tel deze gegevens op bij de controletotalen die voor
     * de kassa worden bijgehouden. De implementatie wordt
     * later vervangen door een echte betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Iterator<Artikel> artikelen = klant.getArtikelen();
        Persoon klantPersoon = klant.getKlant();
        Betaalwijze betaalWijze = klantPersoon.getBetaalwijze();
        double totaalPrijs = 0;
        //hoeveelheid artikelen op dienblad
        int klantArtikelCount = 0;

        //bereken totaalprijs
        while (artikelen.hasNext()){
            totaalPrijs += artikelen.next().getPrijs();
            klantArtikelCount++;
        }

        //als de klant een kortingskaart heeft bereken de nieuwe prijs
        if(klantPersoon instanceof KortingskaartHouder){
            double korting;
            korting = totaalPrijs * (((KortingskaartHouder) klantPersoon).getKortingsPercentage() / 100);
            if(((KortingskaartHouder) klantPersoon).hasMaximum() && korting > ((KortingskaartHouder) klantPersoon).getMaximum()){
                korting = ((KortingskaartHouder) klantPersoon).getMaximum();
            }
            totaalPrijs -= korting;

            //tijdelijke print om te testen
            System.out.println(ANSI_CYAN + klantPersoon.getVoornaam() + " " + klantPersoon.getAchternaam() + " heeft "
                    + ((KortingskaartHouder) klantPersoon).getKortingsPercentage() + "% korting en bespaart " + korting + "€" + ANSI_RESET);
        }

        //probeer te betalen, zoniet gooi een exception die in KantineSimulatie wordt opgevangen
        try {
            betaalWijze.betaal(totaalPrijs);
            totaalWaarde += totaalPrijs;
            artikelCount += klantArtikelCount;
            //tijdelijke prints om te testen
            System.out.println(klantPersoon.getVoornaam() + " " + klantPersoon.getAchternaam() + " betaalde " + totaalPrijs + "€ en heeft nu " + betaalWijze.getSaldo() + "€ over");
        }
        catch (TeWeinigGeldException e){
            System.out.println(ANSI_RED + klantPersoon.getVoornaam() + " " + klantPersoon.getAchternaam() + " had niet genoeg geld om te betalen" + ANSI_RESET);
        }
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd,
     * vanaf het moment dat de methode resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return artikelCount;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass
     * zijn gepasseerd, vanaf het moment dat de methode
     * resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public Double hoeveelheidGeldInKassa() {
        return totaalWaarde;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en
     * de totale hoeveelheid geld in de kassa.
     */
    public void resetKassa() {
        totaalWaarde = 0.00;
        artikelCount = 0;
    }
}