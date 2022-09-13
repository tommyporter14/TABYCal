package co.grandcircus.tabycalwebapp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.tabycalwebapp.Models.EventFrontEnd;
import co.grandcircus.tabycalwebapp.Models.User;
import co.grandcircus.tabycalwebapp.Services.EventService;
import co.grandcircus.tabycalwebapp.Services.UserService;

@Controller
public class AppController {

	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;

	// Log In Page
	@RequestMapping("/")
	public String showLogin() {
		return "login";
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
