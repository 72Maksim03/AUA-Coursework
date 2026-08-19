package database;

public class MalformedAccountException extends Exception{

    public MalformedAccountException() {
        super("Malformed account.");
    }

    public MalformedAccountException(String message) {
        super(message);
    }
}
