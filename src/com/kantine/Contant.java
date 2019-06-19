package src.com.kantine;

public class Contant extends Betaalwijze {

    /**
     * Methode om betaling af te handelen
     *
     * @param amount
     * @return boolean of er genoeg saldo is
     */
    public boolean betaal(double amount){
        if(getSaldo() >= amount){
            changeSaldo(-amount);
            return true;
        } else {
            return false;
        }
    }
}
