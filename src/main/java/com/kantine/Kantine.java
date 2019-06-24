package com.kantine;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Kantine {
    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineaanbod;

    private EntityManager manager;

    String[] artikels = new String[] {"appel", "babaan", "burger", "Patat", "Pindakaas"};
    Double[] prijzen = {2.99, 1.99, 10.99, 15.99, 14.99};
    int[] aantalen = {5, 13, 6, 2, 50};

    /**
     * Constructor
     */
    public Kantine(EntityManager em) {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij, em);
        manager = em;
        kantineaanbod = new KantineAanbod(artikels, prijzen, aantalen);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt
     * en aan elkaar gekoppeld. Maak twee Artikelen aan
     * en plaats deze op het dienblad. Tenslotte sluit de
     * Persoon zich aan bij de rij voor de kassa.
     */
 /*   public void loopPakSluitAan() {
        Persoon persoon = new Persoon();
        Dienblad dienblad = new Dienblad(persoon);
        Artikel artikel = new Artikel("Appel", 4.99);
        dienblad.voegToe(artikel);
        Artikel artikel2 = new Artikel("Banaan", 9.99);
        dienblad.voegToe(artikel2);
        kassarij.sluitAchteraan(dienblad);
    }
*/
    /**
     * In deze methode kiest een Persoon met een dienblad
     * de artikelen in artikelnamen.
     *
     * @param dienblad
     * @param artikelnamen
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen){
        for(int i = 0; i < artikelnamen.length; i++){
            Artikel artikel = kantineaanbod.getArtikel(artikelnamen[i]);
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
    public void setKantineAanbod(KantineAanbod kantineaanbod) {
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
