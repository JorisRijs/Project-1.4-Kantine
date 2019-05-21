package src.com.kantine;

public class KantineSimulatie {

    private Kantine kantine;

    public static final int DAGEN = 7;

    /**
     * constructor
     */

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
        for(int i=0;i <= dagen;i++){

            //per dag nu even vast 10 + i personen nar binnen
            //laten gaan, wordt volgende week veranderd...

            //for lus voor personen
            for(int j = 0; j < 10 + i; j++){
                //kantine.(...);
            }

            //verwerk rij voor de kassa

            // toon dagtotalen (artikelen en geld in kassa)

            //reset de kassa voor de volgende dag
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


    }


}
