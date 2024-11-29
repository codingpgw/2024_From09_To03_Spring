package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface StatementStrategy {
	Logger log = LogManager.getLogger(StatementStrategy.class);
	
	//전략(Strategy) : 알고리즘을 나타내는 인터페이스
	public PreparedStatement makePreparedStatement(Connection connection)
			throws SQLException;
}
