package co.grandcircus.tabycalwebapp.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;



@Service
public class EventService {

	private RestTemplate rt = new RestTemplate();
	
	public EventFrontEnd[] getEvents() {
		String url = "http://localhost:8081/event/";
		EventFrontEnd[] events = rt.getForObject(url, EventFrontEnd[].class);
		return events;
	}
	
	//show by id
	public EventFrontEnd getEventById(String id) {
		String url = "http://localhost:8081/event/" + id;
		EventFrontEnd e = rt.getForObject(url, EventFrontEnd.class, id);
		return e;
	}
}
