package com.pcwk.ehr.member.domain;

import com.pcwk.ehr.cmn.DTO;

public class MemberVO extends DTO {
	private String memId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String birthDt;
	private String memDiv;
	private String regDt;
	
	public MemberVO() {
		
	}

	public MemberVO(String memId, String password, String name, String email, String phone, String birthDt, String memDiv,
			String regDt) {
		super();
		this.memId = memId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birthDt = birthDt;
		this.memDiv = memDiv;
		this.regDt = regDt;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getBirthDt() {
		return birthDt;
	}

	public void setBirthDt(String birthDt) {
		this.birthDt = birthDt;
	}

	public String getMemDiv() {
		return memDiv;
	}

	public void setMemDiv(String memDiv) {
		this.memDiv = memDiv;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", birthDt=" + birthDt + ", memDiv=" + memDiv + ", regDt=" + regDt + "]";
	}
}