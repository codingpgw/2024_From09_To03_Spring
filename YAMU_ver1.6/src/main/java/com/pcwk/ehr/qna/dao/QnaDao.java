package com.pcwk.ehr.qna.dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.qna.domain.QnaVO;

public interface QnaDao {
	List<QnaVO> doRetrieve(DTO dto);

	int doDelete(QnaVO inVO) throws SQLException;

	int doUpdate(QnaVO inVO) throws SQLException;

	int doSave(QnaVO inVO) throws SQLException;

	QnaVO doSelectOne(QnaVO inVO) throws SQLException, NullPointerException;
}