package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao extends UserDao {

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String DB_URL = "jdbc:oracle:thin:@192.168.100.245:1521:xe";
		String DB_USER = "scott";
		String DB_PASSWORD = "pcwk";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		System.out.println("1.conn :"+conn);
		
		return conn;
	}

}
