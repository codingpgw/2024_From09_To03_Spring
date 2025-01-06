package com.pcwk.ehr.register.controller;

import java.sql.SQLException;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.member.domain.MemberVO;
import com.pcwk.ehr.register.service.RegisterService;

@Controller
@RequestMapping("register")
public class RegisterController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	JavaMailSenderImpl mailSender;
	
	@Autowired
	private RegisterService registerService;
	
	public RegisterController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ RegisterController()                  │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	@GetMapping("/register.do")
	public String registerIndex() {
		String viewName = "register/register";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **registerIndex()**                   │");
		log.debug("└───────────────────────────────────────┘");
		
		return viewName;
	}
	
	@PostMapping(value = "/doRegister.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(HttpServletRequest req, MemberVO member) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug(" │ member                           │" + member);
		
		int idPassCheck = registerService.idPassValidation(member);
		
		String message = "";
		
		if (idPassCheck == 10) {
			message = "아이디가 중복되었습니다.";
		}  else  if (idPassCheck == 30) {
			int flag = registerService.doSave(member);
			
			if (flag == 1) {
				message = member.getName() + "님 등록 성공했습니다.";
			} else {
				message = member.getName() + "님 등록 실패했습니다.";
			}
			
			MessageVO messageVO = new MessageVO(flag, message);
			
			jsonString = new Gson().toJson(messageVO);
			log.debug("jsonString:\n{}", jsonString);
		}
		
		return jsonString;
	}
	
	@PostMapping(value = "/idCheck.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String idCheck(HttpServletRequest req, MemberVO member) throws SQLException {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **idCheck()**                         │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug(" │ member                           │" + member);
		
	    int flag = registerService.idPassValidation(member); // 중복 체크 서비스 호출
	    String message = (flag == 10) ? "아이디가 중복되었습니다." : "사용 가능한 아이디입니다.";
	    
	    MessageVO messageVO = new MessageVO(flag, message);
	    String jsonString = new Gson().toJson(messageVO);
	    
	    log.debug("jsonString:\n{}", jsonString);
	    
	    return jsonString;
	}
	
	@PostMapping("/emailAuth.do")
	@ResponseBody
	public int emailAuth(String email) {
		
		log.info("전달 받은 이메일 주소 : " + email);
		
		//난수의 범위 111111 ~ 999999 (6자리 난수)
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		
		//이메일 보낼 양식
		String setFrom = "rbgml1238@naver.com"; //2단계 인증 x, 메일 설정에서 POP/IMAP 사용 설정에서 POP/SMTP 사용함으로 설정o
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "인증 코드는 " + checkNum + " 입니다." +
						 "<br>" +
						 "해당 인증 코드를 인증 코드 확인란에 기입하여 주세요.";
		
		try {
			MimeMessage message = mailSender.createMimeMessage(); //Spring에서 제공하는 mail API
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
		} catch (Exception e) {
			log.debug("┌───────────────────────────────────────┐");
			log.debug("│ **Exception**                         │" + e.getMessage());
			log.debug("└───────────────────────────────────────┘");
		}
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **받는 사람**                            │" + email);
		log.debug("└───────────────────────────────────────┘");
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **인증 번호**                            │" + checkNum);
		log.debug("└───────────────────────────────────────┘");
		
		return checkNum;
	}
}