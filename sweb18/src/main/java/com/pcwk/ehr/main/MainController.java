package com.pcwk.ehr.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")
public class MainController {
	final Logger log = LogManager.getLogger(getClass());
	
	public MainController() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **MainController**                │");
		log.debug("└───────────────────────────────────┘");
	}
	
	@GetMapping("/main.do")
	public String mainIndex() {
		String viewName = "main/main";
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **mainIndex()**                   │");
		log.debug("└───────────────────────────────────┘");
		
		return viewName;
	}
	
	
}
