package com.kantine;

public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	public Datum(){
        this.dag = 0;
        this.maand = 0;
        this.jaar = 0;
    }

    public Datum(int dag, int maand, int jaar){
	    this();
	    if(bestaatDatum(dag, maand, jaar)){
            this.dag = dag;
            this.maand = maand;
            this.jaar = jaar;
        }
    }

    /**
     * Methode om te controleren of een datum klopt
     *
     * @param dag
     * @param maand
     * @param jaar
     * @return boolean
     */
	public boolean bestaatDatum(int dag, int maand, int jaar){
		if (dag >= 1 && maand >= 1 && maand <= 12 && jaar >= 1900 && jaar <= 2100){
            if(dag <= 31 && maand == 1 || maand == 3 || maand == 5 || maand == 7 || maand == 8 || maand == 10 || maand == 12){
                return true;
            } else if(dag <= 30 && maand == 4 || maand == 6 || maand == 9 || maand == 11){
                return true;
            } else if (dag <= 28 && maand == 2){
                return true;
            } else if(dag <= 29 && jaar % 4 == 0 && maand == 2){
                if(jaar % 100 == 0){
                    if (jaar % 400 == 0){
                        return true;
                    }
                    return false;
                }
                return true;
            }
        }
		return false;
	}

    public int getDag() {
        return dag;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    }

    public void setDag(int dag) {
        this.dag = dag;
    }

    public void setMaand(int maand) {
        this.maand = maand;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    /**
	 * Getter voor Sting weergave van datum
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
	    if (bestaatDatum(dag, maand, jaar)){
            return dag + "-" + maand + "-" + jaar;
        } else {
	        return "Onbekend";
        }
	}
}
