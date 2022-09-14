package co.grandcircus.tabycalwebapp.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.tabycalwebapp.Models.Holiday;



@Service
public class HolidayService {
	
		private RestTemplate restTemplate = new RestTemplate();

		
		public Holiday[] getHolidays() {
			String url = "https://date.nager.at/api/v3/PublicHolidays/2022/US";
			Holiday[] response = restTemplate.getForObject(url, Holiday[].class);
			return response;
		}
	
	

}