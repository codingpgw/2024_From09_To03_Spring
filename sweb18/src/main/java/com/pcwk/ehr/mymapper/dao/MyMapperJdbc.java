package com.pcwk.ehr.mymapper.dao;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.mymapper.domain.MyMapperVO;

@Repository
public class MyMapperJdbc implements MyMapperDao {
    final Logger log = LogManager.getLogger(getClass());
    
    
	final String NAMESPACE = "com.pcwk.ehr.mymapper";
	final String DOT = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;// DB연결, SQL수행, 자원반납

	public MyMapperJdbc() {
		super();

	}

	@Override
	public MyMapperVO doHello(MyMapperVO inVO) throws SQLException {
		MyMapperVO outVO = null;
		
		String statement = NAMESPACE+DOT+"doHello";//com.pcwk.ehr.mymapper.doHello
		log.debug("1. param:{}",inVO);
		log.debug("2. statement:{}",statement);
		
		outVO=sqlSessionTemplate.selectOne(statement, inVO);
		log.debug("3. outVO:{}",outVO);
		return outVO;
	}

}