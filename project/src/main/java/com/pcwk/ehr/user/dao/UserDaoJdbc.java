package com.pcwk.ehr.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

@Repository
public class UserDaoJdbc implements UserDao {
	final Logger log = LogManager.getLogger(UserDaoJdbc.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<UserVO> userMapper = new RowMapper<UserVO>() {

		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO outVO = new UserVO();
			outVO.setMem_id(rs.getString("mem_id"));
			outVO.setMem_password(rs.getString("mem_password"));
			outVO.setMem_name(rs.getString("mem_name"));
			outVO.setMem_email(rs.getString("mem_email"));
			outVO.setMem_phonenum(rs.getString("mem_phonenum"));
			outVO.setMem_jumin(rs.getString("mem_jumin"));
			outVO.setMem_div(rs.getInt("mem_div"));
			outVO.setMem_regdt(rs.getString("mem_reg_dt"));
			//-----------------------------------------------------------------
			
			
			log.debug("2.outVO: " + outVO.toString());
			return outVO;
		}

	};

	public UserDaoJdbc() {
		super();
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
		StringBuilder search = new StringBuilder(100);
		
		if( "10".equals( inVO.getSearchDiv() )){       //회원ID
			search.append(" 							WHERE mem_id LIKE ? ||'%'  \n");
		}else if( "20".equals( inVO.getSearchDiv() )){ //이름
			search.append(" 							WHERE mem_name LIKE ? ||'%'     \n");
		}else if( "30".equals( inVO.getSearchDiv() )){ //이메일
			search.append(" 							WHERE mem_email LIKE ? ||'%'    \n");
		}
		
		log.debug("2.inVO: " + inVO);
		  
		StringBuilder sb = new StringBuilder(1000);
		sb.append(" SELECT A.*, B.*                                                             \n");
		sb.append("   FROM (                                                                    \n");
		sb.append(" 		SELECT tt1.rnum no,                                                 \n");
		sb.append(" 			   tt1.mem_id,                                                  \n");
		sb.append(" 			   tt1.mem_password,                                            \n");
		sb.append(" 			   tt1.mem_name,                                          		\n");
		sb.append(" 			   tt1.mem_email,             									\n");
		sb.append(" 			   tt1.mem_phonenum,                                            \n");
		sb.append(" 			   tt1.mem_jumin,                                               \n");
		sb.append(" 			   tt1.mem_div,                                                 \n");
		sb.append(" 			   TO_CHAR(tt1.mem_reg_dt,'YYYY/MM/DD') mem_reg_dt              \n");
		sb.append(" 		  FROM(                                                             \n");
		sb.append(" 				SELECT rownum rnum, t1.*                                    \n");
		sb.append(" 				  FROM (                                                    \n");
		sb.append(" 							SELECT *                                        \n");
		sb.append(" 							FROM yamu.member                                \n");
		sb.append(" 							--WHERE 조건                                                                           \n");
		//-------------------------------------------------------------------------------------------
		sb.append(search.toString());
		//-------------------------------------------------------------------------------------------		
		sb.append(" 							ORDER BY mem_reg_dt DESC                        \n");
		sb.append(" 				)t1                                                         \n");
		sb.append(" 				WHERE ROWNUM <=( ? * (? - 1  )+? )                          \n");
		sb.append(" 		)tt1                                                                \n");
		sb.append(" 		WHERE rnum >=( ? * (? - 1  )+1 )                                    \n");
		sb.append("   ) A                                                                       \n");
		sb.append("   CROSS JOIN (                                                              \n");
		sb.append(" 		SELECT COUNT(*) totalCnt                                            \n");  
		sb.append(" 		FROM yamu.member                                                    \n");
		sb.append(" 		--WHERE 조건                                                                                                          		\n");
		//-------------------------------------------------------------------------------------------
		sb.append(search.toString());
		//-------------------------------------------------------------------------------------------	
		sb.append("   ) B                                                                       \n");

		log.debug("3.sql: \n" + sb.toString());

		Object[] args = null ;
		log.debug("3.inVO.getSearchDiv(): \n" + inVO.getSearchDiv());
		log.debug("3.inVO.getSearchDiv().length(): \n" + inVO.getSearchDiv().length());
		
		if(!"".equals(inVO.getSearchDiv()) || inVO.getSearchDiv().length() > 0) {
			
			args = new Object[7];
			args[0] = inVO.getSearchWord();
			
			args[1] = inVO.getPageSize();
			args[2] = inVO.getPageNo();
			args[3] = inVO.getPageSize();
			args[4] = inVO.getPageSize();
			args[5] = inVO.getPageNo();			
			
			args[6] = inVO.getSearchWord();
		}else {
			args = new Object[5];
			args[0] = inVO.getPageSize();
			args[1] = inVO.getPageNo();
			args[2] = inVO.getPageSize();
			args[3] = inVO.getPageSize();   
			args[4] = inVO.getPageNo();
		}
		
		
		for(Object obj  :args) {
			log.debug("4.obj: \n" + obj);	
		}
		
		RowMapper<UserVO> users = new RowMapper<UserVO>() {

			@Override
			public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserVO outVO = new UserVO();
				// no
				outVO.setMem_id(rs.getString("mem_id"));
				outVO.setMem_password(rs.getString("mem_password"));
				outVO.setMem_name(rs.getString("mem_name"));
				outVO.setMem_email(rs.getString("mem_email"));
				outVO.setMem_phonenum(rs.getString("mem_phonenum"));
				outVO.setMem_jumin(rs.getString("mem_jumin"));
				outVO.setMem_div(rs.getInt("mem_div"));
				outVO.setMem_regdt(rs.getString("mem_reg_dt"));
				outVO.setTotalCnt(rs.getInt("totalCnt"));
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
		sb.append("INSERT INTO yamo.member 	 \n");
		sb.append("    mem_id,               \n");
		sb.append("    mem_password,         \n");
		sb.append("    mem_name,             \n");
		sb.append("    mem_email,            \n");
		sb.append("    mem_phonenum,         \n");
		sb.append("    mem_jumin,            \n");
		sb.append("    mem_div,              \n");
		sb.append("    mem_reg_dt            \n");
		sb.append(") VALUES ( ?,             \n");
		sb.append("           ?,             \n");
		sb.append("           ?,             \n");
		sb.append("           ?,             \n");
		sb.append("           ?,             \n");
		sb.append("           ?,             \n");
		sb.append("           ?,             \n");
		sb.append("           SYSDATE );     \n");

		Object[] args = { inVO.getMem_id(), inVO.getMem_password(), inVO.getMem_name()
				         ,inVO.getMem_email(),inVO.getMem_phonenum(),inVO.getMem_jumin()
				         ,inVO.getMem_div(),inVO.getMem_regdt()
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

		Object[] args = { inVO.getMem_id() };
		log.debug("1.param:");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		outVO = this.jdbcTemplate.queryForObject(sb.toString(), userMapper, args);

		// 조회 데이터가 없는 경우
		if (null == outVO) {
			throw new NullPointerException(inVO.getMem_id() + "(아이디)를 확인 하세요.");
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
		sb.append(" 	  	,email     = ?      	\n");
		sb.append(" 	 	,reg_dt    = SYSDATE 	\n");
		sb.append(" WHERE                     		\n");
		sb.append("    user_id = ?            		\n");
		
		Object[] args = {inVO.getMem_name()
				,inVO.getMem_password()
		        ,inVO.getMem_email()
		        ,inVO.getMem_id()
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
		
		Object[] args = {inVO.getMem_id()};
		
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
