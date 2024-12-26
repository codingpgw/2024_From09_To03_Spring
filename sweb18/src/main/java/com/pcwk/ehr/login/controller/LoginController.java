package com.pcwk.ehr.login.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.login.service.LoginService;
import com.pcwk.ehr.user.domain.UserVO;

@Controller
@RequestMapping("login")
public class LoginController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private LoginService loginService;

	public LoginController() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **LoginController**               │");
		log.debug("└───────────────────────────────────┘");
	}
	
	@GetMapping("/login_index.do")
	public String loginIndex() {
		String viewName = "login/login";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **loginIndex()**                      │");
		log.debug("└───────────────────────────────────────┘");
		
		return viewName;
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession httpSession) {
		String viewName = "main/main";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **logout()**                          │");
		log.debug("└───────────────────────────────────────┘");
		
		if(null != httpSession.getAttribute("user")) {
			httpSession.invalidate();
		}
		
		return viewName;
	}
	
	@RequestMapping(value="login.do",method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String login(UserVO user, HttpSession httpSession) throws Exception {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **login()**                           │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug("user:{}", user);
		
		int flag = loginService.idPassCheck(user);
		
		String message = "";
		
		//아이디를 확인하세요.
		if(10 == flag) {
			message = "아이디를 확인하세요.";
		//비번을 확인하세요.
		}else if(20 == flag) {
			message = "비밀번호를 확인하세요.";
		//아이디 비번 일치
		}else if(30 == flag) {
			UserVO outVO = loginService.doSelectOne(user);
			message = user.getUserId()+"님 환영합니다.";
			
			//session 생성
			httpSession.setAttribute("user", outVO);
		}else {
			flag = 99;
			message = "오류가 발생했습니다. 다시 시도해주세요.";
		}
		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:{}", jsonString);
		
		return jsonString;
	}
	
}
