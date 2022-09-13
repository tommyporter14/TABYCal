package co.grandcircus.tabycalwebapp.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.grandcircus.tabycalwebapp.Models.DateTimeWrapper;
import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;
import co.grandcircus.tabycalwebapp.Services.EventService;
import co.grandcircus.tabycalwebapp.Util.WeekViewHelper;

@Controller
public class AppController {

	@Autowired
	private EventService eventService;

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

}
