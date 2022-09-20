package co.grandcircus.tabycalwebapp.Services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.tabycalwebapp.Models.Holiday;



@Service
public class HolidayService {
	
		private RestTemplate restTemplate = new RestTemplate();

		
		public Holiday[] getHolidays() {
			LocalDateTime currentDate = LocalDateTime.now();
			int currentYear = currentDate.getYear();
			String url = "https://date.nager.at/api/v3/PublicHolidays/{0}/US";
			Holiday[] response = restTemplate.getForObject(url, Holiday[].class, currentYear);
			return response;
		}
	
	

}
