package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.user.domain.UserVO;

public interface UserService {

	//50(BASIC -> SILVER로 가기 위한 최소 로그인 횟수
	int MIN_LOGIN_COUNT_FOR_SILVER = 50;
	//30(SILVER -> GOLD로 가기 위한 최소 추천 횟수
	int MIN_RECOMMEND_COUNT_FOR_GOLD = 30;

	/**
	 * 회원 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doDelete(UserVO inVO) throws SQLException;

	/**
	 * 회원 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doUpdate(UserVO inVO) throws SQLException;

	/**
	 * 회원 목록 페이징 처리
	 * @param dto
	 * @return List<UserVO>
	 */
	List<UserVO> doRetrieve(DTO dto);

	/**
	 * 회원 상세 조회
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 * @throws NullPointerException
	 */
	UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException, NullPointerException;

	/**
	 * 회원등록(가입)
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(UserVO inVO) throws SQLException;

	/**
	 * 회원 등업
	 * @throws SQLException
	 */
	void upgradeLevels() throws SQLException;

}