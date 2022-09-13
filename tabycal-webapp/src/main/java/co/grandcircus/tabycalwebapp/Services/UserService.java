package co.grandcircus.tabycalwebapp.Services;



import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.tabycalwebapp.Models.User;



@Service
public class UserService {
    private RestTemplate request = new RestTemplate();

    public User[] getAll() {
        // String url = "/users";
        String url = "http://localhost:8081/users";
        final User[] response = request.getForObject(url, User[].class);
        return response;
    }

    public User getByUsername(String userName) {
        String url = "http://localhost:8081/userprofile/?userName={0}";
        System.out.println(userName);
        System.out.println(url);
        final User response = request.getForObject(url, User.class, userName);
        return response;
    }

    public User createUser(User newUser){
        String url = "http://localhost:8081/users/create";
        System.out.println(newUser.toString());
        final User response = request.postForObject(url, newUser, User.class);
        System.out.println("in create user service");
        return response;
    }
}
