package co.grandcircus.TABYCal.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class UserNameExistsException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public UserNameExistsException(String userName) {
        super("Item with User Name: " + userName);
    }

}
