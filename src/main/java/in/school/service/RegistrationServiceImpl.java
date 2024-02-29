package in.school.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.school.DTOs.ForgotPasswordDto;
import in.school.DTOs.LogInDTO;
import in.school.DTOs.SignUpDto;
import in.school.entity.CityInfo;
import in.school.entity.Country_info;
import in.school.entity.StateInfo;
import in.school.entity.User_info;
import in.school.repo.CityInfoRepo;
import in.school.repo.CountryInfoRepo;
import in.school.repo.StateInfoRepo;
import in.school.repo.UserInfoRepo;
import in.school.utils.Utils;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	UserInfoRepo userRepo;
	CountryInfoRepo countryRepo;
	StateInfoRepo stateRepo;
	CityInfoRepo cityRepo;
	Utils utils;

	@Autowired
	public void setUserRepo(UserInfoRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	public void setCountryRepo(CountryInfoRepo countryRepo) {
		this.countryRepo = countryRepo;
	}

	@Autowired
	public void setStateRepo(StateInfoRepo stateRepo) {
		this.stateRepo = stateRepo;
	}

	@Autowired
	public void setCityRepo(CityInfoRepo cityRepo) {
		this.cityRepo = cityRepo;
	}

	@Autowired
	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	@Override
	public boolean signUp(SignUpDto dto) {
		User_info user_info = new User_info();
		BeanUtils.copyProperties(dto, user_info);
		user_info.setUpdatePassword("Y");
		user_info.setUpassword(utils.getPassword());
		User_info save = userRepo.save(user_info);
		String to = save.getUemail();
		String Subject = "my app password ";
		String body = "your password is : " + save.getUpassword();
		utils.sendEmail(Subject, body, to);
		return save.getId() == null ? false : true;
	}

	@Override
	public User_info logIn(LogInDTO dto) {
		return userRepo.findByUemailAndUpassword(dto.getEmail(), dto.getPassword());
	}

	@Override
	public boolean passwordCange(ForgotPasswordDto dto) {
		Optional<User_info> findById = userRepo.findById(dto.getUserId());
		if (findById.isPresent()) {
			User_info user = findById.get();
			user.setUpassword(dto.getSpassword());
			user.setUpdatePassword("N");
			userRepo.save(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepo.findByUemail(email);
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer, String> states = new HashMap<Integer, String>();
		List<StateInfo> state = stateRepo.findAllByCountyId(countryId);
		state.forEach(s -> {
			states.put(s.getStateId(), s.getStateName());
		});
		return states;
	}

	@Override
	public Map<Integer, String> getCitys(Integer stateId) {
		Map<Integer, String> citys = new HashMap<Integer, String>();
		List<CityInfo> city = cityRepo.findAllByStateId(stateId);
		city.forEach(s -> {
			citys.put(s.getCityId(), s.getCityName());
		});
		return citys;
	}

	@Override
	public Map<Integer, String> getCountrys() {
		Map<Integer, String> countrys = new HashMap<Integer, String>();
		List<Country_info> findAll = countryRepo.findAll();
		findAll.forEach(c -> {
			countrys.put(c.getCountryId(), c.getCountryName());
		});

		return countrys;
	}

}
