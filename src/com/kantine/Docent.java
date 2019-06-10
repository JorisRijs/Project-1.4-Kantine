package src.com.kantine;

import java.util.
public class Docent extends Persoon {

    private String acronym;

    public Docent(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, String acronym){
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);

        this.acronym = acronym.substring(0,4);
    }

}
