package co.grandcircus.TABYCal.AppController;

import org.springframework.beans.factory.annotation.Autowired;
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
import co.grandcircus.TABYCal.UserService.UserService;

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