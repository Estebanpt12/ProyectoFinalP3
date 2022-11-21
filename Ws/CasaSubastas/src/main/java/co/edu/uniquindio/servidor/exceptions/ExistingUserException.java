package co.edu.uniquindio.servidor.exceptions;

public class ExistingUserException extends Exception{
    public ExistingUserException(String message){
        super(message);
    }
}
