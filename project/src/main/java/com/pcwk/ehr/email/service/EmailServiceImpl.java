package com.pcwk.ehr.email.service;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.email.dao.EmailDao;
import com.pcwk.ehr.email.domain.EmailAuthVO;

@Service
public class EmailServiceImpl implements EmailService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	EmailDao emailDao;
	
	public EmailServiceImpl() {
		
	}

	@Override
	public int doDelete(EmailAuthVO inVO) throws SQLException {
		return emailDao.doDelete(inVO);
	}

	@Override
	public int doSave(EmailAuthVO inVO) throws SQLException {
		return emailDao.doSave(inVO);
	}

	@Override
	public EmailAuthVO doSelectOne(EmailAuthVO inVO) throws SQLException, NullPointerException {
		return emailDao.doSelectOne(inVO);
	}

	@Override
	public String doSelectAuthCodeByEmail(String memEmail) throws SQLException {
		return emailDao.doSelectAuthCodeByEmail(memEmail);
	}

	@Override
	public String doSelectAuthTimeByEmail(String memEmail) throws SQLException {
		return emailDao.doSelectAuthTimeByEmail(memEmail);
	}

}
