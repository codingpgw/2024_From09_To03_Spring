package com.pcwk.ehr.email.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.email.domain.EmailAuthVO;
import com.pcwk.ehr.user.dao.UserDaoJdbc;

@Repository
public class EmailDaoImpl implements EmailDao {
	final Logger log = LogManager.getLogger(UserDaoJdbc.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<EmailAuthVO> emailMapper = new RowMapper<EmailAuthVO>() {

		@Override
		public EmailAuthVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmailAuthVO outVO = new EmailAuthVO();
			outVO.setMemEmail(rs.getString("memEmail"));
			outVO.setAuthCode(rs.getString("authCode"));
			outVO.setAuthTime(rs.getString("authTime"));
			outVO.setAuthStatus(rs.getInt("authStatus"));

			log.debug("2.outVO: " + outVO.toString());
			return outVO;
		}
	};

	public EmailDaoImpl() {

	}

	@Override
	public int doDelete(EmailAuthVO inVO) throws SQLException {
		int flag = 0;

		StringBuilder sb = new StringBuilder(100);
		sb.append("DELETE FROM yamu.email_auth  \n");
		sb.append("WHERE  mem_email = ?      	\n");

		Object[] args = { inVO.getMemEmail() };
		log.debug("1.param seq: " + inVO.getMemEmail());

		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);

		return flag;
	}

	@Override
	public int doSave(EmailAuthVO inVO) throws SQLException {
		int flag = 0;

		StringBuilder sb = new StringBuilder(200);
		sb.append("INSERT INTO yamu.email_auth ( \n");
		sb.append("	    mem_email,               \n");
		sb.append("	    auth_status              \n");
		sb.append("	) VALUES (  ?,              \n");
		sb.append("	            ?)              \n");

		Object[] args = { inVO.getMemEmail(), 0 };

		log.debug("1.param:");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		flag = this.jdbcTemplate.update(sb.toString(), args);
		log.debug("2.flag:{}", flag);

		return flag;
	}

	@Override
	public void deleteAll() throws SQLException {

	}

	@Override
	public int doCheck(EmailAuthVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EmailAuthVO doSelectOne(EmailAuthVO inVO) throws SQLException, NullPointerException {
		EmailAuthVO outVO = null;
		StringBuilder sb = new StringBuilder(200);
		sb.append("SELECT                                                     \n");
		sb.append("    mem_email,                                             \n");
		sb.append("    auth_code,                                             \n");
		sb.append("    TO_CHAR(auth_time,'YYYY/MM/DD HH24:MI:SS') auth_time,  \n");
		sb.append("    auth_status                                            \n");
		sb.append("FROM                                                       \n");
		sb.append("    yamu.email_auth                                        \n");
		sb.append("WHERE mem_email = ?                                        \n");

		Object[] args = { inVO.getMemEmail() };

		log.debug("1.seq:{}", inVO.getMemEmail());

		outVO = jdbcTemplate.queryForObject(sb.toString(), this.emailMapper, args);

		return outVO;
	}
	
	@Override
	public String doSelectAuthCodeByEmail(String memEmail) throws SQLException {
	    String sql = "SELECT auth_code FROM yamu.email_auth WHERE mem_email = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{memEmail}, String.class);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}

	@Override
	public String doSelectAuthTimeByEmail(String memEmail) throws SQLException {
		 String sql = "SELECT auth_time FROM yamu.email_auth WHERE mem_email = ?";
		    try {
		        return jdbcTemplate.queryForObject(sql, new Object[]{memEmail}, String.class);
		    } catch (EmptyResultDataAccessException e) {
		        return null;
		    }
	}
}
