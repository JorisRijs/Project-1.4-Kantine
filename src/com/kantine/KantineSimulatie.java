package src.com.kantine;

public class KantineSimulatie {

    private Kantine kantine;
    private KantineAanbod KA;

    public static final int DAGEN = 7;

    /**
     * constructor
     */

    String[] artikels = new String[] {"appel", "babaan", "burger", "Patat", "Pindakaas"};

    public KantineSimulatie(){
        kantine = new Kantine();
    }

    /**
    * Deze methode simuleert een aantal dagen in het
    * verloop van de kantine
     *
     * @param dagen
    */

    public void simuleer(int dagen){

        //herhaal elke dag
        for(int i=0; i < dagen; i++){

            //per dag nu even vast 10 + i personen nar binnen
            //later gaan, wordt volgende week veranderd...

            //for lus voor personen
            for(int j = 0; j < 10 + i; j++){
                Persoon persoon = new Persoon();
                Dienblad dienblad = new Dienblad(persoon);
                kantine.loopPakSluitAan(dienblad, artikels);
            }

            kantine.verwerkRijVoorKassa();
            System.out.println("Er zijn op dag " + i + " " + kantine.getKassa().aantalArtikelen() + " artikelen verkocht " +
                    "voor een totaal van " + kantine.getKassa().hoeveelheidGeldInKassa());
            kantine.getKassa().resetKassa();
        }

    }

    /**
     * start een simulatie
     */

    public static void main(String[] args){

        int dagen;
        if(args.length == 0){
            dagen = DAGEN;
        }
        else{
            dagen = Integer.parseInt(args[0]);
        }
        KantineSimulatie KS = new KantineSimulatie();
        KS.simuleer(dagen);
    }
}
