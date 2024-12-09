package com.pcwk.ehr.user.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;
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
	
	@RequestMapping(value="/user/doUpdate.do"
			,method = RequestMethod.POST
		,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(HttpServletRequest req, UserVO param) throws SQLException  {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doUpdate()**                        │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug("param:{}",param);
		
		int flag = userService.doUpdate(param);
		
		String message = "";
		
		if(1 == flag) {
			message = param.getName()+"님 정보가 수정되었습니다.";
		}else {
			message = param.getName()+"님 정보가 수정 실패했습니다.";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag,message));
		log.debug("jsonString:{}",jsonString);
		
		
		return jsonString;
	}
	
	@RequestMapping(value="/user/doDelete.do"
			,method = RequestMethod.POST
		,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest req,
			@RequestParam(name = "userId", required = true, defaultValue = "Unkown Id")String inUserId) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doDelete()**                        │");
		log.debug("└───────────────────────────────────────┘");
		UserVO inVO = new UserVO();
		
		String userId = inUserId;
		log.debug("inUserId:{}",inUserId);
		inVO.setUserId(userId);
		log.debug("inVO:{}",inVO);
		
		int flag = userService.doDelete(inVO);
		
		String message = "";
		if(1==flag) {
			message = inVO.getUserId()+"님이 삭제되었습니다.";
		}else {
			message = inVO.getUserId()+"님이 실패 했습니다.";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag,message));
		log.debug("jsonString:\n{}",jsonString);
		
		return jsonString;
	}
	
	@RequestMapping(value="/user/doSave.do"
			,method = RequestMethod.POST
		,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(HttpServletRequest req, UserVO param) throws SQLException {
		String jsonString = "";
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug("param:{}",param);
		
		int flag = userService.doSave(param);
		
		String message = "";
		
		if(1 == flag) {
			message = param.getName()+"님 등록 성공하였습니다.";
		}else {
			message = param.getName()+"님 등록 실패하였습니다.";
		}
		
		MessageVO messageVO = new MessageVO(flag,message);
		
		jsonString = new Gson().toJson(messageVO);
		log.debug("jsonString:{}",jsonString);
		
		return jsonString;
	}
}
