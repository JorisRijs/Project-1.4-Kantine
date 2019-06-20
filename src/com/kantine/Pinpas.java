package src.com.kantine;

public class Pinpas extends Betaalwijze {

    private double creditLimit = 0;

    /**
     * Methode om creditLimit in te stellen
     * @param creditLimit
     */
    public void setCreditLimit(double creditLimit){
        this.creditLimit = creditLimit;
    }
    /**
     * Methode om betaling af te handelen
     *
     * @param //double amount
     * @return boolean of er genoeg saldo is
     */
    public void betaal(double amount) throws TeWeinigGeldException{
        if(getSaldo()-creditLimit  >= amount){
            changeSaldo(-amount);
        } else {
            throw new TeWeinigGeldException();
        }
    }
}
