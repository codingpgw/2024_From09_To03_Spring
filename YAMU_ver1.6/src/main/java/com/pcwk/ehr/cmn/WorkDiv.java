package com.pcwk.ehr.cmn;

import java.sql.SQLException;
import java.util.List;

public interface WorkDiv<T> {
	
	/**
	 * 단건 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doDelete(T inVO) throws SQLException;
	
	/**
	 * 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doUpdate(T inVO) throws SQLException;
	
	/**
	 * 목록 조회
	 * @param dto
	 * @return List<BoardVO>
	 */
	List<T> doRetrieve(DTO dto);
	
	/**
	 * 단건 저장
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(T inVO) throws SQLException;
	
	/**
	 * 단건 조회
	 * @param inVO
	 * @return BoardVO
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	T doSelectOne(T inVO) throws SQLException, NullPointerException;

	
}
