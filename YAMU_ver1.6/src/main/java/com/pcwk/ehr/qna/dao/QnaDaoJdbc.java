package com.pcwk.ehr.qna.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.pcwk.ehr.qna.domain.QnaVO;

@Repository
public class QnaDaoJdbc implements QnaDao {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public QnaDaoJdbc() {
		super();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<QnaVO> qnaMapper = new RowMapper<QnaVO>() {
		@Override
		public QnaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			QnaVO outVO = new QnaVO();
			
			outVO.setQnaNo(rs.getInt("qna_no"));
			outVO.setQuestion(rs.getString("qna_question"));
			outVO.setAnswer(rs.getString("qna_answer"));
			outVO.setAdmin(rs.getNString("qna_admin"));
			outVO.setRegDt(rs.getString("qna_reg_dt"));
			
			log.debug("1. outVO: {}", outVO.toString());
			
			return outVO;
		}
	};
	
	@Override
	public int doDelete(QnaVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb=new StringBuilder(100);		
		sb.append(" DELETE FROM qna		\n");
		sb.append(" WHERE qna_no = ?    \n");
		
		log.debug("1.sql:\n " + sb.toString());
		
		Object[] args = {inVO.getQnaNo()};
		log.debug("2.param cmnNo: " + inVO.getQnaNo());
		
		flag = jdbcTemplate.update(sb.toString(), args);
		
		log.debug("3.flag:{}", flag);
		
		return flag;
	}
	
	@Override
	public int doUpdate(QnaVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb=new StringBuilder(500);
		
		sb.append(" UPDATE qna             		  \n");
		sb.append(" SET                           \n");
		sb.append("     qna_question = ?,      	  \n");
		sb.append("     qna_answer	 = ?          \n");
		sb.append(" WHERE                         \n");
		sb.append("     qna_no = ?		          \n");
		
		Object[] args = {inVO.getQuestion(), inVO.getAnswer(), inVO.getQnaNo()};
		//log.debug("0.sql:\n{}",sb.toString());
		log.debug("1.param:{}", Arrays.toString(args));		
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		return flag;
	}
	
	@Override
	public List<QnaVO> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<QnaVO> qnaList = new ArrayList<QnaVO>();
		StringBuilder search = new StringBuilder(100);
		log.debug(qnaList);
		
		if ("10".equals(inVO.getSearchDiv())){    
			search.append(" WHERE qna_question      LIKE '%' || ? || '%'  \n");
		} else if ("20".equals(inVO.getSearchDiv())){
			search.append(" WHERE qna_answer    	LIKE '%' || ? || '%'  \n");
		} else if ("30".equals(inVO.getSearchDiv())){
			search.append(" WHERE qna_question      LIKE '%' || ? || '%'  \n");
			search.append(" 	OR                     	   		    	\n");
			search.append(" 	  qna_answer  	    LIKE '%' || ? || '%'  \n");
		}
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append(" SELECT A.*, B.*                                           \n");   
		sb.append(" FROM (                                                    \n");   
		sb.append("     SELECT                                                \n");   
		sb.append(" 		tt1.rnum no,                                      \n");   
		sb.append(" 		tt1.qna_no,                                       \n");   
		sb.append(" 		tt1.qna_question,                                 \n");   
		sb.append(" 		tt1.qna_answer,                                   \n");     
		sb.append(" 		tt1.qna_admin,                                    \n");
		sb.append(" 		TO_CHAR(tt1.qna_reg_dt, 'YYYY/MM/DD') qna_reg_dt  \n");   
		sb.append("     FROM (                                                \n");   
		sb.append("             SELECT ROWNUM rnum, t1.*                      \n");   
		sb.append("             FROM (                                        \n");   
		sb.append("                     SELECT *                              \n");   
		sb.append("                     FROM qna                              \n");
		sb.append("                     -- WHERE 조건                                                      	  \n");
		//-------------------------------------------------------------------------
		sb.append(search.toString());                                              
		//-------------------------------------------------------------------------
		sb.append("                     ORDER BY qna_reg_dt DESC              \n");   
		sb.append("             ) t1                                          \n");   
		sb.append("             WHERE ROWNUM <= (? * (? - 1) + ?)             \n");
		sb.append("     ) tt1                                                 \n");   
		sb.append("     WHERE rnum >= (? * (? - 1) + 1)                       \n");
		sb.append(" ) A                                                       \n");   
		sb.append(" CROSS JOIN (                                              \n");   
		sb.append("     SELECT COUNT(*) totalCnt                              \n");   
		sb.append("     FROM qna                                              \n");
		sb.append("     -- WHERE 조건                                                                                  	  \n");
		//-------------------------------------------------------------------------
		sb.append(search.toString());                                              
		//-------------------------------------------------------------------------
		sb.append(" ) B                                                       \n");
		
