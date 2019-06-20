package src.com.kantine;

import java.util.*;

public class Namen {
    private Namen(){}

    /**
     * Geeft een willekeurige naam uit de fNames of mNames array afhankelijk van het geslacht
     * @param geslacht
     * @return naam
     */
    public static String getRandomVoorNaam(String geslacht){
        Random random = new Random();
        String naam;
        if(geslacht == "man"){
            naam = mNames[random.nextInt(mNames.length-1)];
        }
        else {
            naam = fNames[random.nextInt(fNames.length-1)];
        }
        return naam;
    }

    /**
     * Geeft een willekeurige achternaam
     * @return naam
     */
    public static String getRandomAchterNaam(){
        Random random = new Random();
        return lastNames[random.nextInt(lastNames.length-1)];
}

    private static final String[] fNames = new String[]
            {"Aafke","Aagje","Aartje","Afina","Afra","Aleida","Alida","Allegonda","Anneke","Annemijn","Annie","Antje","Ardina","Barta","Bente","Benthe","Berendina","Bertha","Boukje","Bouwina","Bregje","Carlijn","Cathelijne","Cato","Clasina","Daantje","Diede","Dieke","Dieuwertje","Dina","Dingena","Dirkje","Divera","Doortje","Duifje","Eefje","Elise","Eliza","Elke","Ella","Elle","Elsa","Else","Engelina","Engeltje","Evelien","Famke","Felien","Fieke","Fiene","Floortje","Florien","Froukje","Frouwke","Geertje","Geertruida","Geesje","Gerda","Gerritje","Gesina","Grada","Greta","Grietje","Guusje","Hendrika","Hendrikje","Hilde","Ida","Iza","Ize","Jana","Janna","Janne","Janneke","Jannetje","Jannie","Jantien","Jasmijn","Jenna","Jenneke","Jente","Jenthe","Jentje","Jette","Jikke","Joanne","Jolien","Jolijn","Jonne","Josefien","Josje","Jozina","Karlijn","Klaasje","Koosje","Krina","Laurien","Lena","Lene","Lenne","Lente","Lenthe","Levina","Lia","Lianne","Lidewij","Lieke","Liene","Liese","Lieve","Lijsbeth","Lina","Lobke","Lotte","Maaike","Maartje","Madelief","Manna","Mare","Marieke","Marije","Marijke","Marijn","Marretje","Mechtildis","Meike","Merel","Mia","Mieke","Mijntje","Mina","Nanne","Neeltje","Niene","Nienke","Niesje","Nina","Noortje","Nora","Pieterdina","Pieternella","Pleuntje","Reina","Rensina","Renske","Rika","Romijn","Roosmarijn","Rosalinde","Rozemarijn","Saartje","Sammie","Sanna","Sanne","Silke","Sinne","Stijntje","Suze","Tessel","Teuna","Teuntje","Tonia","Trijntje","Veerle","Vere","Vlinder","Wieke","Willemijn","Willemina","Zwaantje"};

    private static final String[] mNames = new String[] {"Aaldert","Aaldrik","Adriaan","Albert","Aldert","Alfons","Alje","Alle","Andries","Anno","Anthonie","Anton","Antoon","Aren","Arend","Arie","Arij","Arjan","Arjen","Arnold","August","Barend","Bartel","Barteld","Bastiaan","Berend","Bertus","Boele","Boudewijn","Bruno","Carel","Christiaan","Coenraad","Cornelis","Corstiaan","Dingeman","Dingenis","Douwe","Duco","Eduard","Egbert","Eildert","Elbert","Eltje","Eltjo","Elzo","Enno","Eppo","Evert","Fedde","Ferre","Floris","Fokko","Francis","Frederik","Friso","Garrit","Gerard","Gerben","Gerrit","Gezinus","Gijsbert","Gilles","Godfried","Gosem","Gosen","Govert","Gradus","Gustaaf","Harmen","Heino","Hendrik","Herman","Hero","Hessel","Hidde","Hilbert","Hindrik","Hubrecht","Huibert","Iman","Jannes","Jantinus","Jasper","Jelle","Jeroen","Jesper","Jochem","Johan","Joppe","Joris","Jozef","Jurjen","Jurrien","Karel","Lammert","Leendert","Leonard","Leunis","Lieven","Lodewijk","Lourens","Luitje","Luppo","Maarten","Machiel","Marijn","Marnix","Marten","Martijn","Mathijs","Matthijs","Maurits","Maximiliaan","Meindert","Melle","Menno","Merijn","Merlijn","Michiel","Nicolaas","Olivier","Otto","Pepijn","Peter","Pieter","Reijer","Reinder","Reinier","Reinout","Remco","Remmelt","Renier","Roelof","Roemer","Rogier","Rokus","Rudolf","Rutger","Sander","Sebas","Sebastiaan","Sijmen","Silvijn","Steven","Stoffer","Teije","Teunis","Theodoor","Thieme","Thije","Thijme","Thijmen","Thymen","Tieme","Tiemen","Tije","Tijme","Tijmen","Timme","Timo","Tonnis","Torre","Valentijn","Wessel","Wijnand","Willem","Wolter","Wouter","Zeger"};

    private static final String[] lastNames = new String[]{"de Jong","Jansen","de Vries","van de Berg","van Dijk","Bakker","Janssen","Visser","Smit","Meijer","de Boer","Mulder","de Groot","Bos","Vos","Peters","Hendriks","van Leeuwen","Dekker","Brouwer","de Wit","Dijkstra","Smits","deGraaf","van der Meer","van der Linden","Kok","Jacobs","de Haan","Vermeulen","van den Heuvel","van de Veen","van den Broek","de Bruijn","de Bruin","van der Heijden","Schouten","van Beek","Willems","van Vliet","van de Ven","Hoekstra","Maas","Verhoeven","Koster","van Dam","van de Wal","Prins","Blom","Huisman","Peeters","de Jonge","Kuipers","van Veen","Post","Kuiper","Veenstra","Kramer","van de Brink","Scholten","van Wijk","Postma","Martens","Vink","de Ruiter","Timmermans","Groen","Gerritsen","Jonker","van Loon","Boer","van de Velde","Willemsen","Smeets","de Lange","de Vos","Bosch","van Dongen","Schipper","de Koning","van der Laan","Koning","van de Velden","Driessen","van Doorn","Hermans","Evers","van den Bosch","van der Meulen","Hofman","Bosman","Wolters","Sanders","van der Horst","Mol","Kuijpers","Molenaar","van der Pol","de Leeuw","Verbeek"};
}
