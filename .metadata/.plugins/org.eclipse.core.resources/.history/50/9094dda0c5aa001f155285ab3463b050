package com.pcwk.ehr.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserMain {

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
		System.out.println("doSave");
		try {
			int flag = dao.doSave(user);
			if(1==flag) {
				System.out.println("등록 성공");
			}else {
				System.out.println("등록 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doSelectOne() {
		System.out.println("doSelectOne");
		
		try {
			UserVO outVO = dao.doSelectOne(user);
			
			if(null != outVO) {
				System.out.println("조회 성공 : "+outVO);
			}else {
				System.out.println("조회 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		UserMain main = new UserMain();
		
		main.doSave();
		//단건조회
		main.doSelectOne();
	}

}
