package com.pcwk.ehr.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.pcwk.ehr.user.service.UserService;

@Controller
public class UserController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Qualifier("userServiceImpl")
	@Autowired
	private UserService userService;
	
	public UserController() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **UserController**                │");
		log.debug("└───────────────────────────────────┘");
	}
}
