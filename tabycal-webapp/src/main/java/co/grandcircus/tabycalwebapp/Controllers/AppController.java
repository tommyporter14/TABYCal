package co.grandcircus.tabycalwebapp.Controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.tabycalwebapp.Models.DateTimeWrapper;
import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;
import co.grandcircus.tabycalwebapp.Models.Holiday;
import co.grandcircus.tabycalwebapp.Models.User;
import co.grandcircus.tabycalwebapp.Services.EventService;
import co.grandcircus.tabycalwebapp.Services.HolidayService;
import co.grandcircus.tabycalwebapp.Services.UserService;
import co.grandcircus.tabycalwebapp.Util.HolidayHelper;
import co.grandcircus.tabycalwebapp.Util.WeekViewHelper;


@Controller
public class AppController {


	@Autowired
	private EventService eventService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private HolidayService holidayService;

	// Log In Page
	@RequestMapping("/")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/week/{date}")
	public String showWeek(@PathVariable String date, Model model) {
		LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
		List<DateTimeWrapper> weekList = new ArrayList<>();
		for (int i = 6; i >= 0; i--) {
			weekList.add(new DateTimeWrapper(dateTime.minusDays(i)));

		}
		List<EventFrontEnd> weekEventList = eventService.getEventsByStartDateAndEndDate(weekList.get(0).getDate(),
				weekList.get(weekList.size() - 1).getDate());
		WeekViewHelper eventsHelper = new WeekViewHelper(weekEventList);
		
		model.addAttribute("lastHour", eventsHelper.getLatestHour());
		model.addAttribute("earliestHour",eventsHelper.getEarliestHour());
		model.addAttribute("eventsHelper", eventsHelper);
		model.addAttribute("weekList", weekList);
		
		List<Holiday> holidayList = Arrays.asList(holidayService.getHolidays());
		HolidayHelper holidayHelper = new HolidayHelper(holidayList);
		model.addAttribute("holidayHelper", holidayHelper);
		
		return "week";
	}

	@RequestMapping("/day/{date}")
	public String showDay(@PathVariable String date, Model model) {

		LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
		model.addAttribute("stringDate", dateTime.format(DateTimeFormatter.ofPattern("E, M d")));
		model.addAttribute("day", dateTime.getDayOfMonth());
		model.addAttribute("dayOfWeek", dateTime.getDayOfWeek());
		model.addAttribute("month", dateTime.getMonth());

		List<EventFrontEnd> eventData = eventService.getEventsByDate(dateTime);
		model.addAttribute("listOfDayEvents", eventData);
		
		List<Holiday> holidayList = Arrays.asList(holidayService.getHolidays());
		HolidayHelper holidayHelper = new HolidayHelper(holidayList);
		model.addAttribute("holidayHelper", holidayHelper);
		model.addAttribute("dateTime", dateTime);

		return "day";
	}
	@RequestMapping("/newaccount")
	public String enterDetails() {
		return "newaccount";
	}

	@RequestMapping("/verifyaccount")
	public String verifyUser(@RequestParam String userName, Model model) {
		try {
			User userProfile = userService.getByUsername(userName);
			model.addAttribute("userProfile", userProfile);
			EventFrontEnd[] userEvents = eventService.getByUserName(userName);
			model.addAttribute("userEvents", userEvents );
	} catch (Exception ex) {
		System.out.println(ex.toString());
		return "redirect:/";
	}

	return "month";

}	

	// return to the log in page but we can have a "success page" then direct to log
	@PostMapping("/createuser")
	public String createUser(User newUser, Model model) {
		// Do we need to make an extra hop to call the User Service? Is it better
		// practice to call the UserController directly.
		try {
			User addedUser = userService.createUser(newUser);
			model.addAttribute("addedUser", addedUser);
			// model.addAttribute("addedUser", userService.createUser(newUser));
		} catch (Exception ex) {
			model.addAttribute("userName", newUser.getUserName());
			// System.out.println("this is the model: " +model.asMap());
			return "newaccount";
		}
		return "successcreate";
	}

	@RequestMapping("/month-calendar")
	public String showMonth(Model model) {

		EventFrontEnd[] events = eventService.getEvents();
		Holiday[] holidays = holidayService.getHolidays();
		model.addAttribute("events", events);
		model.addAttribute("holidays", holidays);
		return "month";
	}
	
	@RequestMapping("/create-event")
	public String showCreateEvent(Model model) {
		User[] userList = userService.getAll();
	    model.addAttribute("users", userList);
		return "create-event";
	}
		
