package com.pcwk.ehr.community.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.domain.CommunityVO;

@Repository
public class CommunityDaoImpl implements CommunityDao {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<CommunityVO> communityMapper = new RowMapper<CommunityVO>() {      
		
		@Override
		public CommunityVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommunityVO outVO = new CommunityVO();
			
			outVO.setCmnNo(rs.getInt("cmn_no"));
			outVO.setMemId(rs.getString("mem_id"));
			outVO.setCmnTitle(rs.getString("cmn_title"));   
			outVO.setCmnCategory(rs.getString("cmn_category"));
			outVO.setCmnContent(rs.getString("cmn_content"));
			outVO.setCmnDiv(rs.getString("cmn_div"));
			outVO.setCmnRegDt(rs.getString("cmn_reg_dt"));   
			outVO.setCmnModDt(rs.getString("cmn_mod_dt"));  
			outVO.setCmnView(rs.getInt("cmn_view"));
			
			log.debug("2.outVO: " + outVO.toString());
			return outVO;
		}
		
	};
	
	public CommunityDaoImpl() {
		
	}

	@Override
	public int doDelete(CommunityVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb = new StringBuilder(100);
		sb.append("DELETE FROM yamu.community \n");
		sb.append("WHERE cmn_no = ?           \n");
		
		log.debug("0.sql:\n"+sb.toString());
		
		Object[] args = {inVO.getCmnNo()};
		log.debug("1.param seq: "+inVO.getCmnNo());
		
		flag = jdbcTemplate.update(sb.toString(),args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

	@Override
	public int doUpdate(CommunityVO inVO) throws SQLException {
		int flag = 0;
		StringBuilder sb = new StringBuilder(500);
		sb.append("UPDATE yamu.community          \n");
		sb.append("SET cmn_title = ?,             \n");
		sb.append("    cmn_content = ?,           \n");
		sb.append("    cmn_category = ?,          \n");
		sb.append("    cmn_div = ?,               \n");
		sb.append("    cmn_mod_dt = sysdate,      \n");
		sb.append("    cmn_view = NVL(cmn_view,0) \n");
		sb.append("WHERE cmn_no = ?               \n");
		
		Object[] args = {
				inVO.getCmnTitle(),
				inVO.getCmnContent(),
				inVO.getCmnCategory(),
				inVO.getCmnDiv(),
				inVO.getNo()
		};
		log.debug("1.param:{}",Arrays.toString(args));
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

	@Override
	public List<CommunityVO> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<CommunityVO> communityList = new ArrayList<CommunityVO>();
		
		//category를 통해 board의 div와 같은 구별을 실행
		//cmn_div의 진료과 구분의 케이스는 왼쪽의 aside에 잇는 목록을 통해서든 다른 방식을 통해 호출하는 것이 좋아보임
		log.debug("1.param:"+inVO);
		
		return null;
	}

	@Override
	public int doSave(CommunityVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(200);
		sb.append("INSERT INTO yamu.community (    \n");
		sb.append("	    cmn_no,                    \n");
		sb.append("	    mem_id,                    \n");
		sb.append("	    cmn_title,                 \n");
		sb.append("	    cmn_content,               \n");
		sb.append("	    cmn_category,              \n");
		sb.append("	    cmn_div,                   \n");
		sb.append("	    cmn_reg_dt,                \n");
		sb.append("	    cmn_mod_dt,                \n");
		sb.append("	    cmn_view                   \n");
		sb.append("	    ) VALUES ( ?,              \n");
		sb.append("	           ?,                  \n");
		sb.append("	           ?,                  \n");
		sb.append("	           ?,                  \n");
		sb.append("	           ?,                  \n");
		sb.append("	           ?,                  \n");
		sb.append("	           sysdate,            \n");
		sb.append("	           sysdate,            \n");
		sb.append("	           ? );                \n");
		
		Object[] args = {
			inVO.getCmnNo(),	
			inVO.getMemId(),
			inVO.getCmnTitle(),
			inVO.getCmnContent(),
			inVO.getCmnCategory(),
			inVO.getCmnDiv(),
			0	
		};
		
		log.debug("0.sql:\n{}",sb.toString());
		log.debug("1.param:{}",Arrays.toString(args));
		
		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

	@Override
	public CommunityVO doSelectOne(CommunityVO inVO) throws SQLException, NullPointerException {
		CommunityVO outVO = null;
		
		StringBuilder sb = new StringBuilder(200);
		sb.append("SELECT                                                       \n");
		sb.append("    cmn_no,                                                  \n");
		sb.append("    mem_id,                                                  \n");
		sb.append("    cmn_title,                                               \n");
		sb.append("    cmn_content,                                             \n");
		sb.append("    cmn_category,                                            \n");
		sb.append("    cmn_div,                                                 \n");
		sb.append("    TO_CHAR(cmn_reg_dt,'YYYY/MM/DD HH24:MI:SS') cmn_reg_dt,  \n");
		sb.append("    TO_CHAR(cmn_mod_dt,'YYYY/MM/DD HH24:MI:SS') cmn_mod_dt,  \n");
		sb.append("    cmn_view                                                 \n");
		sb.append("  FROM                                                       \n");
		sb.append("  	yamu.community                                          \n");
		sb.append(" WHERE cmn_no = ?                                            \n");
		
		log.debug("0.sql:\n{}",sb.toString());
	    Object[] args = {inVO.getCmnNo()};
	    
	    log.debug("1.seq:{}",inVO.getCmnNo());
	    
	    outVO = jdbcTemplate.queryForObject(sb.toString(), this.communityMapper, args);
	    
		return outVO;
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int saveAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkHighCmnView(CommunityVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
