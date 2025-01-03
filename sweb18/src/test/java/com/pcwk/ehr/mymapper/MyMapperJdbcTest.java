package com.pcwk.ehr.mymapper;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.mymapper.dao.MyMapperDao;
import com.pcwk.ehr.mymapper.domain.MyMapperVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"	
})
class MyMapperJdbcTest {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	ApplicationContext context;
	
	@Autowired
	MyMapperDao dao;
	
	MyMapperVO  myMapperVO;
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		myMapperVO = new MyMapperVO();
					
	}

	@Test
	public void doHello() throws SQLException {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doHello()                             │");
		log.debug("└───────────────────────────────────────┘");
		
		myMapperVO.setUserId("이상무");
		myMapperVO.setPassword("4321");
		
		MyMapperVO outVO = dao.doHello(myMapperVO);
		
		log.debug("│ outVO                             │"+outVO);
		assertNotNull(outVO);
	}
	
	@Test
	void beans() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ beans()                               │");
		log.debug("└───────────────────────────────────────┘");	
		
		log.debug("context:"+context);
		log.debug("dao:"+dao);
		assertNotNull(context);
		assertNotNull(dao);		
	}

}
