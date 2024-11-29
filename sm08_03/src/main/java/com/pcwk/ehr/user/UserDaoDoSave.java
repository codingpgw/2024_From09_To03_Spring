package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDoSave implements StatementStrategy {
	UserVO inVO;
	public UserDaoDoSave() {

	}
	
	public UserDaoDoSave(UserVO inVO) {
		super();
		this.inVO = inVO;
	}


	@Override
	public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
		StringBuilder sb = new StringBuilder(200);
		sb.append("INSERT INTO member (      \n"); 
		sb.append("user_id,                  \n"); 
		sb.append("name,                     \n"); 
		sb.append("password,                 \n"); 
		sb.append("reg_dt ) VALUES           \n"); 
		sb.append("(                         \n"); 
		sb.append("?,                        \n"); 
		sb.append("?,                        \n"); 
		sb.append("?,                        \n"); 
		sb.append("SYSDATE )                 \n"); 
		log.debug("2.sql :"+sb.toString());
		
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		//2.1 param 설정
		pstmt.setString(1, inVO.getUserId());
		pstmt.setString(2, inVO.getName());
		pstmt.setString(3, inVO.getPassword());
		
		log.debug("3.param :"+inVO.toString());
		
		return pstmt;
	}



}
