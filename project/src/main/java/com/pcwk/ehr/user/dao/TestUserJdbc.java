package com.pcwk.ehr.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.TestUserVO;
import com.pcwk.ehr.user.domain.UserVO;

@Repository
public class TestUserJdbc implements TestUserDao {
final Logger log = LogManager.getLogger(UserDaoJdbc.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<TestUserVO> userMapper = new RowMapper<TestUserVO>() {

		@Override
		public TestUserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestUserVO outVO = new TestUserVO();
			outVO.setMem_id(rs.getString("mem_id"));
			outVO.setMem_password(rs.getString("mem_password"));
			outVO.setMem_name(rs.getString("mem_name"));
			outVO.setMem_email(rs.getString("mem_email"));
			//------------------------------------------------------------------
			outVO.setMem_phone(rs.getNString("mem_phone"));
			outVO.setMem_jumin(rs.getString("mem_jumin"));
			outVO.setMem_div(rs.getInt("mem_div"));
			outVO.setMem_regdt(rs.getString("mem_regdt"));
			
			
			log.debug("2.outVO: " + outVO.toString());
			return outVO;
		}

	};
	
	
	public TestUserJdbc() {
		super();
	}

	@Override
	public int doDelete(TestUserVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(TestUserVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TestUserVO> doRetrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestUserVO> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int doSave(TestUserVO inVO) throws SQLException {
		int flag = 0;

		StringBuilder sb = new StringBuilder(200);
		sb.append("INSERT INTO test_member ( \n");
		sb.append("    mem_id,               \n");
		sb.append("    mem_password,         \n");
		sb.append("    mem_name,             \n");
		sb.append("    mem_email,            \n");
		sb.append("    mem_phone,            \n");
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
		
		Object[] args = {inVO.getMem_id(), inVO.getMem_password(), inVO.getMem_name(),
				inVO.getMem_email(), inVO.getMem_phone()
				
		};
		
		log.debug("1.param:");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		flag = this.jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

	@Override
	public TestUserVO doSelectOne(TestUserVO inVO) throws SQLException, NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

}
