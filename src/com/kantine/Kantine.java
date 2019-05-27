package src.com.kantine;

import java.math.BigDecimal;

public class Kantine {
    private Kassa kassa;
    private KassaRij kassarij;

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
        Artikel artikel = new Artikel("Appel", new BigDecimal(4.99));
        dienblad.voegToe(artikel);
        Artikel artikel2 = new Artikel("Banaan", new BigDecimal(9.99));
        dienblad.voegToe(artikel2);
        kassarij.sluitAchteraan(dienblad);
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
