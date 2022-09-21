package co.grandcircus.tabycalwebapp.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import co.grandcircus.tabycalwebapp.Models.User;

@Service
public class UserService {

	@Value("${tabycalapis.url}")
	private String apiUrl;
	
	
	
	private RestTemplate request = new RestTemplate();

	public User[] getAll() {
		// String url = "/users";
		String url = apiUrl + "/users";
		final User[] response = request.getForObject(url, User[].class);
		return response;
	}

	public User getByUsername(String userName) {
		String url = apiUrl + "/userprofile/?userName={0}";
		final User response = request.getForObject(url, User.class, userName);
		return response;
	}

	public ResponseEntity<User> createUser(User newUser) {
		String url = apiUrl + "/users/create";
		HttpEntity<User> entittyBody = new HttpEntity<>(newUser);
		System.out.println("made it to the service");
		return request.exchange(url, HttpMethod.POST, entittyBody, User.class);

	}
}
