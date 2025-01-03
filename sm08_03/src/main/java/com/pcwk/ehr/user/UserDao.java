package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {
	final Logger log = LogManager.getLogger(UserDao.class);
	
	private DataSource dataSource;
	
	public UserDao() {
		super();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int jdbcContextWithStatementStrategy(StatementStrategy stmt)
			throws SQLException{
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			
			//-------------------------------------------
			pstmt = stmt.makePreparedStatement(conn);
			//-------------------------------------------
			
			log.debug("3.param : 없음");
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
	
	public int getCount() throws SQLException {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//2.
			conn = dataSource.getConnection();
			StringBuilder sb = new StringBuilder(200);
			sb.append("SELECT COUNT(*) totalCnt \n");
			sb.append("  FROM member            \n");
			log.debug("2.sql :"+sb.toString());
			
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("3.param : 없음");
			
			//4
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
				//count = rs.getInt("totalCnt");
				log.debug("4.count:{}",count);
			}
		}catch(SQLException e) {
			throw e;
		}finally {
			//5
			if(null != rs) {
				try {
					rs.close();	
				}catch(SQLException e) {
					
				}			
			}
			
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
		
		return count;
	}
	
	public void deleteAll() throws SQLException {
		//SQL 작성만
		//1. Connection : X
		//2. 자원 반납 : close()
		
		StatementStrategy st = new UserDaoDeleteAll();
		jdbcContextWithStatementStrategy(st);
		
	}

	public int doSave(UserVO inVO) throws SQLException {
		int flag = 0;
		StatementStrategy st = new UserDaoDoSave(inVO);
		flag = jdbcContextWithStatementStrategy(st);
		return flag;
	}

	public UserVO doSelectOne(UserVO inVO) throws SQLException,NullPointerException {
		//1. DB연결을 위한 Connection
		//2. SQL을 담은 PreparedStatement,Statement를 생성
		//3. PreparedStatement를 실행한다.
		//4. 실행결과 받기 ResultSet 받아서 저장.(x)
		//5. Connection,PreparedStatement,ResultSet의 자원 반납.
		//6. JDBC API에 대한 예외 처리 
		
		UserVO outVO = null;
		
		//1.
		Connection conn = dataSource.getConnection();
		
		//2.
		StringBuilder sb = new StringBuilder(200);
		sb.append("SELECT                                             \n");
		sb.append("user_id,                                           \n");
		sb.append("name,                                              \n");
		sb.append("password,                                          \n");
		sb.append("TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt     \n");
		sb.append("FROM                                               \n");
		sb.append("member                                             \n");
		sb.append("WHERE user_id = ?                         		  \n");
		log.debug("2.sql :"+sb.toString());
		
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getUserId());
		
		log.debug("3.param : "+inVO.toString());
		
		//4
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			outVO = new UserVO();
			
			outVO.setUserId(rs.getString("user_id"));
			outVO.setName(rs.getString("name"));
			outVO.setPassword(rs.getString("password"));
			outVO.setRegDt(rs.getString("reg_dt"));
			
			log.debug("4.outVO : +"+inVO.toString());
		}
		
		//조회 데이터가 없는 경우
		if(null == outVO) {
			throw new NullPointerException(inVO.getUserId()+"(아이디)를 확인하세요.");
		}
		
		//5
		rs.close();
		pstmt.close();
		conn.close();
		
		return outVO;
	}

}
