package src.com.kantine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

public class Kassa {
    private KassaRij rij;
    private int artikelCount;
    private Double totaalWaarde;
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

        while (artikelen.hasNext()){
            totaalPrijs += artikelen.next().getPrijs();
        }

        if(klantPersoon instanceof KortingskaartHouder){
            double korting;
            korting = totaalPrijs * (((KortingskaartHouder) klantPersoon).getKortingsPercentage() / 100);
            if(((KortingskaartHouder) klantPersoon).hasMaximum() && korting > ((KortingskaartHouder) klantPersoon).getMaximum()){
                korting = ((KortingskaartHouder) klantPersoon).getMaximum();
            }
            totaalPrijs -= korting;

            //tijdelijke print om te testen
            System.out.println(klantPersoon.getVoornaam() + " heeft " + ((KortingskaartHouder) klantPersoon).getKortingsPercentage() + "% korting en bespaart " + korting + " euro.");
        }

        if(betaalWijze.betaal(totaalPrijs)){
            totaalWaarde += totaalPrijs;
            artikelCount++;
            //tijdelijke prints om te testen
            System.out.println(klantPersoon.getVoornaam() + " betaalde " + totaalPrijs + " en heeft nu " + betaalWijze.getSaldo()  + " euro over");
        } else {
            System.out.println(klantPersoon.getVoornaam() + " had niet genoeg geld om te betalen");
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