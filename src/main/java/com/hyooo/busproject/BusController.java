package com.hyooo.busproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BusController {
	
	@RequestMapping("/")
	public String chat() {
		return "bus";
	}

}
