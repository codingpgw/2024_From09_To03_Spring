package com.pcwk.ehr.hospital.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.dao.UserDaoJdbc;
import com.pcwk.ehr.user.domain.HospitalVO;
import com.pcwk.ehr.user.domain.UserVO;

@Repository
public class HospitalDaoJdbc implements HospitalDao {
	final Logger log = LogManager.getLogger(UserDaoJdbc.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<HospitalVO> hopitalMapper = new RowMapper<HospitalVO>() {

		@Override
		public HospitalVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			HospitalVO outVO = new HospitalVO();
			outVO.setHospital_id(rs.getString("hospital_id"));
			outVO.setHospital_addr(rs.getString("hospital_addr"));
			outVO.setHospital_div(rs.getString("hospital_div"));
			outVO.setHospital_etc(rs.getString("hospital_etc"));
			outVO.setHospital_name(rs.getString("hospital_name"));
			outVO.setHospital_mapimg(rs.getString("hospital_mapimg"));
			outVO.setHospital_tel(rs.getString("hospital_tel"));
			outVO.setHospital_lon(rs.getInt("hospital_lon"));
			outVO.setHospital_lat(rs.getInt("hospital_lat"));
			outVO.setHospital_time(rs.getString("hospital_time"));
			// -----------------------------------------------------------------

			log.debug("2.outVO: " + outVO.toString());
			return outVO;
		}

	};

	public HospitalDaoJdbc() {

	}

	@Override
	public int doDelete(HospitalVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(HospitalVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HospitalVO> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;

		List<HospitalVO> hospitalList = new ArrayList<HospitalVO>();
		StringBuilder search = new StringBuilder(100);

		if ("10".equals(inVO.getSearchDiv())) { // 병원 이름
			search.append(" 							WHERE hospital_name LIKE ?||'%'  \n");
		} else if ("20".equals(inVO.getSearchDiv())) { // 병원과
			search.append(" 							WHERE hospital_div LIKE ?||'%'     \n");
		} else if ("30".equals(inVO.getSearchDiv())) { // 주소
			search.append(" 							WHERE hospital_addr LIKE '%'||?||'%'    \n");
		}

		log.debug("2.inVO: " + inVO);

		StringBuilder sb = new StringBuilder(1000);
		sb.append(" SELECT A.*, B.*                                                             \n");
		sb.append("   FROM (                                                                    \n");
		sb.append(" 		SELECT tt1.rnum no,                                                 \n");
		sb.append(" 			   tt1.hospital_name,                                               \n");
		sb.append(" 			   tt1.hospital_addr,                                            	\n");
		sb.append(" 			   tt1.hospital_tel,                                          		\n");
		sb.append(" 			   tt1.hospital_lon,             									\n");
		sb.append(" 			   tt1.hospital_lat,                                            	\n");
		sb.append(" 			   tt1.hospital_div,                                             \n");
		sb.append(" 			   tt1.hospital_etc                                                 \n");
		sb.append(" 		  FROM(                                                             \n");
		sb.append(" 				SELECT rownum rnum, t1.*                                    \n");
		sb.append(" 				  FROM (                                                    \n");
		sb.append(" 							SELECT *                                        \n");
		sb.append(" 							FROM yamu.hospital                              \n");
		sb.append(
				" 							--WHERE 조건                                                                           \n");
		// -------------------------------------------------------------------------------------------
		sb.append(search.toString());
		// -------------------------------------------------------------------------------------------
		sb.append(" 							ORDER BY hospital_id                         	\n");
		sb.append(" 				)t1                                                         \n");
		sb.append(" 				WHERE ROWNUM <=( ? * (? - 1  )+? )                          \n");
		sb.append(" 		)tt1                                                                \n");
		sb.append(" 		WHERE rnum >=( ? * (? - 1  )+1 )                                    \n");
		sb.append("   ) A                                                                       \n");
		sb.append("   CROSS JOIN (                                                              \n");
		sb.append(" 		SELECT COUNT(*) totalCnt                                            \n");
		sb.append(" 		FROM yamu.hospital                                                  \n");
		sb.append(
				" 		--WHERE 조건                                                                                                          		\n");
		// -------------------------------------------------------------------------------------------
		sb.append(search.toString());
		// -------------------------------------------------------------------------------------------
		sb.append("   ) B                                                                       \n");

		log.debug("3.sql: \n" + sb.toString());

		Object[] args = null;
		log.debug("3.inVO.getSearchDiv(): \n" + inVO.getSearchDiv());
		log.debug("3.inVO.getSearchDiv().length(): \n" + inVO.getSearchDiv().length());

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

		for (Object obj : args) {
			log.debug("4.obj: \n" + obj);
		}

		RowMapper<HospitalVO> hospitals = new RowMapper<HospitalVO>() {

			@Override
			public HospitalVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				HospitalVO outVO = new HospitalVO();
				outVO.setHospital_name(rs.getString("hospital_name"));
				outVO.setHospital_addr(rs.getString("hospital_addr"));
				outVO.setHospital_tel(rs.getString("hospital_tel"));
				outVO.setHospital_lon(rs.getInt("hospital_lon"));
				outVO.setHospital_lat(rs.getInt("hospital_lat"));
				outVO.setHospital_div(rs.getString("hospital_div"));
				outVO.setHospital_etc(rs.getString("hospital_etc"));
				outVO.setTotalCnt(rs.getInt("totalCnt"));

				log.debug("outVO:{}", outVO);

				return outVO;
			}

		};

		hospitalList = this.jdbcTemplate.query(sb.toString(), hospitals, args);

		return hospitalList;
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int doSave(HospitalVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HospitalVO doSelectOne(HospitalVO inVO) throws SQLException, NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

}
