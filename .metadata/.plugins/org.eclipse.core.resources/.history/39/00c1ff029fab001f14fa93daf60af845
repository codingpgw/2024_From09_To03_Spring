package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll extends UserDao {

	@Override
	public PreparedStatement makeStatement(Connection connection) throws SQLException {
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder(200);
		sb.append("DELETE FROM member \n");
		log.debug("2.sql :"+sb.toString());
		
		pstmt = connection.prepareStatement(sb.toString());
		return pstmt;
	}

}
