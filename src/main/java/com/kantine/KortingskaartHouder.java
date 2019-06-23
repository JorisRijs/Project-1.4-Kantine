package com.kantine;

public interface KortingskaartHouder {
    /**
     * Mehode om kortingspercentage op te vragen
     */
    public double getKortingsPercentage();

    /**
     * Mehode om op te vragen of de kortingskaart een maximum korting per keer heeft
     */
    public boolean hasMaximum();

    /**
     * Methode om het maximum kortingsbedrag op te vragen
     * @return
     */
    public double getMaximum();
}
