package co.grandcircus.restapis.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidEmailException(String userName) {
        super(userName + "is an invalid email.");
    }

}
