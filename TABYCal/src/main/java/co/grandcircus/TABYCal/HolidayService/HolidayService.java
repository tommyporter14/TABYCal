package co.grandcircus.TABYCal.HolidayService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.TABYCal.HolidayResponse.HolidayResponse;

@Service
public class HolidayService {
	
		private RestTemplate restTemplate = new RestTemplate();
		@Value("${key}")
		private String key;
		
		public HolidayResponse getHolidays() {
			String url = "https://holidays.abstractapi.com/v1/?api_key={0}&country=US";
			HolidayResponse response = restTemplate.getForObject(url,  HolidayResponse.class, key);
			return response;
		}
	
	

}
