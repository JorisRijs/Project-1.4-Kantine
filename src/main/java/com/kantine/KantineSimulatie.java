package com.kantine;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class KantineSimulatie {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    // kantine
    private Kantine kantine;

    // dagen
    private static int DAGEN = 7;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // welke dag het is
    private int dag;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen = new String[]
            {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static Double[] artikelprijzen = new Double[]{1.00, 3.10, 2.00, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    //ANSI kleuren voor prints
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32;1m";

    //opleidingen die studenten kunnen volgen
    private static final String[] courses = new String[]
            {"ICT", "Bio-Informatica", "PABO", "Bedrijfskunde", "Chemie", "Chemische Technologie", "Rechten"};

    //afkortingen van alle docenten die in de kantine te vinden zijn
    private static final String[] docenten = new String[]
            {"TATH", "VEGT", "HOEM", "KNJA", "BIKO", "MESM", "BRUM"};

    //statistiek getallen
    double[] omzetten = new double[DAGEN];
    int[] aantallen = new int[DAGEN];
    int[] klanten = new int[DAGEN];

    /**
     * Constructor
     *
     */
    public KantineSimulatie(int dagen) {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager, this);
        random = new Random();
        int[] hoeveelheden = getRandomArray(
                AANTAL_ARTIKELEN,
                MIN_ARTIKELEN_PER_SOORT,
                MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(
                artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
        simuleer(dagen);
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
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
     * @return de huidige dag in de simulatie
     */
    public int getDag() {
        return dag;
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
        //simuleer een bepaald aantal dagen
        dag = 0;
        for (int i=0; i<dagen; i++){
            dag++;
            //Bedenk hoeveel klanten deze dag binnen komen
            int customerCount = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            //genereer customerCount hoeveelheid klanten
            for (int o=0; o<customerCount; o++){
                //maak de dienblad aan voor de klant
                Dienblad klant;

                //maak een nummer aan dat bepaald wat voor soort klant wordt aangemaakt
                int randCustomer = getRandomValue(0, 99);

                //maak de klanten aan
                if (randCustomer == 0){ //kantinemedewerker
                    int employeeNumber = random.nextInt();

                    KantineMedewerker kantineMedewerker = new KantineMedewerker(employeeNumber, false);
                    klant = new Dienblad(kantineMedewerker);
                }
                else if (randCustomer <= 89){ //student
                    int studentNumber = random.nextInt();
                    int courseNumber = getRandomValue(0, courses.length-1);

                    String courseString = courses[courseNumber];

                    Student student = new Student(studentNumber, courseString);
                    klant = new Dienblad(student);
                }
                else{ //docent
                    int docentNum = getRandomValue(0, docenten.length-1);
                    String docentString = docenten[docentNum];

                    Docent docent = new Docent(docentString);
                    klant = new Dienblad(docent);
                }
                //bepaal de betaalwijze van de klant doormiddel van een random getal
                int randBetaalWijze = getRandomValue(1, 10);
                Betaalwijze betaalWijze;
                if (randBetaalWijze <= 3){
                    betaalWijze = new Contant();
                } else {
                    betaalWijze = new Pinpas();
                }
                betaalWijze.setSaldo(getRandomValue(6,12)); //alle klanten hebben voor nu 6-12
                klant.getKlant().setBetaalwijze(betaalWijze);

                //Geef de klant wat gegevens
                klant.getKlant().setVoornaam(Namen.getRandomVoorNaam("man"));
                klant.getKlant().setAchternaam(Namen.getRandomAchterNaam());

                //pak een random aantal artikelen tussen MIN_ARTIKELEN_PER_PERSOON en MAX_ARTIKELEN_PER_PERSOON
                int artikelCount = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);
                int[] artikelIndexen = getRandomArray(artikelCount, 0, artikelCount-1);
                String[] artikelen = geefArtikelNamen(artikelIndexen);

                //zet de klant met zijn artikelen in de rij
                kantine.loopPakSluitAan(klant, artikelen);
            }
            //verwerk iedere dag de rij die voor de kassa staat
            kantine.verwerkRijVoorKassa();
            omzetten[i] = kantine.getKassa().hoeveelheidGeldInKassa();
            aantallen[i] = kantine.getKassa().aantalArtikelen();
            klanten[i] = customerCount;

            //print de dagtotalen en hoeveel personen binnen zijn gekomen
            System.out.println(ANSI_YELLOW + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~");
            System.out.println("Er zijn op dag " + i + " " + kantine.getKassa().aantalArtikelen() + " artikelen verkocht " +
                    "voor een totaal van " + kantine.getKassa().hoeveelheidGeldInKassa());
            System.out.println("Er waren " + customerCount + " klanten.");
            System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~" + ANSI_RESET);

            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }

        //queries om getallen uit db te halen
        Query query = manager.createQuery(
                "SELECT SUM(totaal) FROM Factuur");
        double qTotaal = (Double) query.getSingleResult();
        query = manager.createQuery(
                "SELECT SUM(korting) FROM Factuur");
        double qKorting = (Double) query.getSingleResult();
        query = manager.createQuery(
                "SELECT AVG(korting) FROM Factuur");
        double qAvgKorting = (Double) query.getSingleResult();
        query = manager.createQuery(
                "SELECT AVG(totaal) FROM Factuur");
        double qAvgTotaal = (Double) query.getSingleResult();
        query = manager.createQuery(
                "SELECT f FROM Factuur f ORDER BY totaal DESC").setMaxResults(3);
        List<Object[]> resultaat1 = query.getResultList();

        //print getallen
        System.out.println(ANSI_GREEN + "+-------------------------------------------Getallen------------------------------------------+");
        System.out.println("|Totale omzet: " + qTotaal);
        System.out.println("|Totale uitgegeven korting: " + qKorting);
        System.out.println("|");
        System.out.println("|Gemiddelde omzet per dag : " + Administratie.berekenGemiddeldeOmzet(omzetten));
        System.out.println("|Gemiddelde verkochte artikelen per dag : " + Administratie.berekenGemiddeldAantal(aantallen));
        System.out.println("|Gemiddelde hoeveelheid klanten per dag : " + Administratie.berekenGemiddeldAantal(klanten));
        System.out.println("|Gemiddeld bedrag per factuur: " + qAvgTotaal);
        System.out.println("|Gemiddeld uitgegeven korting per factuur: " + qAvgKorting);
        System.out.println("| ");
        double[] dagOmzetten = Administratie.berekenDagOmzet(omzetten);
        for(int i =0; i < dagOmzetten.length; i++) {
            System.out.println("|Gemiddelde omzet op dag " + i + " : " + dagOmzetten[i]);
        }
        System.out.println("|");
        System.out.println("|De drie hoogste facturen:");
        for(Object factuur : resultaat1){
            System.out.println("|" + factuur);
        }
        System.out.println("+---------------------------------------------------------------------------------------------+" + ANSI_RESET);
    }

    public static void main(String[] args){
        int dagen;
        if(args.length == 0){
            dagen = DAGEN;
        }
        else{
            dagen = Integer.parseInt(args[0]);
            DAGEN = dagen;
        }
        KantineSimulatie KS = new KantineSimulatie(dagen);
    }
}
