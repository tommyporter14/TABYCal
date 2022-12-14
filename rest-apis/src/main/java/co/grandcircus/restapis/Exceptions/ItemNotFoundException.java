package co.grandcircus.restapis.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ItemNotFoundException(String id) {
        super("Could not find item with id: " + id);
    }

}