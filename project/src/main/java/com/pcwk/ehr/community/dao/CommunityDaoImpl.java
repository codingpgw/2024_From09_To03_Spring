package com.pcwk.ehr.community.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.community.domain.CommunityVO;

@Repository
public class CommunityDaoImpl implements CommunityDao {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public CommunityDaoImpl() {
		
	}
	
	private RowMapper<CommunityVO> communityMapper = new RowMapper<CommunityVO>() {
		@Override
		public CommunityVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommunityVO outVO = new CommunityVO();
			outVO.setCmnNo(rs.getInt("cmn_no"));
			outVO.setMemId(rs.getNString("mem_id"));
			outVO.setTitle(rs.getNString("cmn_title"));
			outVO.setContent(rs.getNString("cmn_content"));
			outVO.setCategory(rs.getNString("cmn_category"));
			outVO.setDiv(rs.getNString("cmn_div"));
			outVO.setRegDt(rs.getNString("cmn_reg_dt"));
			outVO.setModDt(rs.getNString("cmn_mod_dt"));
			outVO.setView(rs.getInt("cmn_view"));
			
			log.debug("1. outVO: {}", outVO.toString());
			
			return outVO;
		}
	};
	@Transactional
	@Override
	public int doDelete(CommunityVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb=new StringBuilder(100);		
		sb.append(" DELETE FROM community	\n");
		sb.append(" WHERE cmn_no = ?       	\n");
		
		log.debug("1.sql:\n " + sb.toString());
		
		Object[] args = {inVO.getCmnNo()};
		log.debug("2.param cmnNo: " + inVO.getCmnNo());
		
		String testQuery = "SELECT COUNT(*) FROM community";
		int count = jdbcTemplate.queryForObject(testQuery, Integer.class);
		log.debug("Test query result: " + count);
		
		flag = jdbcTemplate.update(sb.toString(), args);
		
		log.debug("3.flag:{}", flag);
		
		return flag;
	}

	@Override
	public int doUpdate(CommunityVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb=new StringBuilder(500);
		
		sb.append(" UPDATE community                            \n");
		sb.append(" SET                                     \n");
		sb.append("     cmn_title    = ?,                   \n");
		sb.append("     cmn_content	 = ?,                   \n");
		sb.append("     cmn_mod_dt   = sysdate              \n");
		sb.append(" WHERE                                   \n");
		sb.append("     cmn_no = ?		                    \n");
		
		Object[] args = {inVO.getTitle(), inVO.getContent(), inVO.getCmnNo()};
		//log.debug("0.sql:\n{}",sb.toString());
		log.debug("1.param:{}", Arrays.toString(args));		
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		return flag;
	}

	@Override
	public List<CommunityVO> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<CommunityVO>  communityList = new ArrayList<CommunityVO>();
		log.debug("1.param: " + inVO);
		
		StringBuilder where = new StringBuilder(100);
		//검색조건: 10(제목), 20(내용), 30(제목+내용)
		
		if("10".equals( inVO.getSearchDiv() )){    
			where.append(" AND cmn_title      LIKE '%' || ? || '%'  \n");
		}else if("20".equals( inVO.getSearchDiv() )){
			where.append(" AND cmn_content    LIKE '%' || ? || '%'  \n");
		}else if("30".equals( inVO.getSearchDiv() )){
			where.append(" AND ( cmn_title    LIKE '%' || ? || '%'  \n");
			where.append("       OR                     	   		\n");
			where.append("       cmn_content  LIKE '%' || ? || '%'  \n");
			where.append("     )									\n");
		}
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append(" SELECT A.*,B.*                                           		\n");
		sb.append(" FROM (                                                			\n");
		sb.append(" 		SELECT tt1.rnum no,                              		\n");
		sb.append(" 			   tt1.cmn_no,                               		\n");
		sb.append(" 			   tt1.mem_id,                           			\n");
		sb.append(" 			   tt1.cmn_title,                            		\n");
		sb.append(" 			   tt1.cmn_content,                           		\n");
		sb.append(" 			   tt1.cmn_category,                           		\n");
		sb.append(" 			   tt1.cmn_div,                                		\n");
		sb.append(" 			   TO_CHAR(tt1.cmn_reg_dt,'YYYY/MM/DD') cmn_reg_dt, \n");
		sb.append(" 			   TO_CHAR(tt1.cmn_mod_dt,'YYYY/MM/DD') cmn_mod_dt, \n");
		sb.append(" 			   tt1.cmn_view                             		\n");
		sb.append(" 		FROM (                                          		\n");
		sb.append(" 			SELECT ROWNUM rnum, t1.*                     		\n");
		sb.append(" 			FROM (                                       		\n");
		sb.append(" 				SELECT *                                 		\n");
		sb.append(" 				  FROM community                           		\n");		
		sb.append(" 				  --검색조건                                                                   \n");
		sb.append(" 				  WHERE cmn_div = ?		                        \n");
		//-----------------------------------------------------------------------
		sb.append(where.toString());
		//-----------------------------------------------------------------------
		sb.append(" 				 ORDER BY cmn_mod_dt DESC   	                \n");
		sb.append(" 			)t1                                       		    \n");
		sb.append(" 			WHERE ROWNUM <= (? * (? - 1) + ?)                   \n");
		sb.append(" 		)tt1                                             		\n");
		sb.append(" 		WHERE rnum >= (? * (? - 1) + 1)                         \n");
		sb.append(" )A                                               				\n");
		sb.append(" CROSS JOIN (                                     				\n");
		sb.append(" 		--페이지 번호 : 전체글수                                                                  \n");
		sb.append(" 	SELECT COUNT(*) totalCnt                         			\n");
		sb.append(" 	FROM community	                                    		\n");
		sb.append(" 	--검색조건                                                                                		\n");
		sb.append(" 	WHERE cmn_div = ?                                 			\n");
		//-----------------------------------------------------------------------
		sb.append(where.toString());
		//-----------------------------------------------------------------------		
		sb.append(" )B		      				                                   \n");
		
		log.debug("1.sql: \n" + sb.toString());
		
		RowMapper<CommunityVO> boards = new RowMapper<CommunityVO>() {

			@Override
			public CommunityVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommunityVO outVO = new CommunityVO();
				outVO.setNo(rs.getInt("no"));
				//--------------------------------------------------------------
				outVO.setCmnNo(rs.getInt("cmn_no"));
				outVO.setMemId(rs.getString("mem_id"));
				outVO.setTitle(rs.getString("cmn_title"));
				outVO.setContent(rs.getString("cmn_content"));
				outVO.setCategory(rs.getString("cmn_category"));
				outVO.setDiv(rs.getString("cmn_div"));
				outVO.setRegDt(rs.getString("cmn_reg_dt"));
				outVO.setModDt(rs.getString("cmn_mod_dt"));
				outVO.setView(rs.getInt("cmn_view"));
				//--------------------------------------------------------------
				outVO.setTotalCnt(rs.getInt("totalCnt"));
				
				
				log.debug("2.outVO: " + outVO.toString());
				return outVO;
			}
		}; 
		
		Object[] args = null ;
		
		if ("10".equals( inVO.getSearchDiv()) || "20".equals( inVO.getSearchDiv())) {
			args = new Object[9];
			Map<String, String> searchOptionMap = inVO.getOptionSearch();
			
			args[0] = searchOptionMap.get("cmn_div");
			args[1] = inVO.getSearchWord();
			
			args[2] = inVO.getPageSize();
			args[3] = inVO.getPageNo();
			args[4] = inVO.getPageSize();
			args[5] = inVO.getPageSize();   
			args[6] = inVO.getPageNo();
					
			args[7] = searchOptionMap.get("cmn_div");
			args[8] = inVO.getSearchWord();			
			
			
		} else if ("30".equals( inVO.getSearchDiv())) {
			args = new Object[11];
			Map<String, String> searchOptionMap = inVO.getOptionSearch();
			
			args[0] = searchOptionMap.get("cmn_div");
			args[1] = inVO.getSearchWord();
			args[2] = inVO.getSearchWord();
			
			args[3] = inVO.getPageSize();
			args[4] = inVO.getPageNo();
			args[5] = inVO.getPageSize();
			args[6] = inVO.getPageSize();   
			args[7] = inVO.getPageNo();
					
			args[8] = searchOptionMap.get("cmn_div");
			args[9] = inVO.getSearchWord();		
			args[10] = inVO.getSearchWord();		
			
			
		} else {
			args = new Object[7];
			Map<String, String> searchOptionMap = inVO.getOptionSearch();
			
			args[0] = searchOptionMap.get("cmn_div");
			
			args[1] = inVO.getPageSize();
			args[2] = inVO.getPageNo();
			args[3] = inVO.getPageSize();
			args[4] = inVO.getPageSize();   
			args[5] = inVO.getPageNo();		
			
			args[6] = searchOptionMap.get("cmn_div");
						
		}
		
		communityList = jdbcTemplate.query(sb.toString(), boards, args);
		
		for( CommunityVO vo : communityList) {
			log.debug(vo);
		}
		
		return communityList;
	}

	@Override
	public int doSave(CommunityVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb = new StringBuilder(200);
		//seq는 화면에서 조회  파라메터로 전달 !
		sb.append(" INSERT INTO community (  \n");
		sb.append("     mem_id,              \n");
		sb.append("     cmn_title,           \n");
		sb.append("     cmn_content,         \n");
		sb.append("     cmn_category,        \n");
		sb.append("     cmn_div,             \n");
		sb.append("     cmn_reg_dt,          \n");
		sb.append("     cmn_mod_dt,          \n");
		sb.append("     cmn_view             \n");
		sb.append(" ) VALUES ( ?,            \n");
		sb.append("            ?,            \n");
		sb.append("            ?,            \n");
		sb.append("            ?,            \n");
		sb.append("            ?,            \n");
		sb.append("            SYSDATE,      \n");
		sb.append("            SYSDATE,      \n");
		sb.append("            ? )           \n");
		log.debug("1.sql:\n{}", sb.toString());
		
		Object[] args = {inVO.getMemId(), inVO.getTitle(), inVO.getContent(),
						 inVO.getCategory(), inVO.getDiv(), 0};
		
		log.debug("2.param: {}", Arrays.toString(args));
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("3.flag: {}", flag);
		
		return flag;
	}

	@Override
	public CommunityVO doSelectOne(CommunityVO inVO) throws SQLException, NullPointerException {
		CommunityVO  outVO = null;
		StringBuilder sb = new StringBuilder(200);		
		sb.append(" SELECT                                                         \n");
		sb.append("     cmn_no,                                                    \n");
		sb.append("     mem_id,                                                    \n");
		sb.append("     cmn_title,                                                 \n");
		sb.append("     cmn_content,                                               \n");
		sb.append("     cmn_category,                                              \n");
		sb.append("     cmn_div,                                                   \n");
		sb.append("     TO_CHAR(cmn_reg_dt, 'YYYY/MM/DD HH24:MI:SS') cmn_reg_dt,   \n");
		sb.append("     TO_CHAR(cmn_mod_dt, 'YYYY/MM/DD HH24:MI:SS') cmn_mod_dt,   \n");
		sb.append("     cmn_view                                                   \n");
		sb.append(" FROM                                                           \n");
		sb.append("     community                                                  \n");
		sb.append(" WHERE cmn_no = ?											   \n");
		
		//log.debug("0.sql:\n{}",sb.toString());
		Object[] args = {inVO.getCmnNo()};
		
		log.debug("1.cmn_no: {}", inVO.getCmnNo());
		
		outVO = jdbcTemplate.queryForObject(sb.toString(), this.communityMapper, args);
		log.debug("2.outVO: {}",outVO);
		
		return outVO;
	}

	@Override
	public void deleteAll() throws SQLException {
		int flag = 0;
		
		StringBuilder sb=new StringBuilder(50);		
		sb.append(" DELETE FROM community  \n");
		
		//log.debug("0.sql:\n "+sb.toString());
		
		flag = jdbcTemplate.update(sb.toString());
		
		log.debug("2.flag: {}", flag);
				
	}
	
	@Override
	public int saveAll() {
		int cnt = 0;

		StringBuilder sb = new StringBuilder(500);
		sb.append(" INSERT INTO community (                                      \n");
		sb.append("		   mem_id,                                               \n");
		sb.append("		   cmn_title,                                            \n");
		sb.append("		   cmn_content,                                          \n");
		sb.append("		   cmn_category,                                         \n");
		sb.append("		   cmn_div,                                              \n");
		sb.append("		   cmn_reg_dt,                                           \n");
		sb.append("		   cmn_mod_dt,                                           \n");
		sb.append("		   cmn_view)                                       		 \n");
		sb.append(" SELECT                          				 			 \n");
		sb.append(" 	   DECODE(MOD(level, 2), 1, 'admin', 'kcho1') mem_id,    \n");
		sb.append("        '제목 '|| level cmn_title,                             \n");
		sb.append(" 	   '내용 '|| level cmn_content,                           \n");
		sb.append(" 	   DECODE(MOD(level, 2), 1, '10', '20') cmn_category,    \n");
		sb.append(" 	   DECODE(MOD(level, 2), 1, '10', '20') cmn_div,         \n");
		sb.append(" 	   SYSDATE - level / 60 cmn_reg_dt,                      \n");
		sb.append(" 	   SYSDATE - level / 60 cmn_mod_dt,                      \n");
		sb.append(" 	   0 cmn_view                                       	 \n");
		sb.append("   FROM dual                                             	 \n");
		sb.append("  CONNECT BY LEVEL <=24                                   	 \n");
		
		cnt =jdbcTemplate.update(sb.toString());
		log.debug("총 등록 건수 :{}", cnt);
		
		return cnt;
	}
	
	@Override
	public int getBoardSequence() {
		int no = 0;
		StringBuilder sb = new StringBuilder(200);		
		sb.append("  SELECT community_no.nextval AS cmn_no \n");
		sb.append("    FROM dual                     \n");
		
		//log.debug("0.sql:\n{}",sb.toString());
		no = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
		log.debug("1.seq: {}", no);
		
		return no;
	}

	//자신이 등록한 글은 조회 count 증가 않됨!
	@Override
	public int doReadCntUpdate(CommunityVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb=new StringBuilder(500);
		
		sb.append(" UPDATE community                          \n");
		sb.append(" SET                                       \n");
		sb.append("     cmn_view    =  NVL(cmn_view, 0) + 1   \n");
		sb.append(" WHERE                                     \n");
		sb.append("         cmn_no     = ?		              \n");
		sb.append("   AND   mem_id 	  != ?	                  \n");
		
		Object[] args = {inVO.getNo(), inVO.getMemId()};
		log.debug("1.sql:\n{}", sb.toString());
		log.debug("2.param: {}", Arrays.toString(args));		
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("3.flag: {}", flag);
		return flag;
	}
}