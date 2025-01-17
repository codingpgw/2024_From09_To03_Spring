package com.pcwk.ehr.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserMain {
	final Logger log = LogManager.getLogger(UserMain.class);
	
	UserDao dao;
	UserVO user;
	
	ApplicationContext context;
	
	public UserMain() {
		
		context = new GenericXmlApplicationContext("applicationContext.xml"); 
				
		//dao = (UserDao)context.getBean("userDao");
		dao = context.getBean("userDao",UserDao.class);
		user = new UserVO("james01", "이상무01", "4321", "사용하지 않음");
		
	}
	
	public void doSave() {
		log.debug("doSave");
		try {
			int flag = dao.doSave(user);
			if(1==flag) {
				log.debug("등록 성공");
			}else {
				log.debug("등록 실패");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doSelectOne() {
		log.debug("doSelectOne");
		
		try {
			UserVO outVO = dao.doSelectOne(user);
			
			if(null != outVO) {
				log.debug("조회 성공 : "+outVO);
			}else {
				log.debug("조회 실패");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAll() {
		log.debug("deleteAll");
		
		try {
			  dao.deleteAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	public void getCount() {
		log.debug("getCount");
		
		try {
			dao.getCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		UserMain main = new UserMain();
		
		main.deleteAll();
		
		main.doSave();
		//단건조회
		main.doSelectOne();
		main.getCount();
	}

}
