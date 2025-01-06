package com.pcwk.ehr.login.service;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.member.dao.MemberDao;
import com.pcwk.ehr.member.domain.MemberVO;

@Service
public class LoginServiceImpl implements LoginService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MemberDao memberDao;
	
	public LoginServiceImpl() {
		
	}
	
	@Override
	public int idPassCheck(MemberVO inVO) throws SQLException {
		int flag = 30;
		// flag = 10 : id 없음
		// flag = 20 : pw 불일치
		// flag = 30 : id pw 일치
		
		int count = memberDao.idCheck(inVO);
		
		if (count != 1) {
			flag = 10;
			return flag;
		}
		
		count = memberDao.idPassCheck(inVO);
		
		if (count != 1) {
			flag = 20;
			return flag;
		}
		
		log.debug("flag: {}", flag);
		
		return flag;
	}

	@Override
	public MemberVO doSelectOne(MemberVO inVO) throws Exception {
		return memberDao.doSelectOne(inVO);
	}

}
