package in.school.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	public String getPassword() {
		return UUID.randomUUID().toString();
	}
	public boolean sendEmail (String subject , String body , String to ) {
		
		return false ;
	}
}
