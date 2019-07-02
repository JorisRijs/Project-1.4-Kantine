package com.kantine;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class Kassa {
    //kassa rij
    private KassaRij rij;

    //hoeveelheid artikelen verkocht sinds laatste reset
    private int totaalArtikelCount;

    //hoeveelheid geld in de kassa
    private Double totaalWaarde;

    //ANSI kleuren voor prints
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private EntityManager manager;
    private KantineSimulatie KS;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, EntityManager em, KantineSimulatie KS) {
        this.rij = kassarij;
        this.KS = KS;
        totaalWaarde = 0.00;
        manager = em;
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
        LocalDate datum = LocalDate.of(2019, 5, KS.getDag());
        Persoon klantPersoon = klant.getKlant();
        Betaalwijze betaalWijze = klantPersoon.getBetaalwijze();

        //maakt een factuur aan en haal de waarden op
        Factuur factuur = new Factuur(klant, datum);
        double totaal = factuur.getTotaal();
        double korting = factuur.getKorting();

        //Als de klant korting heeft print dit op het scherm
        if(factuur.getKorting() != 0){
            System.out.println(ANSI_CYAN + klantPersoon.getVoornaam() + " " + klantPersoon.getAchternaam() + " heeft "
                    + ((KortingskaartHouder) klantPersoon).getKortingsPercentage() + "% korting en bespaart " + korting + "€" + ANSI_RESET);
        }

        //probeer te betalen, bij TeWeinigGeldException print dat de klant niet genoeg geld heeft om te betalen
        EntityTransaction transaction = null;
        try {
            betaalWijze.betaal(totaal);
            totaalWaarde += totaal;
            totaalArtikelCount += factuur.getArtikelCount();
            System.out.println(klantPersoon.getVoornaam() + " " + klantPersoon.getAchternaam() + " betaalde " + totaal + "€ en heeft nu " + betaalWijze.getSaldo() + "€ over");

            //probeer in de database te zetten
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(factuur);
            for(FactuurRegel regel : factuur.getRegels()){
                manager.persist(regel);
            }
            transaction.commit();
        }
        catch (TeWeinigGeldException e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(ANSI_RED + klantPersoon.getVoornaam() + " " + klantPersoon.getAchternaam() + " had niet genoeg geld om te betalen" + ANSI_RESET);
        }
        catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd,
     * vanaf het moment dat de methode resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return totaalArtikelCount;
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
        totaalArtikelCount = 0;
    }
}