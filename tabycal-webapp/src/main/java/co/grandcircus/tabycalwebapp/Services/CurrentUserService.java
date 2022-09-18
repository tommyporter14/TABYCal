package co.grandcircus.tabycalwebapp.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import co.grandcircus.tabycalwebapp.Models.CurrentUser;

@Service
public class CurrentUserService {
    RestTemplate request = new RestTemplate();

    public CurrentUser setCurrentUser(CurrentUser currentUser) {
        String url = "http://localhost:8081/setcurrentuser";
        CurrentUser response =  request.postForObject(url, currentUser, CurrentUser.class) ;
        System.out.println(response.toString());
        return response;
    }

    public String deleteCurrentUser() {
        String url = "http://localhost:8081/deletecurrentuser";
        request.delete(url);
        return "current user is cleared";
    }

    public String getCurrentUser() {
        String url = "http://localhost:8081/currentuser";
        return request.getForObject(url, String.class);
    }

}
