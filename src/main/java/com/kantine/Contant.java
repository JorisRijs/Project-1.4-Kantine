package com.kantine;

public class Contant extends Betaalwijze {

    /**
     * Methode om betaling af te handelen
     *
     * @param amount
     * @return boolean of er genoeg saldo is
     */
    public void betaal(double amount) throws TeWeinigGeldException{
        if(getSaldo() >= amount){
            changeSaldo(-amount);
        } else {
            throw new TeWeinigGeldException();
        }
    }
}
