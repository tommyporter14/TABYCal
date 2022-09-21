package co.grandcircus.tabycalwebapp.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import co.grandcircus.tabycalwebapp.Models.DateTimeWrapper;
import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;

public class WeekViewHelper {

	private List<EventFrontEnd> events;


	public List<EventFrontEnd> getEvents() {
		return events;
	}

	public void setEvents(List<EventFrontEnd> events) {
		this.events = events;
	}

	public WeekViewHelper() {

	}

	public WeekViewHelper(List<EventFrontEnd> events) {
		this.events = events;
	}

	public List<EventFrontEnd> eventsForDayAtTime(int time, LocalDate date) {
		LocalDateTime startOfHour = date.atTime(time, 0);
		LocalDateTime endOfHour = date.atTime(time, 59);
		EventFrontEnd[] eventFrontEndArr =  events.stream().filter(event -> event.getStartTime().toLocalDate().equals(date)).filter(event -> {
			LocalDateTime startingEventTime = event.getStartTime();
			return (startingEventTime.isEqual(startOfHour) || startingEventTime.isAfter(startOfHour))
					&& (startingEventTime.isBefore(endOfHour) || startingEventTime.isEqual(endOfHour));
		}).toArray(num -> new EventFrontEnd[num]);
		return Arrays.asList(eventFrontEndArr);
	}

	public List<EventFrontEnd> eventsForDayAtTime(int time, DateTimeWrapper date) {
		return eventsForDayAtTime(time, date.getDate());
	}

	public int getEarliestHour() {
		return events.stream().map(e -> e.getStartTime()).min((one, two) -> one.compareTo(two)).map(dt -> dt.getHour())
				.orElse(0);
	}
	
	public int getLatestHour() {
		return events.stream().map(e -> e.getStartTime()).max((one, two) -> one.compareTo(two)).map(dt -> dt.getHour())
				.orElse(23);
	}
	
	public String printHourForWeek(int hour) {
		return LocalTime.of(hour, 0).format(DateTimeFormatter.ofPattern("h:mm a")).toLowerCase();
		
		
	}
}
