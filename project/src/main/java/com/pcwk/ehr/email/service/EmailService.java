package com.pcwk.ehr.email.service;

import java.sql.SQLException;

import com.pcwk.ehr.email.domain.EmailAuthVO;

public interface EmailService {
	int doDelete(EmailAuthVO inVO) throws SQLException;
	
	int doSave(EmailAuthVO inVO) throws SQLException;
	
	EmailAuthVO doSelectOne(EmailAuthVO inVO) throws SQLException, NullPointerException;
	
	String doSelectAuthCodeByEmail(String memEmail) throws SQLException;
	String doSelectAuthTimeByEmail(String memEmail) throws SQLException;
}
