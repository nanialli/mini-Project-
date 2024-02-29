package in.school.service;

import java.util.Map;

import in.school.DTOs.ForgotPasswordDto;
import in.school.DTOs.LogInDTO;
import in.school.DTOs.SignUpDto;
import in.school.entity.User_info;

public interface RegistrationService {
	
	
	public boolean signUp(SignUpDto dto);

	public User_info logIn(LogInDTO dto);

	public boolean passwordCange(ForgotPasswordDto dto);

	public boolean checkEmail(String email);
	
	public Map<Integer , String > getCountrys () ;

	public Map<Integer, String> getStates(Integer countryId);

	public Map<Integer, String> getCitys(Integer stateId);
}
