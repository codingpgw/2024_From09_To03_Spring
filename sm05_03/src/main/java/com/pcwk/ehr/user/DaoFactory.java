package com.pcwk.ehr.user;
/**
 * xxxDao 생성 : ConnectionMaker
 * @author gy
 *
 */
public class DaoFactory {
	
	/**
	 * UserDao 생성
	 * @return
	 */
	
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}
	
//	public BoardDao boardDao() {
//		return new BoardDao(connectionMaker());
//	}
	
	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}
	
}
