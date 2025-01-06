package com.pcwk.ehr.login.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
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
import com.pcwk.ehr.member.domain.MemberVO;

@Controller
@RequestMapping("login")
public class LoginController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private LoginService loginService;
	
	public LoginController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ LoginController()                     │");
		log.debug("└───────────────────────────────────────┘");
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
	public String logout(HttpSession httpSession, HttpServletResponse response) {
		String viewName = "main/main";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **logout()**                          │");
		log.debug("└───────────────────────────────────────┘");
		
		if (httpSession.getAttribute("member") != null) {
			// session 삭제
			httpSession.invalidate();
		}
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
		
		return viewName;
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String login(MemberVO member, HttpSession httpSession) throws Exception {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **login()**                           │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug(" │ param                       │" + member);
		
		int flag = loginService.idPassCheck(member);
		
		String message = "";
		
		// 아이디를 확인하세요.
		if (flag == 10) {
			message = "아이디를 확인하세요.";
		// 비밀번호를 확인하세요.
		} else if (flag == 20) {
			message = "비밀번호를 확인하세요.";
		// 아이디 비밀번호 일치
		} else if (flag == 30) {
			MemberVO outVO = loginService.doSelectOne(member);
			if (outVO.getMemDiv() == "20") {
				message = "관리자 " + outVO.getName() + "님 환영합니다.";
			} else {
				message = outVO.getName() + "님 환영합니다.";
			}
			
			// session 생성
			httpSession.setAttribute("member", outVO);
		} else {
			flag = 99;
			message = "오류가 발생했습니다.";
		}
		
		
		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}", jsonString);
		
		return jsonString;
	}
}
