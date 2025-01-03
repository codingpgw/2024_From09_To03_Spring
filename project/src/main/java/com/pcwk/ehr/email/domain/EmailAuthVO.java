package com.pcwk.ehr.email.domain;

public class EmailAuthVO {
	private String memEmail;
	private String authCode;
	private String authTime;
	private int authStatus;
	
	public EmailAuthVO() {

	}

	public EmailAuthVO(String memEmail, String authCode, String authTime, int authStatus) {
		super();
		this.memEmail = memEmail;
		this.authCode = authCode;
		this.authTime = authTime;
		this.authStatus = authStatus;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthTime() {
		return authTime;
	}

	public void setAuthTime(String authTime) {
		this.authTime = authTime;
	}

	public int getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}

	@Override
	public String toString() {
		return "EmailAuth [memEmail=" + memEmail + ", authCode=" + authCode + ", authTime=" + authTime + ", authStatus="
				+ authStatus + ", toString()=" + super.toString() + "]";
	}
	
}
