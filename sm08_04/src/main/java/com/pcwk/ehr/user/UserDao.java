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
	
	private JdbcContext jdbcContext;
	
	public UserDao() {
		super();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		//dataSource 중복 사용.
		jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(dataSource);
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
		jdbcContext.workWithStatementStrategy(new StatementStrategy() {

			@Override
			public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = null;
				StringBuilder sb = new StringBuilder(200);
				sb.append("DELETE FROM member \n");
				log.debug("2.sql :"+sb.toString());
				
				pstmt = connection.prepareStatement(sb.toString());
				return pstmt;
			}
			
		});	
	}

	public int doSave(UserVO inVO) throws SQLException {
		int flag = 0;
		//익명 내부 클래스
		//new 인터페이스 이름(){클래스 본문}
		flag = jdbcContext.workWithStatementStrategy(new StatementStrategy() {

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
		});
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
