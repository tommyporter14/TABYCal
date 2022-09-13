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
        String url = "http://localhost:8081/users/{id}";
        final User response = request.getForObject(url, User.class, userName);
        return response;
    }

    public User createUser(User newUser){
        System.out.println("in create user service");
        String url = "http://localhost:8081/users/create";
        final User response = request.postForObject(url, request, User.class, newUser);
        return response;
    }
}
