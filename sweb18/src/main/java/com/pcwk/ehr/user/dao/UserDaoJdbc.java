package com.pcwk.ehr.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.domain.UserVO;

@Repository
public class UserDaoJdbc implements UserDao {
	final Logger log = LogManager.getLogger(UserDaoJdbc.class);
	
	final String NAMESPACE = "com.pcwk.ehr.user";
	final String DOT 	   = ".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

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
		
		String statement = NAMESPACE+DOT+"saveAll";
		log.debug("0. parameter:없음");
		log.debug("1. statement:{}",statement);
		cnt = sqlSessionTemplate.insert(statement);
		
		log.debug("총 등록 건수 :{}", cnt);

		return cnt;
	}

	@Override
	public List<UserVO> doRetrieve(DTO dto) {

		SearchVO inVO = (SearchVO) dto;
		log.debug("1. param:{}",inVO);
		List<UserVO> userList = new ArrayList<UserVO>();
		String statement = NAMESPACE+DOT+"doRetrieve";
		
		userList = sqlSessionTemplate.selectList(statement, inVO);
		
		for(UserVO vo :userList) {
			log.debug(vo);
		}
		
		return userList;
	}

	@Override
	public List<UserVO> getAll() throws SQLException {
		List<UserVO> userList = new ArrayList<UserVO>();
		
		String statement = NAMESPACE+DOT+"getAll";
		log.debug("0. parameter:없음");
		log.debug("1. statement:{}",statement);
		

		userList = sqlSessionTemplate.selectList(statement);

		return userList;
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int getCount() throws SQLException {
		int count = 0;
		
		String statement = NAMESPACE+DOT+"getCount";
		log.debug("0. parameter:없음");
		log.debug("1. statement:{}",statement);
		
		count = sqlSessionTemplate.selectOne(statement);
		log.debug("2. count:{}", count);

		return count;
	}

	@Override
	public void deleteAll() throws SQLException {
		int count = 0;
		// SQL작성만!
		// 1. Connection : X
		// 2. 자원 반납 : close() X
		String statement = NAMESPACE+DOT+"deleteAll";
		log.debug("0. parameter:없음");
		log.debug("1. statement:{}",statement);
		
		count = sqlSessionTemplate.delete(statement);
		log.debug("2. count:{}",count);

	}

	// 등록
	@Override
	public int doSave(UserVO inVO) throws SQLException {
		int flag = 0;

		log.debug("1.param:"+inVO);
		
		String statement = NAMESPACE+DOT+"doSave";
		log.debug("2. statement:{}",statement);
		
		flag = sqlSessionTemplate.insert(statement, inVO);
		log.debug("3.flag:{}", flag);

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
		
		String statement = NAMESPACE+DOT+"doSelectOne";
		
		log.debug("0. parameter:{}",inVO);
		log.debug("1. statement:{}",statement);
		
		outVO = sqlSessionTemplate.selectOne(statement, inVO);
		
		log.debug("outVO:{}",outVO);
		
		// 조회 데이터가 없는 경우
		if (null == outVO) {
			throw new NullPointerException(inVO.getUserId() + "(아이디)를 확인 하세요.");
		}

		return outVO;
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		int flag = 0;
	
		log.debug("0.param:"+inVO);
		String statement = NAMESPACE+DOT+"doUpdate";

		log.debug("1. statement:{}",statement);
		
		flag = sqlSessionTemplate.update(statement, inVO);
		log.debug("2.flag:{}", flag);
		
		return flag;
	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {
		int flag = 0;
		
		Object[] args = {inVO.getUserId()};
		String statement = NAMESPACE+DOT+"doDelete";
		
		log.debug("0. parameter:{}",args);
		log.debug("1. statement:{}",statement);
		
		flag = sqlSessionTemplate.delete(statement, inVO.getUserId());
		log.debug("2. flag:{}",flag);
		
		return flag;
	}

	@Override
	public int idCheck(UserVO inVO) throws SQLException {
		int count = 0;

		String statement = NAMESPACE+DOT+"idCheck";
		
		log.debug("0. parameter:{}",inVO);
		log.debug("1. statement:{}",statement);
		
		//selectOne : 결과값이 a single row
		count = sqlSessionTemplate.selectOne(statement, inVO);
		log.debug("3. count:{}", count);
		
		return count;
	}

	@Override
	public int idPassCheck(UserVO inVO) throws SQLException {
		int count = 0;
		
		String statement = NAMESPACE+DOT+"idPassCheck";
		
		log.debug("0. parameter:{}",inVO);
		log.debug("1. statement:{}",statement);
		
		count = sqlSessionTemplate.selectOne(statement, inVO);
		log.debug("3. count:{}", count);
		
		return count;
	}
	
	

}
