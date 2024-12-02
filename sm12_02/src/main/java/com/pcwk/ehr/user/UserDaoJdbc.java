package com.pcwk.ehr.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoJdbc implements UserDao {
	final Logger log = LogManager.getLogger(UserDaoJdbc.class);

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private RowMapper<UserVO> userMapper = new RowMapper<UserVO>() {

		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO outVO = new UserVO();
			outVO.setUserId(rs.getString("user_id"));
			outVO.setName(rs.getString("name"));
			outVO.setPassword(rs.getString("password"));
			outVO.setRegDt(rs.getString("reg_dt"));
			//------------------------------------------------------------------
			outVO.setLogin(rs.getInt("login"));
			outVO.setRecommend(rs.getInt("recommend"));
			outVO.setGrade(Level.valueOf(rs.getInt("grade")));//Enum Level변경
			outVO.setEmail(rs.getString("email"));
			
			
			log.debug("2.outVO: " + outVO.toString());
			return outVO;
		}

	};

	public UserDaoJdbc() {
		super();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	/**
	 * 502건 데이터 입력, paging데이터 처리
	 * 
	 * @return
	 */
	@Override
	public int saveAll() {
		int cnt = 0;

		StringBuilder sb = new StringBuilder(300);
		sb.append(" INSERT INTO member                                    \n");
		sb.append(" SELECT 'jamse' ||level user_id,                       \n");
		sb.append("        '이상무' ||level name,                          \n");
		sb.append("        '4321' password,                               \n");
		sb.append("        MOD(LEVEL,10) login,                           \n");
		sb.append("        MOD(LEVEL,2) recommend,                        \n");
		sb.append("        DECODE( MOD(LEVEL,3),0,3,MOD(LEVEL,3)) grade,  \n");
		sb.append("        'jamesol@paran.com' email,                     \n");
		sb.append("        SYSDATE - level reg_dt                         \n");
		sb.append("   FROM dual                                           \n");
		sb.append("   CONNECT BY LEVEL <= 502                             \n");

		cnt = this.jdbcTemplate.update(sb.toString());
		log.debug("총 등록 건수 :{}", cnt);

		return cnt;
	}

	@Override
	public List<UserVO> doRetrieve(DTO dto) {

		SearchVO inVO = (SearchVO) dto;

		List<UserVO> userList = new ArrayList<UserVO>();

		StringBuilder sb = new StringBuilder(1000);
		sb.append(" SELECT A.*, B.*                                                             \n");
		sb.append("   FROM (                                                                    \n");
		sb.append(" 		SELECT tt1.rnum no,                                                 \n");
		sb.append(" 			   tt1.user_id,                                                 \n");
		sb.append(" 			   tt1.name,                                                    \n");
		sb.append(" 			   tt1.password,                                                \n");
		sb.append(" 			   TO_CHAR(tt1.reg_dt,'YYYY/MM/DD') reg_dt,                     \n");
		sb.append(" 			   tt1.login,                                                   \n");
		sb.append(" 			   tt1.recommend,                                               \n");
		sb.append(" 			   tt1.grade,                                                   \n");
		sb.append(" 			   tt1.email                                                    \n");
		sb.append(" 		  FROM(                                                             \n");
		sb.append(" 				SELECT rownum rnum, t1.*                                    \n");
		sb.append(" 				  FROM (                                                    \n");
		sb.append(" 							SELECT *                                        \n");
		sb.append(" 							FROM member                                     \n");
		sb.append(" 							--WHERE 조건                                                                     \n");
		sb.append(" 							ORDER BY reg_dt DESC                            \n");
		sb.append(" 				)t1                                                         \n");
		sb.append(" 				WHERE ROWNUM <=( ? * (? - 1  )+? )                          \n");
		sb.append(" 		)tt1                                                                \n");
		sb.append(" 		WHERE rnum >=( ? * (? - 1  )+1 )                                    \n");
		sb.append("   ) A                                                                       \n");
		sb.append("   CROSS JOIN (                                                              \n");
		sb.append(" 		SELECT COUNT(*) totalCnt                                            \n");
		sb.append(" 		FROM member                                                         \n");
		sb.append(" 		--WHERE 조건                                                                                                          \n");
		sb.append("   ) B                                                                       \n");

		Object[] args = { inVO.getPageSize(), inVO.getPageNo(), inVO.getPageSize(), inVO.getPageSize(),
				inVO.getPageNo() };

		RowMapper<UserVO> users = new RowMapper<UserVO>() {

			@Override
			public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserVO outVO = new UserVO();
				// no
				outVO.setNo(rs.getInt("no"));
				outVO.setUserId(rs.getString("user_id"));
				outVO.setName(rs.getString("name"));
				outVO.setPassword(rs.getString("password"));
				outVO.setRegDt(rs.getString("reg_dt"));
				// totalCnt
				outVO.setTotalCnt(rs.getInt("totalCnt"));
                //--------------------------------------------------------------
				outVO.setLogin(rs.getInt("login"));
				outVO.setRecommend(rs.getInt("recommend"));
				outVO.setGrade(  Level.valueOf(rs.getInt("grade")) );
				outVO.setEmail(rs.getString("email"));
				log.debug("outVO:{}", outVO);

				return outVO;
			}

		};

		userList = this.jdbcTemplate.query(sb.toString(), users, args);

		return userList;
	}

	@Override
	public List<UserVO> getAll() throws SQLException {
		List<UserVO> userList = new ArrayList<UserVO>();

		StringBuilder sb = new StringBuilder();
		sb.append("  SELECT                                    \n");                      
		sb.append("      user_id,                              \n");
		sb.append("      name,                                 \n");
		sb.append("      password,                             \n");
		sb.append("      login,                                \n");
		sb.append("      recommend,                            \n");
		sb.append("      grade,                                \n");
		sb.append("      email,                                \n");
		sb.append("      TO_CHAR(reg_dt,'YYYY/MM/DD') reg_dt   \n");
		sb.append("  FROM member                               \n");
		sb.append("  ORDER BY user_id DESC                      \n");

		userList = this.jdbcTemplate.query(sb.toString(), userMapper);

		return userList;
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int getCount() throws SQLException {
		int count = 0;

		// 1.
		StringBuilder sb = new StringBuilder(50);
		sb.append(" SELECT COUNT(*) totalCnt \n");
		sb.append(" FROM  member             \n");

		count = this.jdbcTemplate.queryForObject(sb.toString(), Integer.class);
		log.debug("1. count:{}", count);

		return count;
	}

	@Override
	public void deleteAll() throws SQLException {
		// SQL작성만!
		// 1. Connection : X
		// 2. 자원 반납 : close() X
		StringBuilder sb = new StringBuilder(100);
		sb.append(" DELETE FROM member \n");
		// log.debug("2. sql:\n" + sb.toString());
		this.jdbcTemplate.update(sb.toString());

	}

	// 등록
	@Override
	public int doSave(UserVO inVO) throws SQLException {
		int flag = 0;

		StringBuilder sb = new StringBuilder(200);
		sb.append("   INSERT INTO member (     \n");
		sb.append("      user_id,              \n");
		sb.append("      name,                 \n");
		sb.append("      password,             \n");
		sb.append("      login,                \n");
		sb.append("      recommend,            \n");
		sb.append("      grade,                \n");
		sb.append("      email,                \n");
		sb.append("      reg_dt                \n");
		sb.append("  ) VALUES ( ?,             \n");
		sb.append("             ?,             \n");
		sb.append("             ?,             \n");
		sb.append("             ?,             \n");
		sb.append("             ?,             \n");
		sb.append("             ?,             \n");
		sb.append("             ?,             \n");
		sb.append("             SYSDATE )      \n");

		Object[] args = { inVO.getUserId(), inVO.getName(), inVO.getPassword() 
				         ,inVO.getLogin(),inVO.getRecommend(),inVO.getGrade().intValue()
				         ,inVO.getEmail()
		};

		log.debug("1.param:");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		flag = this.jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);

		return flag;
	}

	// 단건조회
	/*
	 * alt+shift+a : 세로 편집
	 * sb.append(" SELECT                                                 \n");
	 * sb.append("     user_id,                                           \n");
	 * sb.append("     name,                                              \n");
	 * sb.append("     password,                                          \n");
	 * sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt     \n");
	 * sb.append(" FROM                                                   \n");
	 * sb.append("     member                                             \n");
	 * sb.append(" WHERE  user_id = :user_id                              \n");
	 */
	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException,EmptyResultDataAccessException ,NullPointerException {

		UserVO outVO = null;

		// 1.
		StringBuilder sb = new StringBuilder(200);
		sb.append("  SELECT                                    \n");                      
		sb.append("      user_id,                              \n");
		sb.append("      name,                                 \n");
		sb.append("      password,                             \n");
		sb.append("      login,                                \n");
		sb.append("      recommend,                            \n");
		sb.append("      grade,                                \n");
		sb.append("      email,                                \n");
		sb.append("      TO_CHAR(reg_dt,'YYYY/MM/DD') reg_dt   \n");
		sb.append("  FROM member                               \n");
		sb.append(" WHERE  user_id = ?                         \n");

		Object[] args = { inVO.getUserId() };
		log.debug("1.param:");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		outVO = this.jdbcTemplate.queryForObject(sb.toString(), userMapper, args);

		// 조회 데이터가 없는 경우
		if (null == outVO) {
			throw new NullPointerException(inVO.getUserId() + "(아이디)를 확인 하세요.");
		}

		return outVO;
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb = new StringBuilder(150);
		sb.append(" UPDATE member             		\n");
		sb.append(" 	SET  name  = ?        		\n");
		sb.append(" 	   	,password  = ?       	\n");
		sb.append(" 	  	,login     = ?      	\n");
		sb.append(" 	 	,recommend = ?      	\n");
		sb.append(" 	 	,grade     = ?      	\n");
		sb.append(" 	  	,email     = ?      	\n");
		sb.append(" 	 	,reg_dt    = SYSDATE 	\n");
		sb.append(" WHERE                     		\n");
		sb.append("    user_id = ?            		\n");
		
		Object[] args = {inVO.getName()
				,inVO.getPassword()
				,inVO.getLogin()
		        ,inVO.getRecommend()
		        ,inVO.getGrade().intValue()
		        ,inVO.getEmail()
		        ,inVO.getUserId()
		};
		
		log.debug("1.param:");
		int i=0;
		for (Object obj : args) {
			log.debug(++i +"."+obj.toString());
		}
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(50);
		sb.append("DELETE FROM member \n");
		sb.append("WHERE user_id = ?  \n");
		
		Object[] args = {inVO.getUserId()};
		
		log.debug("1.param:");
		int i=0;
		for (Object obj : args) {
			log.debug(++i +"."+obj.toString());
		}
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}
	
	

}