package anilist.apilistener.exception;

public class WrongRequestFormatException extends RuntimeException{
    public WrongRequestFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
