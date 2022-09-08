package co.grandcircus.employeecalendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class ItemExistsException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public ItemExistsException(String userName) {
        super("Item with User Name: " + userName);
    }

}
