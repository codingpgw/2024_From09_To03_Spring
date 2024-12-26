package com.pcwk.ehr.community.dao;

import java.sql.SQLException;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.user.domain.CommunityVO;

public interface CommunityDao extends WorkDiv<CommunityVO>{
	
	void deleteAll() throws SQLException;
	
	int saveAll();
	
	int checkHighCmnView(CommunityVO inVO) throws SQLException;
}
