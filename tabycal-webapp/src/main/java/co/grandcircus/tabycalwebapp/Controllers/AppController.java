package co.grandcircus.tabycalwebapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	// Log In Page
	@RequestMapping("/")
	public String showLogin() {
		return "login";
	}
	

}
