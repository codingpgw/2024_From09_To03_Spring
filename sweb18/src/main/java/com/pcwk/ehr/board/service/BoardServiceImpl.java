package com.pcwk.ehr.board.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;

@Service
public class BoardServiceImpl implements BoardService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	BoardDao boardDao;
	
	public BoardServiceImpl() {
		
	}

	@Override
	public int doDelete(BoardVO inVO) throws SQLException {
		return boardDao.doDelete(inVO);
	}

	@Override
	public int doUpdate(BoardVO inVO) throws SQLException {
		return boardDao.doUpdate(inVO);
	}

	@Override
	public List<BoardVO> doRetrieve(DTO dto) {
		return boardDao.doRetrieve(dto);
	}

	@Override
	public int doSave(BoardVO inVO) throws SQLException {
		if(inVO.getSeq() == 0) {
			inVO.setSeq(boardDao.getBoardSequence());
			log.debug(inVO);
		}
		
		return boardDao.doSave(inVO);
	}

	@Override
	public BoardVO doSelectOne(BoardVO inVO) throws SQLException, NullPointerException {
		log.debug("┌────────────────────────────────┐");
		log.debug("│ doSelectOne()                  │");
		log.debug("└────────────────────────────────┘");
		
		//1.조회수 증가
		int flag = boardDao.doReadCntUpdate(inVO);
		log.debug("1.flag:{}",flag);
		
		return boardDao.doSelectOne(inVO);
	}

}
