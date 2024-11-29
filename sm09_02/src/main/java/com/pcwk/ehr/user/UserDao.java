package com.pcwk.ehr.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {


	int saveAll();

	List<UserVO> doRetrieve(DTO dto);

	List<UserVO> getAll() throws SQLException;


	int getCount() throws SQLException;

	void deleteAll() throws SQLException;

	// 등록
	int doSave(UserVO inVO) throws SQLException;

	// 단건조회
	UserVO doSelectOne(UserVO inVO) throws SQLException, NullPointerException;

}