	@RequestMapping("/event-created")
	public String showEventCreated(Model model,
			@RequestParam("start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam("end")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
			@RequestParam String eventName,
			@RequestParam String description,
			@RequestParam List<String> users) {
		Double placeHolder = 0.0;
		EventFrontEnd event = new EventFrontEnd(eventName, description, start, end, placeHolder, users);
		try {
			model.addAttribute("event", eventService.createEvent(event));
		}catch(Exception ex){
			//System.out.println(ex.getMessage());
			User[] userList = userService.getAll();
		    model.addAttribute("users", userList);
			model.addAttribute("message", "Error: overlapping events for user");
			return "create-event";
		}
		
		return "event-created";
	}
	
	@RequestMapping("/event-overview")
	public String showEventOverview(Model model, @RequestParam String id) {
		model.addAttribute("event", eventService.getEventById(id));
		return "event-overview";
	}
	@RequestMapping("/successfully-deleted")
	public String showSuccessfullyDeleted(@RequestParam String id) {
		eventService.deleteEvent(id);
		return "successfully-deleted";
	}
	// Schedule Finder Service (Web Service)

	@RequestMapping("/check-availability")
    public String showCheckAvailability(Model model) {
        User[] userList = userService.getAll();
        model.addAttribute("users", userList);
        return "check-availability";
    }

	@RequestMapping("/availability")
	public String showAvailability(Model model,
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
			@RequestParam List<String> users) {

		//gets all events that exist in db
		EventFrontEnd[] eventsAll = eventService.getEvents();
		
		//blank events list
		List<EventFrontEnd> eventsList = new ArrayList<>();

		//fills event list with events correlated to user name
		for (String u : users) {
			for (int i = 0; i < eventsAll.length; i++) {
				if (eventsAll[i].getUsers().contains(u)) {
					eventsList.add(eventsAll[i]);
				}
			}
		} 
		//converts events for user to array
		EventFrontEnd[] events = new EventFrontEnd[eventsList.size()];
		for (int i = 0; i < events.length; i++) {
			events[i] = eventsList.get(i);
		}	
		
		TreeMap<LocalDateTime, LocalDateTime> relaventEvents= new TreeMap<>();
		for (int i = 0; i < events.length; i++) {
			if (
					!((events[i].getStartTime().isBefore(start) 
					&& (events[i].getEndTime().isBefore(start) || events[i].getEndTime().equals(start))) 
					|| ((events[i].getStartTime().isAfter(end) || events[i].getStartTime().equals(end)) 
					&& events[i].getEndTime().isAfter(end)))
				){
				
				relaventEvents.put(events[i].getStartTime(), events[i].getEndTime());
				
			}
		}
		
		TreeMap<LocalDateTime, LocalDateTime> available = new TreeMap<>();
		
		System.out.println(relaventEvents.toString());
		if(!relaventEvents.isEmpty()) {
			int index = 0;
			for(Map.Entry<LocalDateTime,LocalDateTime> event : relaventEvents.entrySet()) {
				
				//3-5, event is 4 -5, 3-4
				if((event.getKey().isAfter(start) || event.getKey().equals(start)) && (event.getValue().isBefore(end)|| event.getValue().equals(end))){
					//if there is only 1 event do this
					if(relaventEvents.size()==1) {
						if((event.getKey().equals(start)) 
								&& 
								(event.getValue().equals(end))
								){
						}else if((event.getKey().equals(start))
								&& event.getValue().isBefore(end)){
							available.put(event.getValue(), end);
						}else if((event.getValue().equals(end))
								&& event.getKey().isAfter(start)) {
							available.put(start, event.getKey());
						}else {
							available.put(start, event.getKey());
							available.put(event.getValue(), end);
						}
					//if more than 1 event
					}else {
						if((event.getKey().equals(start))
								&& event.getValue().isBefore(end)){
							if(index+1 == relaventEvents.size()) {
								available.put(event.getValue(), end);
							}else if(index==0) {
								available.put(event.getValue(), relaventEvents.higherEntry(event.getKey()).getValue());
							}
							else {
							available.put(event.getValue(), relaventEvents.higherKey(event.getKey()));
							}
						}else if((event.getValue().equals(end))
								&& event.getKey().isAfter(start)) {
							available.put(relaventEvents.lowerEntry(event.getKey()).getValue(), event.getKey());
						}else {
							if(index+1 == relaventEvents.size()){
								available.put(event.getValue(), end);
							}else if(index ==0) {
								available.put(start, event.getKey());
								available.put(event.getValue(),relaventEvents.higherKey(event.getKey()));
							}
							else {
							available.put(relaventEvents.lowerEntry(event.getKey()).getValue(), event.getKey());
							available.put(event.getValue(), relaventEvents.higherEntry(event.getKey()).getKey());
							}
						}
					}
				}
			index++;
			}
		}
		
		ArrayList<LocalDateTime> keys = new ArrayList<>(available.keySet());
		ArrayList<LocalDateTime> values = new ArrayList<>(available.values());
		
		if(relaventEvents.isEmpty()) {
			model.addAttribute("message","wide open");
		}
		else if(available.isEmpty()) {
			model.addAttribute("message", "no availability");
		}
		else {
			model.addAttribute("available", available);
		}
		
			return "availability";
	}
	
	@RequestMapping("/day")
	public String showCurrentDay() {
		Date currentDate = new Date();
		String currentDateYearMonthDay = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
		return "redirect:/day/" + currentDateYearMonthDay;
	}

}
