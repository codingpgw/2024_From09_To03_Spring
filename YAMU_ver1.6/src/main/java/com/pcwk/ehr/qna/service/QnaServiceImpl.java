package com.pcwk.ehr.qna.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.qna.dao.QnaDao;
import com.pcwk.ehr.qna.domain.QnaVO;

@Service
public class QnaServiceImpl implements QnaService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	QnaDao qnaDao;

	public QnaServiceImpl() {
		super();
	}
	
	@Override
	public int doSave(QnaVO inVO) throws SQLException {
		return qnaDao.doSave(inVO);
	}
	
	@Override
	public int doDelete(QnaVO inVO) throws SQLException {
		return qnaDao.doDelete(inVO);
	}

	@Override
	public int doUpdate(QnaVO inVO) throws SQLException {
		return qnaDao.doUpdate(inVO);
	}

	@Override
	public List<QnaVO> doRetrieve(DTO dto) {
		return qnaDao.doRetrieve(dto);
	}

	@Override
	public QnaVO doSelectOne(QnaVO inVO) throws SQLException, NullPointerException {
		return qnaDao.doSelectOne(inVO);
	}
}
