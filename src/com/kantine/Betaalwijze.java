package src.com.kantine;

public abstract class Betaalwijze {

    protected double saldo;

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public void changeSaldo(double amount){
        this.saldo += amount;
    }

    public double getSaldo(){
        return saldo;
    }

    /**
     * Methode om te betalen
     * haalt hoeveelheid af van saldo
     *
     * @param amount
     * @return boolean die aangeeft of er voldoende saldo is
     */
    public abstract boolean betaal(double amount);
}