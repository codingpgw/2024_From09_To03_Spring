package com.pcwk.ehr.register.service;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.member.dao.MemberDao;
import com.pcwk.ehr.member.domain.MemberVO;

@Service
public class RegisterImpl implements RegisterService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MemberDao memberDao;

	public RegisterImpl() {
		
	}
	
	@Override
	public int doSave(MemberVO inVO) throws SQLException {
		return memberDao.doSave(inVO);
	}
	
	@Override
	public int idPassValidation(MemberVO inVO) throws SQLException {
		int flag = 30;
		// flag = 10 : id 중복
		// flag = 20 : pw pw확인 불일치
		// flag = 30 : 고유 id, pw, pw확인 일치
		
		int count = memberDao.idCheck(inVO);
		if (count == 1) {
			flag = 10;
			return flag;
		}
		
		
		
		return flag;
	}
}
