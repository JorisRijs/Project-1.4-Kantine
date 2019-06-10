package src.com.kantine;


public class Docent extends Persoon {

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
}
