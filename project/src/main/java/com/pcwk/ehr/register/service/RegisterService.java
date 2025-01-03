package com.pcwk.ehr.register.service;

import java.sql.SQLException;

import com.pcwk.ehr.member.domain.MemberVO;

public interface RegisterService {

	int idPassValidation(MemberVO inVO) throws SQLException;

	int doSave(MemberVO inVO) throws SQLException;
}
