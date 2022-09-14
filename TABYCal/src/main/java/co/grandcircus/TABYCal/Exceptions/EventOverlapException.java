package co.grandcircus.TABYCal.Exceptions;

import java.time.LocalDateTime;

public class EventOverlapException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EventOverlapException(String user, LocalDateTime startTime, LocalDateTime endTime) {
        super("Conflict for" + user + "at" + startTime + "and" + endTime);
    }

}
