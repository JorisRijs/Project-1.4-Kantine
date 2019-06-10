package src.com.kantine;

import java.util.*;

public class KantineSimulatie_2 {

    // kantine
    private Kantine kantine;

    // dagen
    public static final int DAGEN = 7;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen = new String[]
            {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static Double[] artikelprijzen = new Double[]{1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    private int[] aantalklanten;


    private ArrayList<Persoon> klanten;
    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        kantine = new Kantine();
        random = new Random();
        klanten = new ArrayList<>();
        int[] hoeveelheden = getRandomArray(
                AANTAL_ARTIKELEN,
                MIN_ARTIKELEN_PER_SOORT,
                MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(
                artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen
     * min en max van de gegeven lengte te genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for(int i = 0; i < lengte ;i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl)
     * en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array
     * artikelnamen de bijhorende array van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for(int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {
            /*
            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG) ;
            // laat de personen maar komen...
            for(int j = 0; j < aantalpersonen; j++) {
                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt
                Persoon persoon = new Persoon();
                Dienblad dienblad = new Dienblad(persoon);
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);
                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);
            }*/

            String[] courses = new String[]{"IT", "business", "health", "NSE", "se", "the course we don't speak about 'bitm'"};
            Random rnd = new Random();

            String course;
            int coursenumber;
            int studentnumber;
            int studentamount = rnd.nextInt(89);

            for (int j = 0; j < studentamount; j++){
                coursenumber = rnd.nextInt(5);
                studentnumber = rnd.nextInt();
                course = courses[coursenumber];
                // hij maakt een student aan en een dienblad
                //hij koppelt het dien blad met de student
                Student student = new Student(studentnumber, course);
                Dienblad dienblad = new Dienblad(student);
                klanten.add(student);

                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);
                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);
            }

            String[] acronyms = new String[]{"baba", "niev", "tath", "biko", "mesm", "brum"};
            int acronymnum;
            String Acronym;
            int docentamount = rnd.nextInt(10);

            for (int j =0; j < docentamount; j++){
                acronymnum = rnd.nextInt(5);
                Acronym = acronyms[acronymnum];
                Docent docent = new Docent(Acronym);
                Dienblad dienblad = new Dienblad(docent);
                klanten.add(docent);

                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);
                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);
            }
            for (int j =0; j < 1; j++) {
                KantineMedewerker kw = new KantineMedewerker(1, false);
                Dienblad dienblad = new Dienblad(kw);
                klanten.add(kw);

                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);
                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN - 1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);
            }

            //print de gegevens uit van de klanten
            //for(int j = 0; j < klanten.size(); i++){
            //    System.out.println(klanten.get(j).toString());
            //}

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();



            // druk de dagtotalen af en hoeveel personen binnen zijn gekomen
            System.out.println("Er zijn op dag " + i + " " + kantine.getKassa().aantalArtikelen() + " artikelen verkocht " +
                    "voor een totaal van " + kantine.getKassa().hoeveelheidGeldInKassa());
            System.out.println("Er waren " + klanten.size() + " klanten.");

            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
            klanten.clear();
        }
    }

    public static void main(String[] args){

        int dagen;
        if(args.length == 0){
            dagen = DAGEN;
        }
        else{
            dagen = Integer.parseInt(args[0]);
        }
        KantineSimulatie_2 KS = new KantineSimulatie_2();
        KS.simuleer(dagen);
        /*
        Administratie Ad = new Administratie();
        int[] getallen = new int[]{45, 56, 34, 39, 40, 31};
        Double avg = Ad.berekenGemiddeldAantal(getallen);
        System.out.println(avg);

        double[] nummers = new double[]{567.70, 498.25, 458.90};
        double avgrevenue = Ad.berekenGemiddeldeOmzet(nummers);
        */




        double[] nummers = new double[]{567.70, 498.25, 458.90};
        double avgrevenue = Administratie.berekenGemiddeldeOmzet(nummers);
        System.out.println(avgrevenue);
    }
}
