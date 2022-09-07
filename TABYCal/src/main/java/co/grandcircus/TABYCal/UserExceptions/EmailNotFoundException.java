package co.grandcircus.TABYCal.UserExceptions;


public class EmailNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailNotFoundException(String userName) {
        super("Could not find item with email: " + userName );
    }

}