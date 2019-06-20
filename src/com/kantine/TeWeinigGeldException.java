package src.com.kantine;

public class TeWeinigGeldException extends RuntimeException{
    public TeWeinigGeldException(){
        super();
    }

    public TeWeinigGeldException(Exception e){
        super(e);
    }

    public TeWeinigGeldException(String message){
        super(message);
    }
}
