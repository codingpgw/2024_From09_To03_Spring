package com.pcwk.ehr.user.domain;

import com.pcwk.ehr.cmn.DTO;

public class UserVO extends DTO {
	//ctrl+shift+y : 소문자
	//ctrl+shift+x : 대문자
	private String userId  ;//사용자id
	private String name     ;//이름
	private String password ;//비밀번호
	private String regDt   ;//등록일
	//--------------------------------------------------------------------------
	private int login;      //로그인
	private int recommend;  //추천
	private Level grade  ;  //등급 (BASIC, SILVER, GOLD)
	private String email    ;  //이메일
	
	
	public UserVO() {

	}

	public UserVO(String userId, String name, String password, String regDt, int login, int recommend, Level grade,
			String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.regDt = regDt;
		this.login = login;
		this.recommend = recommend;
		this.grade = grade;
		this.email = email;
	}
	
	//레벨 업그레이드 작업 메서드
	public void upgradeLevel() {
		Level nextLevel = grade.nextLevel();
		if(null == nextLevel) {
			throw new IllegalArgumentException(this.grade+"은 등업 불가능합니다.");
		}else {
			this.grade = nextLevel;
		}
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

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public Level getGrade() {
		return grade;
	}

	public void setGrade(Level grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", name=" + name + ", password=" + password + ", regDt=" + regDt
				+ ", login=" + login + ", recommend=" + recommend + ", grade=" + grade + ", email=" + email
				+ ", toString()=" + super.toString() + "]";
	}

	
	
}
