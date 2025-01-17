package com.pcwk.ehr.async;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AsyncController {
	final Logger log = LogManager.getLogger(getClass());

	public AsyncController() {
		super();
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **AsyncController()**                 │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	@GetMapping("/async/async_index.do")
	public String asyncIndex() {
		String viewName = "async/async_index";
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **asyncIndex()**                      │");
		log.debug("└───────────────────────────────────────┘");
		
		return viewName; 
	}
	
//	@RequestMapping(value="/async/async_result.do"
//			,method = RequestMethod.POST
//			,produces="text/plain;charset=UTF-8")
	@PostMapping(value="/async/async_result.do"
			,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String asyncResult(HttpServletRequest req) {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **asyncResult()**                     │");
		log.debug("└───────────────────────────────────────┘");
		String userName = req.getParameter("username");
		String userPass = req.getParameter("userpass");
		log.debug("userName:{}, userPass:{}",userName,userPass);
		return "Hello "+userName+" || "+userPass;
	}
	
}
