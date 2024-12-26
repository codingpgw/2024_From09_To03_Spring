package com.pcwk.ehr.login.service;

import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.user.domain.UserVO;

public interface LoginService {
	
	/**
	 * 단건 조회
	 * @param inVO
	 * @return
	 * @throws Exception
	 */
	UserVO doSelectOne(UserVO inVO) throws Exception;
	
	/**
	 * 
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	int idPassCheck(UserVO inVO) throws SQLException;
}
