package com.pcwk.ehr.email.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.email.domain.EmailAuthVO;
import com.pcwk.ehr.email.service.EmailService;

@Controller
@RequestMapping("email")
public class EmailController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	EmailService emailService;

	public EmailController() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **EmailController**               │");
		log.debug("└───────────────────────────────────┘");
	}
	
	@PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(@RequestParam(name="memEmail", required = true)String memEmail)
			throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doDelete()**                        │");
		log.debug("└───────────────────────────────────────┘");
		log.debug("memEmail : "+memEmail);
		
		EmailAuthVO inVO = new EmailAuthVO();
		inVO.setMemEmail(memEmail);
		
		log.debug("inVO:{}",inVO);
		
		int flag = this.emailService.doDelete(inVO);
		
		String message = "";
		if(1 == flag) {
			message = "";
		}else {
			message = "인증 재발급에 실패하였습니다.";
		}

		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:{}", jsonString);
		
		return jsonString;
	}
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(HttpServletRequest req, EmailAuthVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");

		log.debug("param:{}", param);
		
		int flag = emailService.doSave(param);
		String message = "";
		if(1 == flag) {
			message = "인증 메일이 발송되었습니다.";
		}else {
			message = "인증 메일 발송이 실패했습니다.";
		}
		
		MessageVO messageVO = new MessageVO(flag, message);

		jsonString = new Gson().toJson(messageVO);
		log.debug("jsonString:{}", jsonString);
		
		return jsonString;
	}
	
	
}
