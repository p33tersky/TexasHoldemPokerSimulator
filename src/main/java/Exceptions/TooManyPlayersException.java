package Exceptions;

public class TooManyPlayersException extends RuntimeException{
    public TooManyPlayersException(String message){
        super(message);
    }
}
