package com.pcwk.ehr.user.service;

import java.sql.SQLException;

import com.pcwk.ehr.user.UserVO;

public class TestUserService extends UserServiceImpl {
	
	private String userId;

	public TestUserService(String userId) {
		super();
		this.userId = userId;
	}
	
	protected void upgradeLevel(UserVO user) throws SQLException {
		//4번째 사용자가 들어오면 예외 발생
		if(userId.equals(user.getUserId())) {
			throw new TestUserServiceException(userId+"예외가 발생했습니다.");
		}
		
		super.upgradeLevel(user);
	}
	
	
}
