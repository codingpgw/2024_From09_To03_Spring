package com.pcwk.ehr.board.dao;

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

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<BoardVO> boardMapper = new RowMapper<BoardVO>() {

		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO outVO = new BoardVO();
			outVO.setSeq(rs.getInt("seq"));
			outVO.setTitle(rs.getString("title"));
			outVO.setContents(rs.getString("contents"));
			outVO.setDiv(rs.getString("div"));
			outVO.setReadCnt(rs.getInt("read_cnt"));
			outVO.setRegId(rs.getString("reg_id"));
			outVO.setRegDt(rs.getString("reg_dt"));
			outVO.setModId(rs.getString("mod_id"));
			outVO.setModDt(rs.getString("mod_dt"));
			
			log.debug("2.outVO: " + outVO.toString());
			return outVO;
		}
		
	};
	
	public BoardDaoImpl() {
		
	}
	
	@Override
	public int doDelete(BoardVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(100);
		sb.append("DELETE FROM board \n");
		sb.append("WHERE seq = ?     \n");
		
		//log.debug("0.sql:\n"+sb.toString());
		
		Object[] args = {inVO.getSeq()};
		log.debug("1.param seq: "+inVO.getSeq());
		
		flag = jdbcTemplate.update(sb.toString(),args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

	@Override
	public int doUpdate(BoardVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb = new StringBuilder(500);
		sb.append("UPDATE board                     \n");
		sb.append("SET                              \n");
		sb.append("    title = ?,                   \n");
		sb.append("    contents = ?,                \n");
		sb.append("    div = ?,                     \n");
		sb.append("    read_cnt = NVL(read_cnt,0)  ,\n");
		sb.append("    mod_id = ?,                  \n");
		sb.append("    mod_dt = sysdate             \n");
		sb.append("WHERE                            \n");
		sb.append("        seq = ?                  \n");
		
		Object[] args = {
				inVO.getTitle(),
				inVO.getContents(),
				inVO.getDiv(),
				inVO.getModId(),
				inVO.getSeq()
		};
		
		log.debug("1.param:{}",Arrays.toString(args));
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

	@Override
	public List<BoardVO> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		log.debug("1.param:"+inVO);
		
		StringBuilder where = new StringBuilder(100);
		//검색조건:10(제목), 20(내용), 30(제목+내용)
		
		if("10".equals(inVO.getSearchDiv())) {
			where.append("AND title   LIKE ?||'%'      \n");
		}else if("20".equals(inVO.getSearchDiv())) {
			where.append("AND contents   LIKE ?||'%'   \n");
		}else if("30".equals(inVO.getSearchDiv())) {
			where.append("AND (title      LIKE ?||'%'      \n");
			where.append("      OR                         \n");
			where.append("     contents   LIKE ?||'%'      \n");
			where.append("    )\n");
		}
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append("SELECT a.*,b.*                                                                  \n");
		sb.append("  FROM (                                                                        \n");
		sb.append("	SELECT tt1.rnum no,                                                            \n");
		sb.append("       tt1.seq,                                                                 \n");
		sb.append("       tt1.title,                                                               \n");
		sb.append("       tt1.mod_id,                                                              \n");
		sb.append("       TO_CHAR(tt1.mod_dt,'YYYY/MM/DD') mod_dt,                                 \n");
		sb.append("       tt1.read_cnt                                                             \n");
		sb.append("	FROM(                                                                          \n");
		sb.append("		SELECT ROWNUM rnum, t1.*                                                   \n");
		sb.append("		FROM(                                                                      \n");
		sb.append("			SELECT *                                                               \n");
		sb.append("			FROM board                                                             \n");
		sb.append("			--검색조건                                                             								   \n");
		sb.append("         WHERE div = ?                                                          \n");
		//----------------------------------------------------------------------------------------------
		sb.append(where.toString());
		//----------------------------------------------------------------------------------------------
		sb.append("		ORDER BY mod_dt DESC                                                       \n");
		sb.append("        )t1                                                                     \n");
		sb.append("		WHERE ROWNUM <= ( ? * (? - 1  )+? )                                        \n");
		sb.append("		)tt1                                                                       \n");
		sb.append("	WHERE rnum >=( ? * (? - 1  )+1 )                                               \n");
		sb.append("	)a                                                                             \n");
		sb.append("	CROSS JOIN (                                                                   \n");
		sb.append("		--페이지 번호 : 전체 글 수                                                 								   \n");
		sb.append("		SELECT COUNT(*) totalCnt                                                   \n");
		sb.append("		FROM board                                                                 \n");
		sb.append("		--검색 조건                                                                									   \n");
		sb.append("     WHERE div = ?                                                          	   \n");
		sb.append(where.toString());
		sb.append("  )b                                                                            \n");
		
		RowMapper<BoardVO> boards = new RowMapper<BoardVO>() {

			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO outVO = new BoardVO();
				
				outVO.setNo(rs.getInt("no"));
				//------------------------------------------------------------
				outVO.setSeq(rs.getInt("seq"));
				outVO.setTitle(rs.getString("title"));
				
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setReadCnt(rs.getInt("read_cnt"));			
				
				outVO.setTotalCnt(rs.getInt("totalCnt"));
				
				log.debug("2.outVO: " + outVO.toString());
				return outVO;
			}
			
		};
		
		Object[] args = null;
		
		
		if("10".equals(inVO.getSearchDiv()) || "20".equals(inVO.getSearchDiv())) {
			args = new Object[9];
			Map<String, String> searchOptionMap = inVO.getOptionSearch();
			
			args[0] = searchOptionMap.get("div");
			args[1] = inVO.getSearchWord();
			
			args[2] = inVO.getPageSize();
			args[3] = inVO.getPageNo();
			args[4] = inVO.getPageSize();
			args[5] = inVO.getPageSize();
			args[6] = inVO.getPageNo();			
			
			args[7] = searchOptionMap.get("div");
			args[8] = inVO.getSearchWord();
			
		}else if("30".equals(inVO.getSearchDiv())) {
			
			args = new Object[11];
			Map<String, String> searchOptionMap = inVO.getOptionSearch();
			
			args[0] = searchOptionMap.get("div");
			args[1] = inVO.getSearchWord();
			args[2] = inVO.getSearchWord();
			
			args[3] = inVO.getPageSize();
			args[4] = inVO.getPageNo();
			args[5] = inVO.getPageSize();
			args[6] = inVO.getPageSize();
			args[7] = inVO.getPageNo();			
			
			args[8] = searchOptionMap.get("div");
			args[9] = inVO.getSearchWord();
			args[10] = inVO.getSearchWord();
			
		}else {
			args = new Object[7];
			Map<String, String> searchOptionMap = inVO.getOptionSearch();
			
			args[0] = searchOptionMap.get("div");
			
			args[1] = inVO.getPageSize();
			args[2] = inVO.getPageNo();
			args[3] = inVO.getPageSize();
			args[4] = inVO.getPageSize();
			args[5] = inVO.getPageNo();			
			
			args[6] = searchOptionMap.get("div");
		}
		
		
		boardList = jdbcTemplate.query(sb.toString(), boards, args);
		
		for(BoardVO vo : boardList) {
			log.debug(vo);
		}
		
		return boardList;
	}

	@Override
	public int doSave(BoardVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(200);
		sb.append("INSERT INTO board (           \n");
		sb.append("	    seq,                     \n");
		sb.append("	    title,                   \n");
		sb.append("	    contents,                \n");
		sb.append("	    div,                     \n");
		sb.append("	    read_cnt,                \n");
		sb.append("	    reg_id,                  \n");
		sb.append("	    reg_dt,                  \n");
		sb.append("	    mod_id,                  \n");
		sb.append("	    mod_dt                   \n");
		sb.append("	) VALUES ( ?,				 \n");
		sb.append("	           ?,                \n");
		sb.append("	           ?,                \n");
		sb.append("	           ?,                \n");
		sb.append("	           ?,                \n");
		sb.append("	           ?,                \n");
		sb.append("	           SYSDATE,          \n");
		sb.append("	           ?,                \n");
		sb.append("	           SYSDATE )         \n");
		
		Object[] args = {
				inVO.getSeq(),
				inVO.getTitle(),
				inVO.getContents(),
				inVO.getDiv(),
				0,
				inVO.getRegId(),
				inVO.getRegId()
		};
		
		//log.debug("0.sql:\n{}",sb.toString());
		log.debug("1.param:{}",Arrays.toString(args));
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);

		return flag;
	}
	
	
	@Override
	public BoardVO doSelectOne(BoardVO inVO) throws SQLException, NullPointerException {
		BoardVO outVO = null;
		
		StringBuilder sb = new StringBuilder(200);
		sb.append("SELECT                                            \n");
	    sb.append("	seq,                                             \n");
	    sb.append("	title,                                           \n");
	    sb.append("	contents,                                        \n");
	    sb.append("	div,                                             \n");
	    sb.append("	read_cnt,                                        \n");
	    sb.append("	reg_id,                                          \n");
	    sb.append("	TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt,  \n");
	    sb.append("	mod_id,                                          \n");
	    sb.append("	TO_CHAR(mod_dt,'YYYY/MM/DD HH24:MI:SS') mod_dt   \n");
	    sb.append("FROM                                              \n");
	    sb.append("		board                                        \n");
	    sb.append("WHERE seq = ?                                     \n");
		
	    //log.debug("0.sql:\n{}",sb.toString());
	    Object[] args = {inVO.getSeq()};
	    
	    log.debug("1.seq:{}",inVO.getSeq());
	    
	    outVO = jdbcTemplate.queryForObject(sb.toString(), this.boardMapper, args);
	    
		return outVO;
	}

	@Override
	public void deleteAll() throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(100);
		sb.append("DELETE FROM board \n");
		
		log.debug("0.sql:\n"+sb.toString());
		
		flag = jdbcTemplate.update(sb.toString());
		log.debug("2.flag:{}", flag);
	}

	@Override
	public int saveAll() {
		int cnt = 0;
		StringBuilder sb = new StringBuilder(500);
		
		sb.append("INSERT INTO board                                    \n");
		sb.append("SELECT board_seq.nextval seq,                        \n");
		sb.append("	   '제목 '|| LEVEL title,                             \n");
		sb.append("	   '내용 '|| LEVEL contents,                          \n");
		sb.append("	   DECODE(MOD(LEVEL,2),1,'10','20') div,            \n");
		sb.append("	   0 read_cnt,                                      \n");
		sb.append("	   DECODE(MOD(LEVEL,2),1,'admin','james') reg_id,   \n");
		sb.append("	   SYSDATE-LEVEL/60 reg_dt,                         \n");
		sb.append("	   DECODE(MOD(LEVEL,2),1,'admin','james') mod_id,   \n");
		sb.append("	   SYSDATE-LEVEL/60 mod_dt                          \n");
		sb.append("  FROM dual                                          \n");
		sb.append("CONNECT BY LEVEL <= 24                               \n");
		
		cnt = jdbcTemplate.update(sb.toString());
		log.debug("총 등록 건수:{}",cnt);
		
		
		return cnt;
	}

	@Override
	public int getBoardSequence() {
		int seq = 0;
		StringBuilder sb = new StringBuilder(100);
		sb.append("SELECT board_seq.nextval AS SEQ  \n");
		sb.append("  FROM dual                      \n");
		
		log.debug("0.sql:\n{}",sb.toString());
				
		seq = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
		log.debug("1.seq:{}",seq);
		
		return seq;
	}

	@Override
	public int doReadCntUpdate(BoardVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb = new StringBuilder(500);
		sb.append("UPDATE board                     \n");
		sb.append("SET                              \n");
		sb.append("    read_cnt = NVL(read_cnt,0)+1 \n");
		sb.append("WHERE                            \n");
		sb.append("        seq = ?                  \n");
		
		Object[] args = {
				inVO.getSeq()
		};
		
		log.debug("1.param:{}",Arrays.toString(args));
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

}
