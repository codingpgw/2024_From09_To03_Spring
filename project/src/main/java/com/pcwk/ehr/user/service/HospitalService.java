package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.user.domain.HospitalVO;

public interface HospitalService {


	/**
	 * 회원 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doDelete(HospitalVO inVO) throws SQLException;

	/**
	 * 회원 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doUpdate(HospitalVO inVO) throws SQLException;

	/**
	 * 회원 목록 페이징 처리
	 * @param dto
	 * @return List<UserVO>
	 */
	List<HospitalVO> doRetrieve(DTO dto);

	/**
	 * 회원 상세 조회
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 * @throws NullPointerException
	 */
	HospitalVO doSelectOne(HospitalVO inVO) throws SQLException, EmptyResultDataAccessException, NullPointerException;

	/**
	 * 회원등록(가입)
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(HospitalVO inVO) throws SQLException;


}