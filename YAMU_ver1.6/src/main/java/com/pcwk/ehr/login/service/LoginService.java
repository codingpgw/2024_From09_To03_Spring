package com.pcwk.ehr.login.service;

import java.sql.SQLException;

import com.pcwk.ehr.member.domain.MemberVO;

public interface LoginService {
	/**
	 * 단건 조회
	 * @param inVO
	 * @return MemberVO
	 * @throws Exception
	 */
	MemberVO doSelectOne(MemberVO inVO) throws Exception;
	
	/**
	 * 
	 * @param inVO
	 * @return int
	 * @throws SQLException
	 */
	int idPassCheck(MemberVO inVO) throws SQLException;
}
