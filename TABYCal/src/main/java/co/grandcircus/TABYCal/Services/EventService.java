package co.grandcircus.TABYCal.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.TABYCal.Models.Event;
import co.grandcircus.TABYCal.Models.EventFrontEnd;
import co.grandcircus.TABYCal.Models.User;

@Service
public class EventService {

	private RestTemplate rt = new RestTemplate();
	
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
