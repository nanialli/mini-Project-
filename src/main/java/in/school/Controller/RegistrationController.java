package in.school.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import in.school.DTOs.ForgotPasswordDto;
import in.school.DTOs.LogInDTO;
import in.school.DTOs.SignUpDto;
import in.school.entity.User_info;
import in.school.service.RegistrationServiceImpl;

@Controller
public class RegistrationController {

	@Autowired
	RegistrationServiceImpl rservice;

	@GetMapping("/login")
	public String logIn(Model model) {
		model.addAttribute("logInDto", new LogInDTO());
		return "logIn";
	}

	@PostMapping("/login")
	public String logIn(@ModelAttribute("logInDto") LogInDTO dto, Model model) {
		User_info logIn = rservice.logIn(dto);
		if (logIn == null) {
			model.addAttribute("errMsg", "Invalid credentials");
			return "login";
		}
		if (logIn.getUpdatePassword().equals("Y")) {

			ForgotPasswordDto passwordDto = new ForgotPasswordDto();
			passwordDto.setUserId(logIn.getId());
			model.addAttribute("resetPasswordDto", passwordDto);
			return "resetPassword";

		}

		return "redirect:/dashboard";
	}

	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("signUpDto", new SignUpDto());
		Map<Integer, String> countrys = rservice.getCountrys();
		model.addAttribute("countrys", countrys);
		return "signUp";

	}

	@PostMapping("/signup")
	public String signUp(@ModelAttribute("signUpDto") SignUpDto dto, Model model) {
		boolean validEmail = rservice.checkEmail(dto.getUemail());
		if (!validEmail) {
			model.addAttribute("invalidEmail", "Email allradey exists try agian with another email ");
			return "redirect:/signup";
		}
		boolean signUpStatus = rservice.signUp(dto);
		if (!signUpStatus) {
			model.addAttribute("errMsg", "registration failed");
			return "signUp";

		} else {
			model.addAttribute("susMsg", "Password Send to your email ");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/states")
	@ResponseBody
	public Map<Integer,String > getStates ( @ModelAttribute("countryId") int countryId  ){
		Map<Integer, String> states = rservice.getStates(countryId);
		return states ;
	}
	
	@GetMapping("/citys")
	@ResponseBody
	public Map<Integer,String > getCitys ( @ModelAttribute("stateId") Integer stateId  ){
		Map<Integer, String> citys = rservice.getCitys(stateId);
		return citys ;
	}

	@PostMapping("/resetpassword")
	public String resetPassword(@ModelAttribute("resetPassword") ForgotPasswordDto passwordDto, Model model) {
		if (passwordDto.getFpassword().equals(passwordDto.getSpassword())) {

			boolean passwordCange = rservice.passwordCange(passwordDto);
			if (passwordCange) {
				return "redirect:/dashboard";
			} else {
				return "login";
			}
		} else {
			model.addAttribute("errMsg", "invalid password");
			return "resertPassword";
		}
	}

}
