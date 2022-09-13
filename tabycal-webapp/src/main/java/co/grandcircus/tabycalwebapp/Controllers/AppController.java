package co.grandcircus.tabycalwebapp.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.grandcircus.tabycalwebapp.Models.User;
import co.grandcircus.tabycalwebapp.Services.UserService;

import co.grandcircus.tabycalwebapp.Models.DateTimeWrapper;
import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;
import co.grandcircus.tabycalwebapp.Services.EventService;
import co.grandcircus.tabycalwebapp.Util.WeekViewHelper;

@Controller
public class AppController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	UserService userService;

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
	// in
	@PostMapping("/createuser")
	public String createUser(User newUser, Model model) {
		// Do we need to make an extra hop to call the User Service? Is it better
		// practice to call the UserController directly.
		try {
			model.addAttribute("addedUser", userService.createUser(newUser));
			System.out.println("created a user yay!");
			// model.addAttribute("addedUser", userService.createUser(newUser));
		} catch (Exception ex) {
			model.addAttribute("userName", newUser.getUserName());
			return "redirect:/newaccount";
		}
		return "successcreate";
	}

}
