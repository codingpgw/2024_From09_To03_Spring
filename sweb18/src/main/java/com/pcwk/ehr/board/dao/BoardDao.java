package com.pcwk.ehr.board.dao;

import java.sql.SQLException;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.WorkDiv;

public interface BoardDao extends WorkDiv<BoardVO> {
	
	void deleteAll() throws SQLException;
	
	int saveAll();
	
	//board_seq 시퀀스 seq조회
	int getBoardSequence();
	
	//조회 Count 증가
	int doReadCntUpdate(BoardVO inVO)throws SQLException;
}
