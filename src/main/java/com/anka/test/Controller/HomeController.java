package com.anka.test.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String gotoHome() {
		log.debug("Request to gotoHome");
		return "home";
	}
}
