package src.com.kantine;

public class Persoon {
    private int BSN;
    private String voornaam;
    private String achternaam;
    private Datum geboorteDatum;
    private char geslacht;

    private Betaalwijze betaalwijze;

    public Persoon(){
        BSN = 0;
        voornaam = "";
        achternaam = "";
        geboorteDatum = new Datum(0, 0, 0);
        geslacht = 'O';
    }

    public Persoon(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht){
        if(geslacht == 'M' || geslacht == 'V'){
            this.geslacht = geslacht;
        } else {
            this.geslacht = 'O';
        }
        this.BSN = BSN;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
    }

    public Betaalwijze getBetaalwijze() {
        return betaalwijze;
    }

    public void setBetaalwijze(Betaalwijze betaalwijze) {
        this.betaalwijze = betaalwijze;
    }

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGeboorteDatum() {
        return geboorteDatum.getDatumAsString();
    }

    public void setGeboorteDatum(Datum geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public String getGeslacht() {
        switch (geslacht){
            case 'M':
                return "Man";
            case 'V':
                return "Vrouw";
            default:
                return "Onbekend";
        }
    }

    public void setGeslacht(char geslacht) {
        if (geslacht == 'M' || geslacht == 'V'){
            this.geslacht = geslacht;
        } else {
            System.out.println(geslacht + " is geen juist geslacht");
        }
    }
    /**
     * Methode om de waarden van de klasse persoon te krijgen als string
     * @return string
     */
    public String toString(){
        return(BSN + " " + voornaam + " " + achternaam + " " + getGeslacht() + " " + getGeboorteDatum());
    }
}
