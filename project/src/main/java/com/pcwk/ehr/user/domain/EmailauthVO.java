package com.pcwk.ehr.user.domain;

public class EmailauthVO {
	private String mem_email;
	private String auth_code;
	private String auth_time;
	private int auth_status;
	
	public EmailauthVO() {
		
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(String auth_time) {
		this.auth_time = auth_time;
	}

	public int getAuth_status() {
		return auth_status;
	}

	public void setAuth_status(int auth_status) {
		this.auth_status = auth_status;
	}

	@Override
	public String toString() {
		return "EmailauthVO [mem_email=" + mem_email + ", auth_code=" + auth_code + ", auth_time=" + auth_time
				+ ", auth_status=" + auth_status + "]";
	}
	
}
