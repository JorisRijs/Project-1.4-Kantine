package src.com.kantine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class Dienblad {
    private ArrayList<Artikel> artikelen;
    private Persoon klant;

    /**
     * Constructor
     */
    public Dienblad() {
        artikelen = new ArrayList<>();
    }

    public Dienblad(Persoon klant) {
        this();
        this.klant = klant;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.add(artikel);
    }

    public Persoon getKlant() {
        return klant;
    }

    public void setKlant(Persoon klant) {
        this.klant = klant;
    }

    public Iterator getArtikelen(){
        Iterator<Artikel> artikelIterator = artikelen.iterator();
        return artikelIterator;
    }
}

