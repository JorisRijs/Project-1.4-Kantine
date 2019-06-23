package com.kantine;

public class Administratie {

    private static final int DAYS_IN_WEEK = 7;

    private Administratie(){}

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] aantal) {
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
        double[] result = new double[7];
        for(int i = 0; i < DAYS_IN_WEEK; i++) {
            int j = 0;
            while(i < omzet.length - 7 * j) {
                result[i] += omzet[i + 7 * j];
                j++;
            }
            result[i] = result[i] / j;
        }
        return result;
    }
}
