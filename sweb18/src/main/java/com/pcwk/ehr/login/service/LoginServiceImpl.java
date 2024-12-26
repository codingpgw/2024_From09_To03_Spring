package com.pcwk.ehr.login.service;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.UserVO;

@Service
public class LoginServiceImpl implements LoginService{
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	UserDao userDao;
		
	public LoginServiceImpl() {
		
	}

	@Override
	public int idPassCheck(UserVO inVO) throws SQLException {
		int flag = 30;
		//flag = 10 : id 없음
		//flag = 20 : 비번 불일치
		//flag = 30 : id, 비번 일치
		
		int count = userDao.idCheck(inVO);
		if(1 != count) {
			flag = 10;
			return flag;
		}
		
		count = userDao.idPassCheck(inVO);
		if(1 != count) {
			flag = 20;
			return flag;
		}
		log.debug("flag:{}",flag);
		
		return flag;
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws Exception {
		return userDao.doSelectOne(inVO);
	}
}
