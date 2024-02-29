package in.school.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.school.service.DashboardServiceImpl;

@Controller
public class DashboardController {
	@Autowired
	DashboardServiceImpl dservice;

	@GetMapping("/dashboard")
	public String getQuate(Model model) {
		String quate = dservice.getQuate();
		model.addAttribute("quate", quate) ;
		return "dashboard" ;
	}
}
