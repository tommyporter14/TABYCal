package co.grandcircus.employeecalendar.EventExceptions;

public class EventNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EventNotFoundException() {
		super("Event Not Found");
	}
}
