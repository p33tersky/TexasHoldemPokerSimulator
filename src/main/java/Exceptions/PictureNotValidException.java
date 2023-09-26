package Exceptions;

public class PictureNotValidException extends RuntimeException{
    public PictureNotValidException(String message){
        super(message);
    }
}
