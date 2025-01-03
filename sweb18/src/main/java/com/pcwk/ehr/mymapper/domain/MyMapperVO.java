package com.pcwk.ehr.mymapper.domain;

import com.pcwk.ehr.cmn.DTO;

public class MyMapperVO extends DTO {
	private String userId;
	private String password;
	
	public MyMapperVO() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "MyMapperVO [userId=" + userId + ", password=" + password + "]";
	}
	
}
