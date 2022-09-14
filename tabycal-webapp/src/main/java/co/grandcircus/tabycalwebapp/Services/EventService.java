package co.grandcircus.tabycalwebapp.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;
import co.grandcircus.tabycalwebapp.Models.EventResponse;
import co.grandcircus.tabycalwebapp.Models.User;



@Service
public class EventService {

	private RestTemplate rt = new RestTemplate();
	
	public EventFrontEnd[] getEvents() {
		String url = "http://localhost:8081/event/";
		EventFrontEnd[] events = rt.getForObject(url, EventFrontEnd[].class);
		return events;
	}
	
	//maybe allow for list instead of array?
	public List<EventFrontEnd> getEventResponse() {
		String url = "http://localhost:8081/event/";
		EventResponse events = rt.getForObject(url, EventResponse.class);
		return events.getEvents();
	}
	
	//show by id
	public EventFrontEnd getEventById(String id) {
		String url = "http://localhost:8081/event/" + id;
		EventFrontEnd e = rt.getForObject(url, EventFrontEnd.class, id);
		return e;
	}
	
	//create event
	 public EventFrontEnd createEvent(EventFrontEnd newEvent){
	        String url = "http://localhost:8081/event/";
	        final EventFrontEnd response = rt.postForObject(url, rt, EventFrontEnd.class, newEvent);
	        rt.postForEntity(url, response, EventFrontEnd.class);
	        return response;
	    }
}
