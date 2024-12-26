package com.pcwk.ehr.user.dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.user.domain.TestUserVO;

public interface TestUserDao {
	int doDelete(TestUserVO inVO) throws SQLException;
	
	int doUpdate(TestUserVO inVO) throws SQLException;
	
	int saveAll();

	List<TestUserVO> doRetrieve(DTO dto);

	List<TestUserVO> getAll() throws SQLException;


	int getCount() throws SQLException;

	void deleteAll() throws SQLException;

	// 등록
	int doSave(TestUserVO inVO) throws SQLException;

	// 단건조회
	TestUserVO doSelectOne(TestUserVO inVO) throws SQLException, NullPointerException;
}
