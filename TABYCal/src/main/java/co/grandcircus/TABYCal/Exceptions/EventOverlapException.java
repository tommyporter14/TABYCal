package co.grandcircus.TABYCal.Exceptions;

import java.time.LocalDateTime;

public class EventOverlapException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EventOverlapException(String user, LocalDateTime startTime, LocalDateTime endTime) {
        super("Conflict scheduling event. One or more users already has an event at this time.");
    }

}
