package co.grandcircus.tabycalwebapp.Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

	// maybe allow for list instead of array?
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
		return getEventsByStartDateAndEndDate(date, date);
	}

	public List<EventFrontEnd> getEventsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		return getEventsByStartDateAndEndDateAndUser(startDate, endDate, null);
	}
	public List<EventFrontEnd> getEventsByStartDateAndEndDateAndUser(LocalDate startDate,LocalDate endDate, String userName) {
		String url = "http://localhost:8081/event/";
		Map<String, String> params = new HashMap<>();
		String fixedStartDate = startDate.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String fixedEndDate = endDate.atTime(23, 59).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		params.put("startDate", fixedStartDate);
		params.put("endDate", fixedEndDate);
		params.put("userName", userName);

		String queryString;

		if (userName == null) {
			queryString = "?startDate={startDate}&endDate={endDate}";
		} else {
			queryString = "?startDate={startDate}&endDate={endDate}&users={userName}";
		}

		EventFrontEnd[] events = rt.exchange(url + queryString, HttpMethod.GET,
				new HttpEntity<>(new HashMap<>()), EventFrontEnd[].class, params).getBody();
		return Arrays.asList(events);
	}

	public EventFrontEnd[] getByUserName(String user){
		String url = "http://localhost:8081/event/{user}";
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

	// update event
	public ResponseEntity<EventFrontEnd> updateEvent(EventFrontEnd updateEvent, String id) {
		String url = "http://localhost:8081/event/put/{0}";
		HttpEntity<EventFrontEnd> entityBody = new HttpEntity<>(updateEvent);
		System.out.println("after response");
		ResponseEntity<EventFrontEnd> response =  rt.exchange(url, HttpMethod.PUT, entityBody, EventFrontEnd.class,id);
		return response ;
	}
}