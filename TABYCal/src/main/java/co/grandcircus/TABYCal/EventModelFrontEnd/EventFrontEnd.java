package co.grandcircus.TABYCal.EventModelFrontEnd;

import java.time.LocalDateTime;
import java.util.List;

public class EventFrontEnd {

	private String id;
	private String eventName;
	private String description;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Double duration;
	private List<String> users;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public EventFrontEnd(String eventName, String description, LocalDateTime startTime, LocalDateTime endTime,
			Double duration, List<String> users) {
		this.eventName = eventName;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.users = users;
	}
	
	public EventFrontEnd() {}
}

	
