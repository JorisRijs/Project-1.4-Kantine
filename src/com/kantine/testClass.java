package src.com.kantine;

import java.math.BigDecimal;

public class testClass {
    public static void main(String[] args){
        Datum datum = new Datum(5, 5, 1995);
        System.out.println(datum.getDatumAsString());

        Persoon persoon = new Persoon();
        System.out.println(persoon.toString());

        Artikel artikel = new Artikel("test", new BigDecimal(2.99));
        System.out.println(artikel.toString());
    }
}
