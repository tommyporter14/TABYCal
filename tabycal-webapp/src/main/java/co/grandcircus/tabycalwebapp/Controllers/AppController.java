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
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import co.grandcircus.tabycalwebapp.Models.CurrentUser;
import co.grandcircus.tabycalwebapp.Models.DateTimeWrapper;
import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;
import co.grandcircus.tabycalwebapp.Models.Holiday;
import co.grandcircus.tabycalwebapp.Models.User;
import co.grandcircus.tabycalwebapp.Services.CurrentUserService;
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

	@Autowired
	private CurrentUserService currentUserService;


	// User login related mappings start/////////////////
	@RequestMapping("/")
	public String showLogin() {
		return "login";
	}


	@RequestMapping("/newaccount")
	public String enterDetails() {
		return "newaccount";
	}


	@RequestMapping("/verifyaccount")
	public ModelAndView verifyUser(@RequestParam String userName, Model model) {

		try {
			currentUserService.deleteCurrentUser();
			User userProfile = userService.getByUsername(userName);
			CurrentUser currentUser = new CurrentUser(userProfile.getUserName(),
					userProfile.getFirstName(), userProfile.getLastName(),
					userProfile.getDateOfBirth(), userProfile.getAdminStatus());
			model.addAttribute("currentUser", currentUser);
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return new ModelAndView("redirect:/");
		}
		System.out.println("MeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeowMeow");

		return new ModelAndView("redirect:/month-calendar", "model", model);

	}

	@PostMapping("/createuser")
	public String createUser(User newUser, Model model) {
		// Do we need to make an extra hop to call the User Service? Is it better
		// practice to call the UserController directly.
		try {
			ResponseEntity<User> responseEntity = userService.createUser(newUser);
			System.out.println("response to web app");
			System.out.println(responseEntity.getStatusCode());
			if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
				model.addAttribute("addedUser", responseEntity.getBody());
				return "successcreate";
			} else if (responseEntity.getStatusCode().equals(HttpStatus.FOUND)) {
				model.addAttribute("addedUser", newUser.getUserName());
				model.addAttribute("statusCode", responseEntity.getStatusCode());
				model.addAttribute("errorMsg",
						newUser.getUserName() + " already exists choose another email");


			} else if (responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				model.addAttribute("addedUser", newUser.getUserName());
				model.addAttribute("statusCode", responseEntity.getStatusCode());
				model.addAttribute("errorMsg", newUser.getUserName() + " is not a valid email");

			}
			System.out.println(model.toString());
			return "newaccount";
		} catch (Exception ex) {

			model.addAttribute("errorMsg", "Unhandled error occured please contact admin");
			// System.out.println("this is the model: " +model.asMap());
			return "newaccount";
		}
	}

	// User login related mappings end /////////////////



	@RequestMapping("/week/{date}")
	public String showWeek(@PathVariable String date, Model model) {
		LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
		List<DateTimeWrapper> weekList = new ArrayList<>();
		for (int i = 6; i >= 0; i--) {
			weekList.add(new DateTimeWrapper(dateTime.minusDays(i)));

		}
		List<EventFrontEnd> weekEventList = eventService.getEventsByStartDateAndEndDate(
				weekList.get(0).getDate(), weekList.get(weekList.size() - 1).getDate());
		WeekViewHelper eventsHelper = new WeekViewHelper(weekEventList);

		model.addAttribute("lastHour", eventsHelper.getLatestHour());
		model.addAttribute("earliestHour", eventsHelper.getEarliestHour());
		model.addAttribute("eventsHelper", eventsHelper);
		model.addAttribute("weekList", weekList);

		List<Holiday> holidayList = Arrays.asList(holidayService.getHolidays());
		HolidayHelper holidayHelper = new HolidayHelper(holidayList);
		model.addAttribute("holidayHelper", holidayHelper);

		return "week";
	}

	// will need to change as we go just here to test
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



	// DEFAULT will need to change as we go just here to test
	@RequestMapping("/month-calendar")
	public String showMonth(Model model) {
		System.out.println(
				"PEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEWPEW");

		EventFrontEnd[] events = eventService.getByUserName(currentUserService.getCurrentUser());

		Holiday[] holidays = holidayService.getHolidays();
		// System.out.print(events[0].getEventName());
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
			@RequestParam("start") @DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam("end") @DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
			@RequestParam String eventName, @RequestParam String description,
			@RequestParam List<String> users) {
		Double placeHolder = 0.0;
		EventFrontEnd event =
				new EventFrontEnd(eventName, description, start, end, placeHolder, users);
		try {
			model.addAttribute("event", eventService.createEvent(event));
		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
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
			@RequestParam("start") @DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam("end") @DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
			@RequestParam List<String> users) {

		EventFrontEnd[] eventsAll = eventService.getEvents();
		List<EventFrontEnd> eventsList = new ArrayList<>();

		for (String u : users) {
			for (int i = 0; i < eventsAll.length; i++) {
				if (eventsAll[i].getUsers().contains(u)) {
					eventsList.add(eventsAll[i]);
				}
			}
		}
		EventFrontEnd[] events = new EventFrontEnd[eventsList.size()];
		for (int i = 0; i < events.length; i++) {
			events[i] = eventsList.get(i);
		}
		// System.out.println(events.toString());
		HashMap<LocalDateTime, Double> map = new HashMap<>();
		for (int i = 0; i < events.length; i++) {
			if (events[i].getStartTime().isAfter(start.minusMinutes(1))
					&& events[i].getStartTime().isBefore(end.plusMinutes(1))) {
				if (map.containsKey(events[i].getStartTime())) {
					if (map.get(events[i].getStartTime()) <= events[i].getDuration()) {
						map.put(events[i].getStartTime(), events[i].getDuration());
					}
				} else {
					map.put(events[i].getStartTime(), events[i].getDuration());
				}
			} else if (events[i].getStartTime().isBefore(start)
					&& events[i].getEndTime().isAfter(end)) {
				map.put(LocalDateTime.MIN, 0.0);
				// System.out.println("!!!");
			}

			// WORKING ON ONE MORE FIX
			// else if(events[i].getStartTime().isEqual(start)
			// ||events[i].getEndTime().isEqual(end)) {
			// map.put(LocalDateTime.MAX, 0.0);
			// System.out.println("!!!");
			// }

		}
		TreeMap<LocalDateTime, Double> sortedMap = new TreeMap<>(map);
		ArrayList<LocalDateTime> startTimes = new ArrayList<>(sortedMap.keySet());
		ArrayList<Double> durations = new ArrayList<>(sortedMap.values());
		ArrayList<LocalDateTime> endTimes = new ArrayList<>();
		for (int i = 0; i < startTimes.size(); i++) {
			Long hourAdd = durations.get(i).longValue();
			double addHold = 0.0;
			if (durations.get(i) >= 1) {
				addHold = durations.get(i) * 60 - 60;
			} else {
				addHold = durations.get(i) * 60;
			}
			int minAdd = (int) addHold;
			endTimes.add(startTimes.get(i).plusHours(hourAdd).plusMinutes(minAdd));
		}
		TreeMap<LocalDateTime, LocalDateTime> sortedEndStartNoOvers = new TreeMap<>();
		for (int i = 0; i < startTimes.size(); i++) {
			if (sortedEndStartNoOvers.containsKey(endTimes.get(i))) {
				if (sortedEndStartNoOvers.get(endTimes.get(i)).isAfter(startTimes.get(i))) {
					sortedEndStartNoOvers.put(endTimes.get(i), startTimes.get(i));
				}
			} else {
				sortedEndStartNoOvers.put(endTimes.get(i), startTimes.get(i));
			}
		}

		ArrayList<LocalDateTime> endTimes2 = new ArrayList<>(sortedEndStartNoOvers.keySet());
		ArrayList<LocalDateTime> startTimes2 = new ArrayList<>(sortedEndStartNoOvers.values());

		TreeMap<LocalDateTime, LocalDateTime> available = new TreeMap<>();
		if (!startTimes.isEmpty()) {
			for (int i = 0; i <= startTimes2.size(); i++) {
				if (i == 0) {
					available.put(start, startTimes2.get(i));
				} else if (i == startTimes2.size()) {
					if (endTimes2.get(i - 1).isEqual(end)) {
						available.put(endTimes2.get(i - 1), startTimes2.get(i));
					} else {
						available.put(endTimes2.get(i - 1), end);
					}
				} else {
					if (startTimes2.get(i).equals(endTimes2.get(i - 1))) {
						available.put(endTimes2.get(i), startTimes2.get(i + 1));
					} else {
						available.put(endTimes2.get(i - 1), startTimes2.get(i));
					}
				}
			}
		}

		// System.out.print(available.toString());

		ArrayList<LocalDateTime> keys = new ArrayList<>(available.keySet());
		ArrayList<LocalDateTime> values = new ArrayList<>(available.values());
		String keysString = keys.toString();
		String valuesString = values.toString();
		String overallString = keysString + valuesString;


		if (available.isEmpty()) {
			model.addAttribute("message", "wide open");
		} else if (overallString.contains(LocalDateTime.MIN.toString())) {
			model.addAttribute("message", "no availability");
		} else {
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
