package src.com.kantine;

public class Student extends Persoon {

    private int student_number;
    private String course;

    public Student(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, int student_number, String course){
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);

        this. student_number = student_number;
        this.course = course;
    }

}
