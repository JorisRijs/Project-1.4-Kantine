package src.com.kantine;

public class Administratie {

    private Administratie(){}

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] aantal) {
        // method body omitted

        int totaal = 0;

        for(int i = 0; i < aantal.length; i++){
            totaal += aantal[i];
        }
        return (totaal/aantal.length);
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {

        Double totaal = 0.00;

        for(int i = 0 ; i < omzet.length ; i++){
            totaal += omzet[i];
        }

    return (totaal/omzet.length);
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[7];
        for(int i = 0; i < 7; i++) {

            int j = 0;
            while( true ) {
                temp[i] += omzet[i + 7 * j];

                // omitted

            }
        }
        return temp;
    }
}
