package com.pcwk.ehr.community.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.community.dao.CommunityDao;
import com.pcwk.ehr.community.domain.CommunityVO;

@Service
public class CommunityServiceImpl implements CommunityService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	CommunityDao communityDao;

	public CommunityServiceImpl() {
		super();
	}

	@Override
	public int doDelete(CommunityVO inVO) throws SQLException {
		return communityDao.doDelete(inVO);
	}

	@Override
	public int doUpdate(CommunityVO inVO) throws SQLException {
		return communityDao.doUpdate(inVO);
	}

	@Override
	public List<CommunityVO> doRetrieve(DTO dto) {
		return communityDao.doRetrieve(dto);
	}

	@Override
	public int doSave(CommunityVO inVO) throws SQLException {
		if (inVO.getCmnNo() == 0) {
			inVO.setCmnNo(communityDao.getBoardSequence());
			log.debug(inVO);
		}
		
		return communityDao.doSave(inVO);
	}

	@Override
	public CommunityVO doSelectOne(CommunityVO inVO) throws SQLException, NullPointerException {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSelectOne()                         │");
		log.debug("└───────────────────────────────────────┘");	
						
		
		//1. 조회수 증가: 본인이 등록한 글이 아닌 경우만 증가됨. 
		int flag = communityDao.doReadCntUpdate(inVO);
		log.debug("1.flag:{}", flag);
		
		return communityDao.doSelectOne(inVO);
	}
}