package src.com.kantine;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Kantine {
    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineaanbod;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt
     * en aan elkaar gekoppeld. Maak twee Artikelen aan
     * en plaats deze op het dienblad. Tenslotte sluit de
     * Persoon zich aan bij de rij voor de kassa.
     */
    public void loopPakSluitAan() {
        Persoon persoon = new Persoon();
        Dienblad dienblad = new Dienblad(persoon);
        Artikel artikel = new Artikel("Appel", 4.99);
        dienblad.voegToe(artikel);
        Artikel artikel2 = new Artikel("Banaan", 9.99);
        dienblad.voegToe(artikel2);
        kassarij.sluitAchteraan(dienblad);
    }

    /**
     * In deze methode kiest een Persoon met een dienblad
     * de artikelen in artikelnamen.
     *
     * @param persoon
     * @param artikelnamen
     */
    public void loopPakSluitAan(Persoon persoon, String[] artikelnamen){

        Dienblad dienblad  = new Dienblad(persoon);
        for(int i = 0; i < artikelnamen.length; i++){
            String temp = artikelnamen[i];

            Artikel artikel = kantineaanbod.getArtikel(temp);
            dienblad.voegToe(artikel);
        }
        kassarij.sluitAchteraan(dienblad);

    }

    /**
     * Deze methode retourneerd het kantineaanbood van een kantine als een object.
     * @return KantineAanbod
     */

    public KantineAanbod getKantineaanbod() {
        return kantineaanbod;
    }

    /**
     * Deze methode set het kantine aanbod van een kantine op basis van een kantineaanbod object.
     * @param kantineaanbod
     */
    public void setKantineaanbod(KantineAanbod kantineaanbod) {
        this.kantineaanbod = kantineaanbod;
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            kassa.rekenAf(kassarij.eerstePersoonInRij());
        }
    }

    /**
     * Returned de kassa.
     * @return Kassa
     */
    public Kassa getKassa() {
        return kassa;
    }
}
