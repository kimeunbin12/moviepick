package kr.co.moviepick;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class adminController {
	
	@RequestMapping(value = "/admin/index.do", method = RequestMethod.GET)
	public String admin() {
		
		return "admin/index";
	}
	
}
