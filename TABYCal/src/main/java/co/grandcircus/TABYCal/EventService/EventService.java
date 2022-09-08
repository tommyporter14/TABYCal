package co.grandcircus.TABYCal.EventService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.TABYCal.EventModelFrontEnd.EventFrontEnd;

@Service
public class EventService {

	private RestTemplate rt = new RestTemplate();
	
	//should we use event or eventfrontend? Maybe change to array list?
	public EventFrontEnd[] getEvents() {
		String url = "http://localhost:8080/event/";
		EventFrontEnd[] events = rt.getForObject(url, EventFrontEnd[].class);
		return events;
	}
	
	//show by id
	public EventFrontEnd getEventById(String id) {
		String url = "http://localhost:8080/event/" + id;
		EventFrontEnd e = rt.getForObject(url, EventFrontEnd.class, id);
		return e;
	}
}
