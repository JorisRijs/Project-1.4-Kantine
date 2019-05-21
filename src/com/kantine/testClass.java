package src.com.kantine;

import java.math.BigDecimal;

public class testClass {
    public static void main(String[] args){
        Datum datum = new Datum(5, 5, 2018);
        System.out.println(datum.getDatumAsString());

        Persoon persoon = new Persoon(5, "Henk", "Tattje", datum, 'M');
        System.out.println(persoon.toString());

        Artikel artikel1 = new Artikel("appel", 2.99);
        Artikel artikel2 = new Artikel("een banaan", 9.99);
        Artikel artikel3 = new Artikel("artikel", 1.50);
        Artikel artikel4 = new Artikel("voedsel", 0.69);
        System.out.println(artikel.toString());

        Dienblad dienblad = new Dienblad(persoon);
        dienblad.voegToe(artikel1);
        dienblad.voegToe(artikel2);
        dienblad.voegToe(artikel3);
        dienblad.voegToe(artikel4);
        System.out.println(dienblad.getAantalArtikelen() + " : " + dienblad.getTotaalPrijs());

        Dienblad dienblad2 = new Dienblad(persoon);
        dienblad2.voegToe(artikel2);

        KassaRij rij = new KassaRij();
        rij.sluitAchteraan(dienblad);
        rij.sluitAchteraan(dienblad2);

        System.out.println(rij.eerstePersoonInRij().getTotaalPrijs());
        //System.out.println(rij.eerstePersoonInRij().getTotaalPrijs());

        Kassa kassa = new Kassa(rij);
        kassa.rekenAf(rij.eerstePersoonInRij());
        System.out.println("In de kassa: " + kassa.hoeveelheidGeldInKassa());
    }
}
