package com.kantine;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KantineSimulatie {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JPAVoorbeeld");
    private EntityManager manager;

    // kantine
    private Kantine kantine;

    // dagen
    private static final int DAGEN = 7;

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

    //opleidingen die studenten kunnen volgen
    private static final String[] courses = new String[]
            {"ICT", "Bio-Informatica", "PABO", "Bedrijfskunde", "Chemie", "Chemische Technologie", "Rechten"};

    //afkortingen van alle docenten die in de kantine te vinden zijn
    private static final String[] docenten = new String[]
            {"TATH", "VEGT", "HOEM", "KNJA", "BIKO", "MESM", "BRUM"};

    /**
     * Constructor
     *
     */
    public KantineSimulatie() {
        kantine = new Kantine();
        random = new Random();
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
        //simuleer een bepaald aantal dagen
        for (int i=0; i<dagen; i++){
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
                betaalWijze.setSaldo(8); //alle klanten hebben voor nu 8 euro per dag
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

            //print de dagtotalen en hoeveel personen binnen zijn gekomen
            System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~");
            System.out.println("Er zijn op dag " + i + " " + kantine.getKassa().aantalArtikelen() + " artikelen verkocht " +
                    "voor een totaal van " + kantine.getKassa().hoeveelheidGeldInKassa());
            System.out.println("Er waren " + customerCount + " klanten.");
            System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~");

            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }
    }

    public void runVoorbeeld(){
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        //een transactie
        manager.close();
        ENTITY_MANAGER_FACTORY.close();

    }

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

        double[] omzetten = new double[]{321.25, 450.50, 210.45, 190.85, 193.25, 159.90, 214.25, 220.90, 102.90, 242.70, 260.35};
        int[] aantallen = new int[]{100, 125, 56, 160};
        double[] dagOmzetten = Administratie.berekenDagOmzet(omzetten);
        System.out.println("+-----------------------Getallen----------------------+");
        System.out.println("|Gemiddelde omzet per dag : " + Administratie.berekenGemiddeldeOmzet(omzetten));
        System.out.println("|Gemiddelde verkochte artikelen per dag : " + Administratie.berekenGemiddeldAantal(aantallen));
        System.out.println("| ");
        for(int i =0; i < dagOmzetten.length; i++) {
            System.out.println("|Gemiddelde omzet op dag " + i + " : " + dagOmzetten[i]);
        }
        System.out.println("+-----------------------------------------------------+");
    }
}
