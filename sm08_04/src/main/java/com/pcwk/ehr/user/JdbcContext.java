package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcContext {
	final Logger log = LogManager.getLogger(getClass());
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int workWithStatementStrategy(StatementStrategy stmt)
			throws SQLException{
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			
			//-------------------------------------------
			pstmt = stmt.makePreparedStatement(conn);
			//-------------------------------------------
			
			flag = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			throw e;
		}finally {
			if(null != pstmt) {
				try {
					pstmt.close();					
				}catch(SQLException e) {
					
				}
			}
			if(null != conn) {
				try {
					conn.close();					
				}catch(SQLException e) {
					
				}
			}	
		}
		
		return flag;
	}
}
