package co.grandcircus.TABYCal.AppController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.TABYCal.EventModelFrontEnd.EventFrontEnd;
import co.grandcircus.TABYCal.EventService.EventService;
import co.grandcircus.TABYCal.UserController.UserController;
import co.grandcircus.TABYCal.UserModel.User;
//import co.grandcircus.TABYCal.UserService.UserService;

@Controller
public class AppController {

	// @Autowired
	// private UserService userService;

	@Autowired
	private UserController userController;

	@Autowired
	private EventService es;

	// Log In Page
	@RequestMapping("/")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/newaccount")
	public String enterDetails() {
		return "newaccount";
	}

	@PostMapping("/verifyaccount")
	public String verifyUser(@RequestParam String userName, Model model){
		try{
			model.addAttribute("userProfile", userController.readByUserName(userName));
		}
		catch(Exception ex){
			System.out.println(ex.toString());
			return "redirect:/";
		}
		
		return "myhome";

	}

	// return to the log in page but we can have a "success page" then direct to log
	// in
	@PostMapping("/createuser")
	public String createUser(User newUser, Model model) {
		// Do we need to make an extra hop to call the User Service? Is it better
		// practice to call the UserController directly.
		try{
		model.addAttribute("addedUser", userController.createUser(newUser));
		// model.addAttribute("addedUser", userService.createUser(newUser));
		}catch (Exception ex){
			model.addAttribute("userName", newUser.getUserName());
			return "redirect:/newaccount";
		}
		return "successcreate";
	}

	// DEFAULT will need to change as we go just here to test
	@RequestMapping("/test")
	public String showMonth(Model model) {

		EventFrontEnd[] events = es.getEvents();
		System.out.print(events[0].getEventName());
		model.addAttribute("events", events);
		return "month";
	}

	// will need to change as we go just here to test
	@RequestMapping("/month")
	public String showMonth() {
		return "month";
	}

	// @RequestMapping("/")
	// public String showHome(Model model) {
	// 	EventFrontEnd[] events = es.getEvents();
	// 	model.addAttribute("events",events);
	// 	return "month";
	// }
		
	@RequestMapping("/event-overview")
	public String showEventOverview(Model model, @RequestParam String id) {
		model.addAttribute("event", es.getEventById(id));
		return "event-overview";
	}
	
	@RequestMapping("/check-availability")
	public String showCheckAvailability() {
		return "check-availability";
	}
	
	//currently working on all events, need to talk to Yaksh to make it user specific
	@RequestMapping("/availability")
	public String showAvailability(Model model, 
			@RequestParam("start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam("end")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
		EventFrontEnd[] events = es.getEvents();
		HashMap<LocalDateTime, Double> map = new HashMap<>();
		for(int i= 0; i < events.length;i++) {
			if(events[i].getStartTime().isAfter(start.minusMinutes(1)) && events[i].getStartTime().isBefore(end.plusMinutes(1))) {
				if(map.containsKey(events[i].getStartTime())){
					if(map.get(events[i].getStartTime())<=events[i].getDuration()){
						map.put(events[i].getStartTime(), events[i].getDuration());
					}else {
						//do nothing
					}
				}else {
					map.put(events[i].getStartTime(),events[i].getDuration());
				}
			}
		}
		TreeMap<LocalDateTime, Double> sortedMap = new TreeMap<>(map);
		ArrayList<LocalDateTime> startTimes = new ArrayList<>(sortedMap.keySet());
		ArrayList<Double> durations = new ArrayList<>(sortedMap.values());
		ArrayList<LocalDateTime> endTimes = new ArrayList<>();
		for(int i = 0; i < startTimes.size(); i ++) {
			Long hourAdd = durations.get(i).longValue();
			double addHold = durations.get(i)*60-60;
			int minAdd = (int) addHold;
			endTimes.add(startTimes.get(i).plusHours(hourAdd).plusMinutes(minAdd));
		}
		TreeMap<LocalDateTime, LocalDateTime> available = new TreeMap<>();
		for(int i = 0; i < startTimes.size(); i ++) {
			if(i==0) {
				available.put(start, startTimes.get(i));
			}else if(i!=0 && i < startTimes.size()-1) {
				if(startTimes.get(i).equals(endTimes.get(i-1))) {
					available.put(endTimes.get(i), startTimes.get(i+1));
				}else {
					available.put(endTimes.get(i-1), startTimes.get(i));
				}	
			}else if (endTimes.get(i).isEqual(end)){
				available.put(endTimes.get(i-1), startTimes.get(i));
			}else{
				available.put(endTimes.get(i), end);
			}
			
		}
	
		//testing
		//System.out.println(map);
		//System.out.println(sortedMap);
		//System.out.println(endTimes);
		//System.out.println(available);
		//System.out.println(events);
		//System.out.println(start);
		//System.out.println(end);
		model.addAttribute("available", available);
		return "availability";
	}

	// will need to change as we go just here to test
	@RequestMapping("/week")
	public String showWeek() {
		return "week";
	}

	// will need to change as we go just here to test
	@RequestMapping("/day")
	public String showDay() {
		return "day";
	}
	
	
}