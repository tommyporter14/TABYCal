package co.grandcircus.tabycalwebapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.grandcircus.tabycalwebapp.Services.HolidayService;


@Controller
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;
	
	@RequestMapping ("/holiday")
	private String showHoliday(Model model) {
		model.addAttribute("holidays", holidayService.getHolidays());
		return "holiday";
	
	}

}
