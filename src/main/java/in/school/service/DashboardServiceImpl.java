package in.school.service;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.school.DTOs.Quote;

@Service
public class DashboardServiceImpl implements DashBoardService {
	private String url = "https://type.fit/api/quotes";

	private Quote[] quotes = null;
	Random r = new Random();

	@Override
	public String getQuate() {
		if (quotes == null) {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> enitity = rt.getForEntity(url, String.class);

			String body = enitity.getBody();
			ObjectMapper om = new ObjectMapper();
			try {
				quotes = om.readValue(body, Quote[].class);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			int i = r.nextInt(quotes.length - 1);
			Quote quote = quotes[i];
			return quote.getText();
		}
		return null ;
	}

}
