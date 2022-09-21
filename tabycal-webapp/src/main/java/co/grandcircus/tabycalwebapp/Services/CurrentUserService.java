package co.grandcircus.tabycalwebapp.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import co.grandcircus.tabycalwebapp.Models.CurrentUser;

@Service
public class CurrentUserService {
	
	@Value("${tabycalapis.url}")
	private String apiUrl;
	
    RestTemplate request = new RestTemplate();

    public CurrentUser setCurrentUser(CurrentUser currentUser) {
        String url = apiUrl + "/setcurrentuser";
        CurrentUser response =  request.postForObject(url, currentUser, CurrentUser.class) ;
        System.out.println(response.toString());
        return response;
    }

    public String deleteCurrentUser() {
        String url = apiUrl + "/deletecurrentuser";
        request.delete(url);
        return "current user is cleared";
    }

    public String getCurrentUser() {
        String url = apiUrl + "/currentuser";
        return request.getForObject(url, String.class);
    }

}
