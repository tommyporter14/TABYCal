package co.grandcircus.restapis.Exceptions;

public class NoUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoUserException(String userName) {
        super("Could not find item with email: " + userName );
    }

}