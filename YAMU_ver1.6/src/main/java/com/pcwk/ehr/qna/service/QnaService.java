package com.pcwk.ehr.qna.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.qna.domain.QnaVO;

public interface QnaService {
	int doSave(QnaVO inVO) throws SQLException;
	
	int doDelete(QnaVO inVO) throws SQLException;
	
	int doUpdate(QnaVO inVO) throws SQLException;
	
	List<QnaVO> doRetrieve(DTO dto);
	
	QnaVO doSelectOne(QnaVO inVO) throws SQLException, NullPointerException;
}
