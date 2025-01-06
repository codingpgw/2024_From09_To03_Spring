package com.pcwk.ehr.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.member.domain.MemberVO;

@Repository
public class MemberDaoJdbc implements MemberDao {
	final Logger log = LogManager.getLogger(MemberDaoJdbc.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<MemberVO> memberMapper = new RowMapper<MemberVO>() {
		@Override
		public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberVO outVO = new MemberVO();
			
			outVO.setMemId(rs.getNString("mem_id"));
			outVO.setPassword(rs.getNString("mem_password"));
			outVO.setName(rs.getNString("mem_name"));
			outVO.setEmail(rs.getNString("mem_email"));
			outVO.setPhone(rs.getNString("mem_phonenum"));
			outVO.setBirthDt(rs.getNString("mem_birth_dt"));
			outVO.setMemDiv(rs.getNString("mem_div"));
			outVO.setRegDt(rs.getNString("mem_reg_dt"));
			
			log.debug("1. outVO: {}", outVO.toString());
			
			return outVO;
		}
	};
	
	public MemberDaoJdbc() {
		super();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	@Override
	public List<MemberVO> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		StringBuilder search = new StringBuilder(100);
		log.debug(memberList);
		
		if("10".equals(inVO.getSearchDiv())) { // 회원id
			search.append("WHERE mem_id LIKE '%' || ? || '%' \n");
		} else if("20".equals (inVO.getSearchDiv())) { // 이름
			search.append("WHERE mem_name LIKE '%' || ? || '%' \n");
		} else if("30".equals (inVO.getSearchDiv())) { // 이메일
			search.append("WHERE mem_email LIKE '%' || ? || '%' \n");
		}
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append(" SELECT A.*, B.*                                                \n");
		sb.append(" FROM (                                                         \n");
		sb.append("     SELECT                                                     \n");
		sb.append("			tt1.rnum no,                                           \n");
		sb.append("			tt1.mem_id,                                            \n");
		sb.append("			tt1.mem_password,                                      \n");
		sb.append("			tt1.mem_name,                                          \n");
		sb.append("			tt1.mem_email,                                         \n");
		sb.append("			tt1.mem_phonenum,                                      \n");
		sb.append("			TO_CHAR(tt1.mem_birth_dt, 'YYYY/MM/DD') mem_birth_dt,  \n");
		sb.append("			tt1.mem_div,                                           \n");
		sb.append("			TO_CHAR(tt1.mem_reg_dt, 'YYYY/MM/DD') mem_reg_dt       \n");
		sb.append("     FROM (                                                     \n");
		sb.append("             SELECT ROWNUM rnum, t1.*                           \n");
		sb.append("             FROM (                                             \n");
		sb.append("                     SELECT *                                   \n");
		sb.append("                     FROM member                                \n");
		sb.append("                     -- WHERE 조건                                                       \n");
		//-------------------------------------------------------------------------
		sb.append(search.toString());                                              
		//-------------------------------------------------------------------------
		sb.append("                     ORDER BY mem_reg_dt DESC                   \n");
		sb.append("             ) t1                                               \n");
		sb.append("             WHERE ROWNUM <= (? * (? - 1) + ?)                  \n");
		sb.append("     ) tt1                                                      \n");
		sb.append("     WHERE rnum >= (? * (? - 1) + 1)                            \n");
		sb.append(" ) A                                                            \n");
		sb.append(" CROSS JOIN (                                                   \n");
		sb.append("     SELECT COUNT(*) totalCnt                                   \n");
		sb.append("     FROM member                                                \n");
		sb.append("     -- WHERE 조건                                                                                   \n");
		//-------------------------------------------------------------------------
		sb.append(search.toString());                                              
		//-------------------------------------------------------------------------
		sb.append(" ) B                                                            \n");
		
		log.debug("1.sql: \n" + sb.toString());
		
//		Object[] args = {inVO.getPageSize(), inVO.getPageNo(), inVO.getPageSize(),
//						 inVO.getPageSize(), inVO.getPageNo()};
		
		Object[] args = null;
		log.debug("2.inVO.getSearchDiv():" + inVO.getSearchDiv());
		log.debug("3.inVO.getSearchDiv().length(): " + inVO.getSearchDiv().length());
		
		if (!"".equals(inVO.getSearchDiv()) || inVO.getSearchDiv().length() > 0) {
			args = new Object[7];
			args[0] = inVO.getSearchWord();
			
			args[1] = inVO.getPageSize();
			args[2] = inVO.getPageNo();
			args[3] = inVO.getPageSize();
			args[4] = inVO.getPageSize();
			args[5] = inVO.getPageNo();
			
			args[6] = inVO.getSearchWord();
		} else {
			args = new Object[5];
			args[0] = inVO.getPageSize();
			args[1] = inVO.getPageNo();
			args[2] = inVO.getPageSize();
			args[3] = inVO.getPageSize();
			args[4] = inVO.getPageNo();
		}
		
		for (Object obj: args) {
			log.debug("4.obj: \n" + obj);
		}	
		
		RowMapper<MemberVO> members = new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO outVO = new MemberVO();
				outVO.setNo(rs.getInt("no"));
				outVO.setMemId(rs.getNString("mem_id"));
				outVO.setPassword(rs.getNString("mem_password"));
				outVO.setName(rs.getNString("mem_name"));
				outVO.setEmail(rs.getNString("mem_email"));
				outVO.setPhone(rs.getNString("mem_phonenum"));
				outVO.setBirthDt(rs.getNString("mem_birth_dt"));

				if ("10".equals(rs.getNString("mem_div"))) {
					outVO.setMemDiv("일반 사용자");
				} else if ("20".equals(rs.getNString("mem_div"))) {
					outVO.setMemDiv("관리자");
				} else {
					outVO.setMemDiv("알 수 없음");
				}
				
				outVO.setRegDt(rs.getNString("mem_reg_dt"));
				outVO.setTotalCnt(rs.getInt("totalCnt"));
					
				log.debug("5. outVO: {}", outVO);
				
				return outVO;
			}
		};
		
		memberList = this.jdbcTemplate.query(sb.toString(), members, args);
		
		return memberList;
	}
	
	@Override
	public int saveAll() {
		int cnt = 0;
		
		StringBuilder sb = new StringBuilder(300);
		sb.append(" INSERT INTO                                           \n");
		sb.append("      member                                           \n");
		sb.append(" SELECT                                                \n");
		sb.append("     'kcho' || level mem_id,                           \n");
		sb.append("     '4321' mem_password,                              \n");
		sb.append("     '조규희' || level mem_name,                         \n");
		sb.append("     'rbgml1238@naver.com' mem_email,                  \n");
		sb.append("     '01012345678' mem_phonenum,                       \n");
		sb.append("     '2000/11/11' mem_birth_dt,           	          \n");
		sb.append("     '10' mem_div,                                     \n");
		sb.append("     SYSDATE - level mem_reg_dt                        \n");
		sb.append(" FROM                                                  \n");
		sb.append("      dual                                             \n");
		sb.append(" CONNECT BY LEVEL <= 502                               \n");
		
		cnt = this.jdbcTemplate.update(sb.toString());
		log.debug("총 등록 건수: {}", cnt);
		
		return cnt;
	}
	
	@Override
	public List<MemberVO> getAll() throws SQLException {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                    		  	   \n");
		sb.append("     mem_id,                              		  	   \n");
		sb.append("     mem_password,                                 	   \n");
		sb.append("     mem_name,                           		  	   \n");
		sb.append("     mem_email,                            	      	   \n");
		sb.append("     mem_phonenum,                           	  	   \n");
		sb.append("     TO_CHAR(mem_birth_dt, 'YYYY/MM/DD') mem_birth_dt,  \n");
		sb.append("     mem_div,                               		  	   \n");
		sb.append("     TO_CHAR(mem_reg_dt, 'YYYY/MM/DD') mem_reg_dt  	   \n");
		sb.append(" FROM                                      		  	   \n");
		sb.append(" 	member                              		  	   \n");
		sb.append(" ORDER BY mem_id DESC                		      	   \n");
		
		memberList = this.jdbcTemplate.query(sb.toString(), memberMapper);
		
		return memberList;
	}
	
	@Override
	public void deleteAll() throws SQLException {
		StringBuilder sb = new StringBuilder(200);
		sb.append(" DELETE FROM member \n");
		log.debug("1. sql:\n" + sb.toString());
		
		this.jdbcTemplate.update(sb.toString());
	}
	
	@Override
	public int getCount() throws SQLException {
		int count = 0;

		StringBuilder sb = new StringBuilder(50);
		sb.append("SELECT COUNT(*) totalCnt    \n");
		sb.append("FROM                        \n");
		sb.append("		member                 \n");
		
		count = this.jdbcTemplate.queryForObject(sb.toString(), Integer.class);
		log.debug("1. count: {}", count);
		
		return count;
	}
	
	// 등록
	@Override
	public int doSave(MemberVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(200);
		sb.append(" INSERT INTO member (                  \n");
		sb.append("     mem_id,                           \n");
		sb.append("     mem_password,                     \n");
		sb.append("     mem_name,                         \n");
		sb.append("     mem_email,                        \n");
		sb.append("     mem_phonenum,                     \n");
		sb.append("     mem_birth_dt,     				  \n");
		sb.append("     mem_div,                          \n");
		sb.append("     mem_reg_dt                        \n");
		sb.append(" ) VALUES ( ?,                         \n");
		sb.append("            ?,                         \n");
		sb.append("            ?,                         \n");
		sb.append("            ?,                         \n");
		sb.append("            ?,                         \n");
		sb.append("            TO_DATE(?, 'YYYY/MM/DD'),  \n");
		sb.append("            10,                        \n");
		sb.append("            SYSDATE )                  \n");
		//log.debug("1.sql:\n" + sb.toString());
		
		Object[] args = {inVO.getMemId(), inVO.getPassword(), inVO.getName(),
						 inVO.getEmail(), inVO.getPhone(), inVO.getBirthDt()};
		
		log.debug("1. param: ");
		for (Object obj : args) {
			log.debug(obj.toString());
		}
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		log.debug("2. flag: {}", flag);
		
		return flag;
	}
	
	// 단건 조회
	@Override
	public MemberVO doSelectOne(MemberVO inVO) throws SQLException, NullPointerException {
		MemberVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                                 \n");
		sb.append("     mem_id,                                            \n");
		sb.append("     mem_password,                                      \n");
		sb.append("     mem_name,                                          \n");
		sb.append("     mem_email,                                         \n");
		sb.append("     mem_phonenum,                                      \n");
		sb.append("     TO_CHAR(mem_birth_dt, 'YYYY/MM/DD') mem_birth_dt,  \n");
		sb.append("     mem_div,                                           \n");
		sb.append("     TO_CHAR(mem_reg_dt, 'YYYY/MM/DD') mem_reg_dt       \n");
		sb.append(" FROM                                                   \n");
		sb.append(" 	member                                             \n");
		sb.append(" WHERE mem_id = ?                                       \n");
		
		Object[] args = {inVO.getMemId()};
		
		log.debug("1. outVO: {}", sb.toString());
		for (Object obj : args) {
			log.debug(obj.toString());
		}
		
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), memberMapper, args);

		if (outVO == null) {
			throw new NullPointerException(inVO.getMemId() + "(아이디)를 확인하세요.");
		}

		return outVO;
	}
	
	// 수정
	@Override
	public int doUpdate(MemberVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(100);
		sb.append(" UPDATE member                  \n");
		sb.append(" SET                            \n");
		sb.append(" 	mem_password 	= ?,       \n");
		sb.append(" 	mem_name 		= ?,       \n");
		sb.append(" 	mem_email 		= ?,       \n");
		sb.append(" 	mem_phonenum 	= ?,       \n");
		sb.append(" 	mem_birth_dt	= ?,       \n");
		sb.append(" 	mem_reg_dt 		= SYSDATE  \n");
		sb.append(" WHERE                          \n");
		sb.append(" 	mem_id = ?                 \n");
		
		Object[] args = {inVO.getPassword(), inVO.getName(), 
						 inVO.getEmail(), inVO.getPhone(), inVO.getBirthDt(),
						 inVO.getMemId()};
		
		log.debug("1. param: ");
		int i = 1;
		for (Object obj : args) {
			log.debug(++i + "." + obj.toString());
		}
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		log.debug("2. flag: {}", flag);
		
		return flag;
	}
	
	// 삭제
	@Override
	public int doDelete(MemberVO inVO) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(50);
		sb.append(" DELETE FROM member   \n");
		sb.append(" WHERE mem_id = ?     \n");
		
		Object[] args = {inVO.getMemId()};
		log.debug("1. param: ");
		
		int i = 0;
		for (Object obj : args) {
			log.debug(++i + "." + obj.toString());
		}
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		
		log.debug("2.flag: {}", flag);
		
		return flag;
	}

	@Override
	public int idCheck(MemberVO inVO) throws SQLException {
		int count = 0;
		StringBuilder sb = new StringBuilder(50);
		sb.append(" SELECT COUNT(*) cnt   \n");
		sb.append(" FROM member           \n");
		sb.append(" WHERE mem_id = ?      \n");
		
		log.debug("1. param: " + inVO);
		log.debug("2. sql:\n" + sb.toString());
		
		Object[] args = {inVO.getMemId()};
		
		count = jdbcTemplate.queryForObject(sb.toString(),  Integer.class, args);
		log.debug("3. count: " + count);
		
		return count;
	}

	@Override
	public int idPassCheck(MemberVO inVO) throws SQLException {
		int count = 0;
		StringBuilder sb = new StringBuilder(50);
		sb.append(" SELECT COUNT(*) cnt   	\n");
		sb.append(" FROM member           	\n");
		sb.append(" WHERE mem_id = ?   		\n");
		sb.append(" AND mem_password = ?    \n");
		
		log.debug("1. param: " + inVO);
		log.debug("2. sql:\n" + sb.toString());
		
		Object[] args = {inVO.getMemId(), inVO.getPassword()};
		
		count = jdbcTemplate.queryForObject(sb.toString(),  Integer.class, args);
		log.debug("3. count: " + count);
		
		return count;
	}
}