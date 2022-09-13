package co.grandcircus.restapis.Exceptions;


public class EmailNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailNotFoundException(String userName) {
        super("Could not find item with email: " + userName );
    }

}