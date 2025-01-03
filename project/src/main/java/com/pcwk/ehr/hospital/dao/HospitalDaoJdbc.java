package com.pcwk.ehr.hospital.dao;


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
import com.pcwk.ehr.hospital.domain.HospitalVO;

@Repository
public class HospitalDaoJdbc implements HospitalDao {
	
	final Logger log = LogManager.getLogger(HospitalDaoJdbc.class);

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<HospitalVO> hospitalMapper = new RowMapper<HospitalVO>() {

		@Override
		public HospitalVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			HospitalVO outVO = new HospitalVO();
			
			outVO.setHospital_id(rs.getString("hospital_id"));
			outVO.setHospital_name(rs.getString("hospital_name"));
			outVO.setHospital_addr(rs.getString("hospital_addr"));
			outVO.setHospital_div(rs.getString("hospital_div"));
			outVO.setHospital_etc(rs.getString("hospital_etc"));
			outVO.setHospital_mapimg(rs.getString("hospital_mapimg"));
			outVO.setHospital_tel(rs.getString("hospital_tel"));
			outVO.setHospital_lon(rs.getString("hospital_lon"));
			outVO.setHospital_lat(rs.getString("hospital_lat"));
			outVO.setHospital_time_mon(rs.getString("hospital_time_mon"));
			outVO.setHospital_time_tue(rs.getString("hospital_time_tue"));
			outVO.setHospital_time_wed(rs.getString("hospital_time_wed"));
			outVO.setHospital_time_thu(rs.getString("hospital_time_thu"));
			outVO.setHospital_time_fri(rs.getString("hospital_time_fri"));
			outVO.setHospital_time_sat(rs.getString("hospital_time_sat"));
			outVO.setHospital_time_sun(rs.getString("hospital_time_sun"));
			outVO.setHospital_time_hol(rs.getString("hospital_time_hol"));
		
			log.debug("outVO:" + outVO.toString());
			return outVO;
		}
		
	};

	public HospitalDaoJdbc() {
		super();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<HospitalVO> getAll() throws SQLException {
		List<HospitalVO> hList = new ArrayList<HospitalVO>();
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append("SELECT                      \n");
		sb.append("    hospital_id,            \n");
		sb.append("    hospital_name,          \n");
		sb.append("    hospital_addr,          \n");
		sb.append("    hospital_div,           \n");
		sb.append("    hospital_etc,           \n");
		sb.append("    hospital_mapimg,        \n");
		sb.append("    hospital_tel,           \n");
		sb.append("    hospital_lon,           \n");
		sb.append("    hospital_lat,           \n");
		sb.append("    hospital_time_mon,      \n");
		sb.append("    hospital_time_tue,      \n");
		sb.append("    hospital_time_wed,      \n");
		sb.append("    hospital_time_thu,      \n");
		sb.append("    hospital_time_fri,      \n");
		sb.append("    hospital_time_sat,      \n");
		sb.append("    hospital_time_sun,      \n");
		sb.append("    hospital_time_hol       \n");
		sb.append("FROM                        \n");
		sb.append("    hospital                \n");
		sb.append("ORDER BY hospital_name DESC \n");
		
		hList = this.jdbcTemplate.query(sb.toString(), hospitalMapper);
		
		return hList;
	}

	@Override
	public int doSave(HospitalVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append("INSERT INTO hospital (     \n");
		sb.append("    hospital_id,           \n");
		sb.append("    hospital_name,         \n");
		sb.append("    hospital_addr,         \n");
		sb.append("    hospital_div,          \n");
		sb.append("    hospital_etc,          \n");
		sb.append("    hospital_mapimg,       \n");
		sb.append("    hospital_tel,          \n");
		sb.append("    hospital_lon,          \n");
		sb.append("    hospital_lat,          \n");
		sb.append("    hospital_time_mon,     \n");
		sb.append("    hospital_time_tue,     \n");
		sb.append("    hospital_time_wed,     \n");
		sb.append("    hospital_time_thu,     \n");
		sb.append("    hospital_time_fri,     \n");
		sb.append("    hospital_time_sat,     \n");
		sb.append("    hospital_time_sun,     \n");
		sb.append("    hospital_time_hol      \n");
		sb.append(") VALUES ( ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ?,              \n");
		sb.append("           ? )             \n");
		
		Object[] args = { inVO.getHospital_id(), inVO.getHospital_name(), inVO.getHospital_addr(),
				inVO.getHospital_div(), inVO.getHospital_etc(), inVO.getHospital_mapimg(), inVO.getHospital_tel(),
				inVO.getHospital_lon(), inVO.getHospital_lat(), inVO.getHospital_time_mon(), inVO.getHospital_time_tue(),
				inVO.getHospital_time_wed(), inVO.getHospital_time_thu(), inVO.getHospital_time_fri(), inVO.getHospital_time_sat(),
				inVO.getHospital_time_sun(), inVO.getHospital_time_hol()
		};
		
		flag = jdbcTemplate.update(sb.toString(), args);
		
		return flag;
	}

	@Override
	public HospitalVO doSelectOne(HospitalVO inVO) throws SQLException, NullPointerException {
		HospitalVO outVO = null;
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append("SELECT                    \n");
		sb.append("    hospital_id,          \n");
		sb.append("    hospital_name,        \n");
		sb.append("    hospital_addr,        \n");
		sb.append("    hospital_div,         \n");
		sb.append("    hospital_etc,         \n");
		sb.append("    hospital_mapimg,      \n");
		sb.append("    hospital_tel,         \n");
		sb.append("    hospital_lon,         \n");
		sb.append("    hospital_lat,         \n");
		sb.append("    hospital_time_mon,    \n");
		sb.append("    hospital_time_tue,    \n");
		sb.append("    hospital_time_wed,    \n");
		sb.append("    hospital_time_thu,    \n");
		sb.append("    hospital_time_fri,    \n");
		sb.append("    hospital_time_sat,    \n");
		sb.append("    hospital_time_sun,    \n");
		sb.append("    hospital_time_hol     \n");
		sb.append("FROM                      \n");
		sb.append("    hospital              \n");
		sb.append("WHERE hospital_id = ?     \n");
		
		Object[] args = {inVO.getHospital_id()};
		
		outVO = jdbcTemplate.queryForObject(sb.toString(), hospitalMapper, args);
		
		if (null == outVO) {
			throw new NullPointerException(inVO.getHospital_id() + "(병원 id)를 확인하세요 !");
		}
		
		return outVO;
	}

	@Override
	public int doUpdate(HospitalVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(1000);
		sb.append("UPDATE hospital               \n");
		sb.append("SET   hospital_id = ?         \n");
		sb.append("		,hospital_name = ?       \n");
		sb.append("		,hospital_addr = ?       \n");
		sb.append("		,hospital_div = ?        \n");
		sb.append("		,hospital_etc = ?        \n");
		sb.append("		,hospital_mapimg = ?     \n");
		sb.append("		,hospital_tel = ?        \n");
		sb.append("		,hospital_lon = ?        \n");
		sb.append("		,hospital_lat = ?        \n");
		sb.append("		,hospital_time_mon = ?   \n");
		sb.append("		,hospital_time_tue = ?   \n");
		sb.append("		,hospital_time_wed = ?   \n");
		sb.append("		,hospital_time_thu = ?   \n");
		sb.append("		,hospital_time_fri = ?   \n");
		sb.append("		,hospital_time_sat = ?   \n");
		sb.append("		,hospital_time_sun = ?   \n");
		sb.append("		,hospital_time_hol = ?   \n");
		sb.append("WHERE hospital_name = ?       \n");

		Object[] args = {inVO.getHospital_id(), inVO.getHospital_name(), inVO.getHospital_addr(),
				inVO.getHospital_div(), inVO.getHospital_etc(), inVO.getHospital_mapimg(), inVO.getHospital_tel(),
				inVO.getHospital_lon(), inVO.getHospital_lat(), inVO.getHospital_time_mon(), inVO.getHospital_time_tue(),
				inVO.getHospital_time_wed(), inVO.getHospital_time_thu(), inVO.getHospital_time_fri(), inVO.getHospital_time_sat(),
				inVO.getHospital_time_sun(), inVO.getHospital_time_hol(), inVO.getHospital_name()};
		
		flag = jdbcTemplate.update(sb.toString(), args);
		
		return flag;
	}

	@Override
	public int doDelete(HospitalVO inVO) throws SQLException {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(100);
		sb.append("DELETE FROM hospital    \n");
		sb.append("WHERE hospital_id = ?  \n");
		
		Object[] args = {inVO.getHospital_id()};
		
		flag = jdbcTemplate.update(sb.toString(), args);
		
		return flag;
	}

	@Override
	public List<HospitalVO> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<HospitalVO> hospitalList = new ArrayList<HospitalVO>();
		StringBuilder search = new StringBuilder(100);
		log.debug(hospitalList);
		
		if("10".equals(inVO.getSearchDiv())) { // 병원 이름
			search.append("WHERE hospital_name LIKE '%' || ? || '%' \n");
		} else if("20".equals (inVO.getSearchDiv())) { // 병원 분류
			search.append("WHERE hospital_div LIKE '%' || ? || '%' \n");
		} else if("30".equals (inVO.getSearchDiv())) { // 지역 구
			search.append("WHERE hospital_addr LIKE '%' || ? || '%' \n");
		}

		StringBuilder sb = new StringBuilder(2000);
	    sb.append(" SELECT A.*, B.*                                              \n");
	    sb.append(" FROM (                                                       \n");
	    sb.append("     SELECT                                                   \n");
	    sb.append("			tt1.rnum no,                                         \n");
	    sb.append("			tt1.hospital_id,      								 \n");
	    sb.append("			tt1.hospital_name,    								 \n");
	    sb.append("			tt1.hospital_addr,    								 \n");
	    sb.append("			tt1.hospital_div,     								 \n");
	    sb.append("			tt1.hospital_etc,     								 \n");
	    sb.append("			tt1.hospital_mapimg,  								 \n");
	    sb.append("			tt1.hospital_tel,     								 \n");
	    sb.append("			tt1.hospital_lon,     								 \n");
	    sb.append("			tt1.hospital_lat,     								 \n");
	    sb.append("			tt1.hospital_time_mon,     					     	 \n");
	    sb.append("			tt1.hospital_time_tue,     					     	 \n");
	    sb.append("			tt1.hospital_time_wed,							     \n");
	    sb.append("			tt1.hospital_time_thu,     						     \n");
	    sb.append("			tt1.hospital_time_fri,     						     \n");
	    sb.append("			tt1.hospital_time_sat,     						     \n");
	    sb.append("			tt1.hospital_time_sun,     						     \n");
	    sb.append("			tt1.hospital_time_hol      						     \n");
	    sb.append("     FROM (                                   				 \n");
	    sb.append("             SELECT ROWNUM rnum, t1.*                         \n");
	    sb.append("             FROM (                                           \n");
	    sb.append("                     SELECT *                                 \n");
	    sb.append("                     FROM hospital                            \n");
	    sb.append("                     -- WHERE 조건                             			 \n");
	    //-------------------------------------------------------------------------   
	    sb.append(search.toString());                                                 
	    //-------------------------------------------------------------------------
	    sb.append("                     ORDER BY hospital_name DESC              \n");
	    sb.append("             ) t1                                             \n");
	    sb.append("             WHERE ROWNUM <= (? * (? - 1) + ?)                \n");
	    sb.append("     ) tt1                                                    \n");
	    sb.append("     WHERE rnum >= (? * (? - 1) + 1)                          \n");
	    sb.append(" ) A                                                          \n");
	    sb.append(" CROSS JOIN (                                                 \n"); 
	    sb.append("     SELECT COUNT(*) totalCnt                                 \n");
	    sb.append("     FROM hospital                                            \n");
	    sb.append("     -- WHERE 조건                                             					 \n");
	    //-------------------------------------------------------------------------   
	    sb.append(search.toString());                                                 
	    //-------------------------------------------------------------------------   
		sb.append(" ) B                                                          \n");
	    
		log.debug("1.sql: \n" + sb.toString());
		
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
		
		RowMapper<HospitalVO> hospitals = new RowMapper<HospitalVO>() {
			@Override
			public HospitalVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				HospitalVO outVO = new HospitalVO();
				outVO.setNo(rs.getInt("no"));
				outVO.setHospital_id(rs.getString("hospital_id"));
				outVO.setHospital_name(rs.getString("hospital_name"));
				outVO.setHospital_addr(rs.getString("hospital_addr"));
				outVO.setHospital_div(rs.getString("hospital_div"));
				outVO.setHospital_etc(rs.getString("hospital_etc"));
				outVO.setHospital_mapimg(rs.getString("hospital_mapimg")); 
				outVO.setHospital_tel(rs.getString("hospital_tel"));
				outVO.setHospital_lon(rs.getString("hospital_lon"));
				outVO.setHospital_lat(rs.getString("hospital_lat"));
				outVO.setHospital_time_mon(rs.getString("hospital_time_mon"));
				outVO.setHospital_time_tue(rs.getString("hospital_time_tue"));
				outVO.setHospital_time_wed(rs.getString("hospital_time_wed"));
				outVO.setHospital_time_thu(rs.getString("hospital_time_thu"));
				outVO.setHospital_time_fri(rs.getString("hospital_time_fri"));
				outVO.setHospital_time_sat(rs.getString("hospital_time_sat"));
				outVO.setHospital_time_sun(rs.getString("hospital_time_sun"));
				outVO.setHospital_time_hol(rs.getString("hospital_time_hol"));
				outVO.setTotalCnt(rs.getInt("totalCnt"));
				
				log.debug("hospital:{}", outVO);
				
				return outVO;
			}
		};
		
		hospitalList = this.jdbcTemplate.query(sb.toString(), hospitals, args);
		
		return hospitalList;
	}

	@Override
	public int getCount() throws SQLException {
		int count = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) totalCnt \n");
		sb.append("FROM hospital            \n");
		
		count = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
		log.debug("count:{}",count);
		
		return count;
	}
	
}