		log.debug("1.sql: \n" + sb.toString());
		
		RowMapper<QnaVO> qnas = new RowMapper<QnaVO>() {
			@Override
			public QnaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QnaVO outVO = new QnaVO();
				
				outVO.setNo(rs.getInt("no"));
				outVO.setQnaNo(rs.getInt("qna_no"));
				outVO.setQuestion(rs.getString("qna_question"));
				outVO.setAnswer(rs.getString("qna_answer"));
				outVO.setAdmin(rs.getNString("qna_admin"));
				outVO.setRegDt(rs.getString("qna_reg_dt"));
				outVO.setTotalCnt(rs.getInt("totalCnt"));
				
				log.debug("1. outVO: {}", outVO.toString());
				
				return outVO;
			}
		};
		
		Object[] args = null ;
		
		if ("10".equals( inVO.getSearchDiv()) || "20".equals( inVO.getSearchDiv())) {
			args = new Object[7];
			
			args[0] = inVO.getSearchWord();
			
			args[1] = inVO.getPageSize();
			args[2] = inVO.getPageNo();
			args[3] = inVO.getPageSize();
			args[4] = inVO.getPageSize();   
			args[5] = inVO.getPageNo();
					
			args[6] = inVO.getSearchWord();			
			
		} else if ("30".equals( inVO.getSearchDiv())) {
			args = new Object[9];
			
			args[0] = inVO.getSearchWord();
			args[1] = inVO.getSearchWord();
			
			args[2] = inVO.getPageSize();
			args[3] = inVO.getPageNo();
			args[4] = inVO.getPageSize();
			args[5] = inVO.getPageSize();   
			args[6] = inVO.getPageNo();
					
			args[7] = inVO.getSearchWord();		
			args[8] = inVO.getSearchWord();		
			
		} else {
			args = new Object[5];
			
			args[0] = inVO.getPageSize();
			args[1] = inVO.getPageNo();
			args[2] = inVO.getPageSize();
			args[3] = inVO.getPageSize();   
			args[4] = inVO.getPageNo();		
		}
		
		qnaList = jdbcTemplate.query(sb.toString(), qnas, args);
		
		for (QnaVO vo : qnaList) {
			log.debug(vo);
		}
		
		return qnaList;
	}
	
	// 등록
	@Override
	public int doSave(QnaVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(200);
		sb.append(" INSERT INTO qna (  		\n");
		sb.append("     qna_question,  		\n");
		sb.append("     qna_answer,  		\n");
		sb.append("     qna_admin,  		\n");
		sb.append("     qna_reg_dt  		\n");
		sb.append(" ) VALUES ( ?,  			\n");
		sb.append("            ?,  			\n");
		sb.append("            ?,  			\n");
		sb.append("            SYSDATE )  	\n");
		log.debug("1.sql:\n" + sb.toString());
		
		Object[] args = {inVO.getQuestion(), inVO.getAnswer(), inVO.getAdmin()};
		
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
	public QnaVO doSelectOne(QnaVO inVO) throws SQLException, NullPointerException {
		QnaVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                             \n");
		sb.append("     qna_question,                                  \n");
		sb.append("     qna_answer,                                    \n");
		sb.append("     qna_admin,                                     \n");
		sb.append("     TO_CHAR(qna_reg_dt, 'YYYY/MM/DD') qna_reg_dt   \n");
		sb.append(" FROM                                               \n");
		sb.append(" 	qna                                            \n");
		sb.append(" WHERE qna_no = ?                                   \n");
		
		Object[] args = {inVO.getQnaNo()};
		
		log.debug("1. outVO: {}", sb.toString());
		for (Object obj : args) {
			log.debug(obj.toString());
		}
		
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), qnaMapper, args);

		if (outVO == null) {
			throw new NullPointerException(inVO.getQnaNo() + "(번호)를 확인하세요.");
		}

		return outVO;
	}
}
