package com.pcwk.ehr.community.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.community.domain.CommunityVO;

public interface CommunityService {
	/**
	 * 단건 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doDelete(CommunityVO inVO) throws SQLException;

	/**
	 * 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doUpdate(CommunityVO inVO) throws SQLException;

	/**
	 * 목록조회
	 * @param dto
	 * @return List<BoardVO>
	 */
	List<CommunityVO> doRetrieve(DTO dto);

	/**
	 * 단건 저장
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(CommunityVO inVO) throws SQLException;

	/**
	 * 단건 조회
	 * @param inVO
	 * @return BoardVO 
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	CommunityVO doSelectOne(CommunityVO inVO) throws SQLException, NullPointerException;
}