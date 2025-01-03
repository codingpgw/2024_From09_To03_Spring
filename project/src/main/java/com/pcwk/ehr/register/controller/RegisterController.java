package com.pcwk.ehr.register.controller;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.email.domain.EmailAuthVO;
import com.pcwk.ehr.email.service.EmailService;
import com.pcwk.ehr.member.domain.MemberVO;
import com.pcwk.ehr.register.service.RegisterService;
import com.pcwk.ehr.user.domain.EmailauthVO;

@Controller
@RequestMapping("register")
public class RegisterController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	JavaMailSenderImpl mailSender;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RegisterService registerService;
	
	public RegisterController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ RegisterController()                  │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	@GetMapping("/register.do")
	public String registerIndex(){
		String viewName = "register/register";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **registerIndex()**                   │");
		log.debug("└───────────────────────────────────────┘");
		return viewName;
	}
	
	@RequestMapping(value = "/doRegister.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
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
	
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
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
	public String emailAuth(String email, Model model) throws SQLException {
	    log.info("전달 받은 이메일 주소: " + email);

	    String checkCode = emailService.doSelectAuthCodeByEmail(email);
	    // 이메일 보낼 양식
	    String setFrom = "1026rjsdnd@naver.com"; // 발신자 이메일 주소 설정
	    String toMail = email;
	    String title = "회원가입 인증 이메일 입니다.";
	    String content = "인증 코드는 " + checkCode + " 입니다.<br>해당 인증 코드를 인증 코드 확인란에 기입하여 주세요.";
	    
	   
	    // 이메일 전송
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
	        helper.setFrom(setFrom);
	        helper.setTo(toMail);
	        helper.setSubject(title);
	        helper.setText(content, true);
	        mailSender.send(message);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 예외 처리 로직 추가
	    }

	    return checkCode;
	}
	
	public boolean isMoreThanFiveMinutes(String email) throws SQLException { 
		// 현재 시간 
		LocalDateTime now = LocalDateTime.now(); // 데이터베이스에서 email_time 가져오기 
		String emailTimeStr = emailService.doSelectAuthTimeByEmail(email); // 이 메서드는 email_time 값을 반환한다고 가정 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // email_time 형식에 맞춰 변경 
		LocalDateTime emailTime = LocalDateTime.parse(emailTimeStr, formatter); // 시간 차이 계산 
		Duration duration = Duration.between(emailTime, now); // 5분 초과 여부 반환 
		return duration.toMinutes() > 5;
	}
	
	@PostMapping("/verifyCode.do")
	@ResponseBody
	public boolean verifyCode(String email, String inputCode) throws SQLException {
	    // 인증 코드 확인
	    String checkCode = emailService.doSelectAuthCodeByEmail(email);
	    if (!inputCode.equals(checkCode)) {
	        return false;
	    }

	    // 5분 초과 여부 확인
	    boolean isTimeValid = !isMoreThanFiveMinutes(email);
	    return isTimeValid;
	}
}
