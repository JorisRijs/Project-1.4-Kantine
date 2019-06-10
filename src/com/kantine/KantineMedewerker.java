package src.com.kantine;

public class KantineMedewerker extends Persoon {

    private int employeeNumber;
    private boolean Achter_Kassa;

    public KantineMedewerker(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, int employeeNumber, boolean Achter_Kassa){
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);

        this.employeeNumber = employeeNumber;
        this.Achter_Kassa = Achter_Kassa;
    }
}
