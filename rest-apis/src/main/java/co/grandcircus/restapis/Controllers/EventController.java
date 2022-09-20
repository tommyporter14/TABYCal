package co.grandcircus.restapis.Controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.restapis.Exceptions.EventNotFoundException;
import co.grandcircus.restapis.Exceptions.EventOverlapException;
import co.grandcircus.restapis.Models.Event;
import co.grandcircus.restapis.Repositories.EventRepository;

@RestController
public class EventController {

	@ResponseBody
	@ExceptionHandler(EventNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String eventNotFoundHandler(EventNotFoundException ex) {
		return ex.getMessage();
	}

	@Autowired
	private EventRepository repo;

	@GetMapping("/event")
	public List<Event> getAllEvents(@RequestParam(required= false) String startDate,
									@RequestParam(required= false) String endDate,
	  								@RequestParam(required= false) List<String> users) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime adjustedStartDate = null;
		LocalDateTime adjustedEndDate = null;
		if(startDate != null && endDate != null) {
			adjustedStartDate = LocalDateTime.parse(startDate, formatter);
			adjustedEndDate = LocalDateTime.parse(endDate, formatter);

		}
		
		if(startDate != null &&endDate != null && users !=null) {
			return repo.findEventsByUsersAndDateRange(users, adjustedStartDate, adjustedEndDate);
		}else if (startDate != null && endDate !=null) {
			return repo.findEventsUsingDateRange(adjustedStartDate, adjustedEndDate);
		}else if(users != null){
			return repo.findEventByListOfUsers(users);	
		}
			
		return repo.findAll();
	}

	// get by id
	@GetMapping("/event/find/{id}")
	public Event getEventById(@PathVariable("id") String id) {
		return repo.findById(id).orElseThrow(() -> new EventNotFoundException());
	}

	// get by username
	@GetMapping("/event/{user}")
	public List<Event> getEventsByUserName(@PathVariable("user") String user) {
		return repo.findByUsers(user);
	}

	// create
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/event/create")
	public Event createEvent(@RequestBody Event event) {
		checkEventIsValid(event);
		repo.insert(event);
		Duration dur = Duration.between(event.getStartTime(), event.getEndTime());
		double timeMin = dur.toMinutes();
		double time = timeMin / 60;
		event.setDuration(time);
		repo.save(event);
		return event;
	}

	//validator
	private void checkEventIsValid(Event event) {
		for (String user : event.getUsers()) {
			List<Event> userEventList = repo.findByUsers(user);

			for (Event userEvent : userEventList) {
				boolean startTimeOverlaps = ((userEvent.getStartTime().isAfter(event.getStartTime())
						|| userEvent.getStartTime().isEqual(event.getStartTime()))
								&& userEvent.getStartTime().isBefore(event.getEndTime()));
				boolean endTimeOverlaps = ((userEvent.getEndTime().isAfter(event.getStartTime())
						|| userEvent.getEndTime().isEqual(event.getStartTime()))
								&& (userEvent.getEndTime().isBefore(event.getEndTime())));
				if (startTimeOverlaps || endTimeOverlaps) {
					throw new EventOverlapException(user, userEvent.getStartTime(), userEvent.getEndTime());
				}

			}
		}

	}

	// update whole event
	@PutMapping("/event/put/{id}")
	public Event updateEvent(@RequestBody Event updateEvent, @PathVariable("id") String id) {
		checkEventIsValid(updateEvent);
		Duration dur = Duration.between(updateEvent.getStartTime(), updateEvent.getEndTime());
		double timeMin = dur.toMinutes();
		double time = timeMin / 60;
		updateEvent.setDuration(time);
		updateEvent.setId(id);
		return repo.save(updateEvent);
	}

	// // partial update
	// @PatchMapping("event/{id}")
	// public Event updatePartialEvent(@PathVariable("id") String id, @RequestParam(required = false) String eventName,
	// 		@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMddHHmm") LocalDateTime startTime,
	// 		@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMddHHmm") LocalDateTime endTime,
	// 		@RequestParam(required = false) String description, @RequestParam(required = false) List<String> users) {
	// 	Event event = repo.findById(id).orElseThrow(() -> new EventNotFoundException());
	// 	if (eventName != null) {
	// 		event.setEventName(eventName);
	// 	}
	// 	if (startTime != null) {
	// 		event.setStartTime(startTime);
	// 	}
	// 	if (endTime != null) {
	// 		event.setEndTime(endTime);
	// 	}
	// 	if (description != null) {
	// 		event.setDescription(description);
	// 	}
	// 	if (users != null) {
	// 		event.setUsers(users);
	// 	}

	// 	event.setId(id);
	// 	Duration dur = Duration.between(event.getStartTime(), event.getEndTime());
	// 	double timeMin = dur.toMinutes();
	// 	double time = timeMin / 60;
	// 	event.setDuration(time);
	// 	return repo.save(event);
	// }

	// delete
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/event/{id}")
	public void deleteEvent(@PathVariable("id") String id) {
		repo.deleteById(id);
	}



// partial update 
@PatchMapping("event/patch/{id}")
public Event updatePartialEvent(@PathVariable("id") String id, @RequestBody Event updateEvent) {
	System.out.println("I'm in updatePartialEvent");
	
	checkEventIsValid(updateEvent);
	Event event = repo.findById(id).orElseThrow(() -> new EventNotFoundException());
	event.setId(id);
	if (updateEvent.getEventName() != null) {
		event.setEventName(updateEvent.getEventName());
	}
	if (updateEvent.getStartTime() != null) {
		event.setStartTime(updateEvent.getStartTime());
	}
	if (updateEvent.getEndTime() != null) {
		event.setEndTime(updateEvent.getEndTime());
	}
	if (updateEvent.getDescription() != null) {
		event.setDescription(updateEvent.getDescription());
	}
	if (updateEvent.getUsers() != null) {
		event.setUsers(updateEvent.getUsers());
	}

	Duration dur = Duration.between(event.getStartTime(), event.getEndTime());
	double timeMin = dur.toMinutes();
	double time = timeMin / 60;
	event.setDuration(time);
	return repo.save(event);
}



}
