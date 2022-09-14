package co.grandcircus.tabycalwebapp.Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;
import co.grandcircus.tabycalwebapp.Models.EventResponse;

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
	
	public EventFrontEnd getEventById(String id) {
		String url = "http://localhost:8081/event/find/"+id;
		EventFrontEnd event = rt.getForObject(url, EventFrontEnd.class, id);
		return event;
	}

	public List<EventFrontEnd> getEventsByDate(LocalDate date) {
		String url = "http://localhost:8081/event/";
		Map<String, String> params = new HashMap<>();
		String startDate = date.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String endDate = date.atTime(23, 59).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		EventFrontEnd[] events = rt.exchange(url + "?startDate={startDate}&endDate={endDate}", HttpMethod.GET,
				new HttpEntity<>(new HashMap<>()), EventFrontEnd[].class, params).getBody();
		return Arrays.asList(events);
	}
	
	public List<EventFrontEnd> getEventsByStartDateAndEndDate(LocalDate startDate,LocalDate endDate) {
		String url = "http://localhost:8081/event/";
		Map<String, String> params = new HashMap<>();
		String fixedStartDate = startDate.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String fixedEndDate = endDate.atTime(23, 59).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		params.put("startDate", fixedStartDate);
		params.put("endDate", fixedEndDate);
		EventFrontEnd[] events = rt.exchange(url + "?startDate={startDate}&endDate={endDate}", HttpMethod.GET,
				new HttpEntity<>(new HashMap<>()), EventFrontEnd[].class, params).getBody();
		return Arrays.asList(events);
	}

	public EventFrontEnd[] getByUserName(String user){
		String url = "http://localhost:8081/event/?user={0}";
		EventFrontEnd[] e = rt.getForObject(url, EventFrontEnd[].class, user);
		return e;
	}
	
	//create event
	 public EventFrontEnd createEvent(EventFrontEnd newEvent){
	        String url = "http://localhost:8081/event/create";
	        final EventFrontEnd response = rt.postForObject(url, newEvent, EventFrontEnd.class);
	        return response;
	    }
	 
	 //delete event
	 public void deleteEvent(String id) {
		 String url = "http://localhost:8081/event/" + id;
		 rt.delete(url);
	 }

}
