package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {
	/**
	 * DB Connection 생성 및 return
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException; 
	
	// 등록
	/*
	 * sb.append("INSERT INTO member (      \n");
	 * sb.append("user_id,                  \n");
	 * sb.append("name,                     \n");
	 * sb.append("password,                 \n");
	 * sb.append("reg_dt ) VALUES           \n");
	 * sb.append("(                         \n");
	 * sb.append("?,                        \n");
	 * sb.append("?,                        \n");
	 * sb.append("?,                        \n");
	 * sb.append("SYSDATE )                 \n");
	 */
	public int doSave(UserVO inVO) throws ClassNotFoundException,SQLException{
		//1. DB연결을 위한 Connection
		//2. SQL을 담은 PreparedStatement,Statement를 생성
		//3. PreparedStatement를 실행한다.
		//4. 실행결과 받기 ResultSet 받아서 저장.(x)
		//5. Connection,PreparedStatement,ResultSet의 자원 반납.
		//6. JDBC API에 대한 예외 처리 
		
		int flag = 0;
		
		//1. : Connection getConnection()
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
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
			System.out.println("2.sql :"+sb.toString());
			
			//2.
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, inVO.getUserId());
			pstmt.setString(2, inVO.getName());
			pstmt.setString(3, inVO.getPassword());
			System.out.println("3.param : "+inVO.toString());
			flag = pstmt.executeUpdate();
			
			conn.prepareStatement("update~~~");
			
			System.out.println("4.flag:"+flag);
			//성공하면 commit;
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			
			//5 (4번 생략)
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return flag;
	}

	// 단건조회
	/*                                                                
	 *  sb.append("SELECT                                             \n");
       	sb.append("user_id,                                           \n");
    	sb.append("name,                                              \n");
    	sb.append("password,                                          \n");
    	sb.append("TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt     \n");
	    sb.append("FROM                                               \n");
    	sb.append("member                                             \n");
    	sb.append("WHERE user_id = :user_id                           \n");
	 */
	public UserVO doSelectOne(UserVO inVO) throws ClassNotFoundException, SQLException {
		//1. DB연결을 위한 Connection
		//2. SQL을 담은 PreparedStatement,Statement를 생성
		//3. PreparedStatement를 실행한다.
		//4. 실행결과 받기 ResultSet 받아서 저장.(x)
		//5. Connection,PreparedStatement,ResultSet의 자원 반납.
		//6. JDBC API에 대한 예외 처리 
		
		UserVO outVO = null;
		
		//1.
		Connection conn = getConnection();
		
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
		System.out.println("2.sql :"+sb.toString());
		
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getUserId());
		
		System.out.println("3.param : "+inVO.toString());
		
		//4
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			outVO = new UserVO();
			
			outVO.setUserId(rs.getString("user_id"));
			outVO.setName(rs.getString("name"));
			outVO.setPassword(rs.getString("password"));
			outVO.setRegDt(rs.getString("reg_dt"));
			
			System.out.println("4.outVO : +"+inVO.toString());
		}
		
		//5
		rs.close();
		pstmt.close();
		conn.close();
		
		return outVO;
	}

}
