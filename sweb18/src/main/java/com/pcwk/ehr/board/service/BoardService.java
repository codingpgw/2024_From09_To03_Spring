package com.pcwk.ehr.board.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;

public interface BoardService {
	/**
	 * 단건 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doDelete(BoardVO inVO) throws SQLException;

	/**
	 * 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doUpdate(BoardVO inVO) throws SQLException;

	/**
	 * 목록 조회
	 * @param dto
	 * @return List<BoardVO>
	 */
	List<BoardVO> doRetrieve(DTO dto);

	/**
	 * 단건 저장
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(BoardVO inVO) throws SQLException;

	/**
	 * 
	 * @param inVO
	 * @return BoardVO
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	BoardVO doSelectOne(BoardVO inVO) throws SQLException, NullPointerException;
}
