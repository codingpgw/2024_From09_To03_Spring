package com.pcwk.ehr.user;

public class UserVO {
	//ctrl+shift+y : 소문자
	//ctrl+shift+x : 대문자
	private String userId  ; //사용자ID
	private String name 	;//이름
	private String password ;//비밀번호
	private String regDt   ; //등록일
	
	public UserVO() {
	
	}
	
	public UserVO(String userId, String name, String password, String regDt) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.regDt = regDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", name=" + name + ", password=" + password + ", regDt=" + regDt + "]";
	}
	
	
}
