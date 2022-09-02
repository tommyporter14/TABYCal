package co.grandcircus.employeecalendar.EventController;

import java.time.Duration;
import java.time.LocalDateTime;
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


import co.grandcircus.employeecalendar.EventExceptions.EventNotFoundException;
import co.grandcircus.employeecalendar.EventModel.Event;
import co.grandcircus.employeecalendar.EventRepository.EventRepository;

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
	
	//get all
	@GetMapping("/event")
	public List<Event> getAllEvents(){
		return repo.findAll();
	}
	
	//get by id
	@GetMapping("/event/{id}")
	public Event getEventById(@PathVariable("id") String id) {
		return repo.findById(id).orElseThrow(() -> new EventNotFoundException());
	}
	
	//create
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/event")
	public Event createEvent(@RequestBody Event event) {
		
		repo.insert(event);
		Duration dur = Duration.between(event.getStartTime(), event.getEndTime());
		double timeMin = dur.toMinutes();
		double time = timeMin/60;
		event.setDuration(time);
		return event;
	}
	
	//update whole event
	@PutMapping("/event/{id}")
	public Event updateEvent(@RequestBody Event event, @PathVariable("id") String id) {
		event.setId(id);
		return repo.save(event);
	}
	
	//partial update
	@PatchMapping("event/{id}")
	public Event updatePartialEvent(@PathVariable("id") String id, 
									@RequestParam(required=false) String eventName, 
									@RequestParam(required=false) @DateTimeFormat(pattern="yyyyMMddHHmm")LocalDateTime startTime,
									@RequestParam(required=false) @DateTimeFormat(pattern="yyyyMMddHHmm")LocalDateTime endTime,
									@RequestParam(required=false) String description,
									@RequestParam(required=false) List<String> users){
		Event event = repo.findById(id).orElseThrow(()-> new EventNotFoundException());
		if(eventName != null) {
			event.setEventName(eventName);
		}if(startTime != null) {
			event.setStartTime(startTime);
		}if(endTime != null) {
			event.setEndTime(endTime);
		}if(description != null) {
			event.setDescription(description);
		}if(users != null) {
			event.setUsers(users);
		}
		
		event.setId(id);
		Duration dur = Duration.between(event.getStartTime(), event.getEndTime());
		double timeMin = dur.toMinutes();
		double time = timeMin/60;
		event.setDuration(time);
		return repo.save(event);
	}
	
	//delete
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/event/{id}") 
	public void deleteEvent(@PathVariable("id") String id) {
		repo.deleteById(id);
	}
	
	
}
