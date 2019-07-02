package com.kantine;


public class Docent extends Persoon implements KortingskaartHouder{

    private String acronym;

    public Docent(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, String acronym){
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);

        this.acronym = acronym.substring(0,3);
    }

    public Docent(String acronym){
        super();
        this.acronym = acronym.substring(0,3);
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    //kortings kaart methodes
    public double getKortingsPercentage(){
        return 25.00;
    }

    public boolean hasMaximum(){
        return true;
    }

    public double getMaximum(){
        return 1.00;
    }
}
