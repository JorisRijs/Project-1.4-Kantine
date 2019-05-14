package src.com.kantine;

public class testClass {
    public static void main(String[] args){
        Datum datum = new Datum(5, 5, 1995);
        System.out.println(datum.getDatumAsString());

        Persoon persoon = new Persoon();
        System.out.println(persoon.getGeslacht() +  " " +
                            persoon.getGeboorteDatum() + " " +
                            persoon.getAchternaam()
        );
    }
}
