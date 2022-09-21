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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;
import co.grandcircus.tabycalwebapp.Models.EventResponse;

@Service
public class EventService {
	
	@Value("${tabycalapis.url}")
	private String apiUrl;
	
	private RestTemplate rt = new RestTemplate();

	public EventFrontEnd[] getEvents() {
		String url = apiUrl + "/event/";
		EventFrontEnd[] events = rt.getForObject(url, EventFrontEnd[].class);
		return events;
	}
	
	//maybe allow for list instead of array?
	public List<EventFrontEnd> getEventResponse() {
		String url = apiUrl + "/event/";
		EventResponse events = rt.getForObject(url, EventResponse.class);
		return events.getEvents();
	}
	
	public EventFrontEnd getEventById(String id) {
		String url = apiUrl + "/event/find/"+id;
		EventFrontEnd event = rt.getForObject(url, EventFrontEnd.class, id);
		return event;
	}

	public List<EventFrontEnd> getEventsByDate(LocalDate date) {
		return getEventsByStartDateAndEndDate(date, date);
	}
	
	public List<EventFrontEnd> getEventsByStartDateAndEndDate(LocalDate startDate,LocalDate endDate) {
		return getEventsByStartDateAndEndDateAndUser(startDate, endDate, null);
	}
	public List<EventFrontEnd> getEventsByStartDateAndEndDateAndUser(LocalDate startDate,LocalDate endDate, String userName) {
		String url = apiUrl + "/event/";
		Map<String, String> params = new HashMap<>();
		String fixedStartDate = startDate.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String fixedEndDate = endDate.atTime(23, 59).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		params.put("startDate", fixedStartDate);
		params.put("endDate", fixedEndDate);
		params.put("userName", userName);
		
		String queryString;
		
		if(userName == null) {
			queryString = "?startDate={startDate}&endDate={endDate}";
		}else {
			queryString = "?startDate={startDate}&endDate={endDate}&users={userName}";
		}
		
		EventFrontEnd[] events = rt.exchange(url + queryString, HttpMethod.GET,
				new HttpEntity<>(new HashMap<>()), EventFrontEnd[].class, params).getBody();
		return Arrays.asList(events);
	}

	public EventFrontEnd[] getByUserName(String user){
		String url = apiUrl + "/event/{user}";
		EventFrontEnd[] e = rt.getForObject(url, EventFrontEnd[].class, user);
		return e;
	}
	
	//create event
	 public EventFrontEnd createEvent(EventFrontEnd newEvent){
	        String url = apiUrl + "/event/create";
	        final EventFrontEnd response = rt.postForObject(url, newEvent, EventFrontEnd.class);
	        return response;
	    }
	 
	 //delete event
	 public void deleteEvent(String id) {
		 String url = apiUrl + "/event/" + id;
		 rt.delete(url);
	 }

}