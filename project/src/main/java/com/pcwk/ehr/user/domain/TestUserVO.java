package com.pcwk.ehr.user.domain;
import com.pcwk.ehr.cmn.DTO;
public class TestUserVO extends DTO{
	private String mem_id;
	private String mem_password;
	private String mem_name;
	private String mem_email;
	private String mem_phone;
	private String mem_jumin;
	private int mem_div;
	private String mem_regdt;
	
	public TestUserVO() {
		
	}

	public TestUserVO(String mem_id, String mem_password, String mem_name, String mem_email, String mem_phone,
			String mem_jumin, int mem_div, String mem_regdt) {
		super();
		this.mem_id = mem_id;
		this.mem_password = mem_password;
		this.mem_name = mem_name;
		this.mem_email = mem_email;
		this.mem_phone = mem_phone;
		this.mem_jumin = mem_jumin;
		this.mem_div = mem_div;
		this.mem_regdt = mem_regdt;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_password() {
		return mem_password;
	}

	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_jumin() {
		return mem_jumin;
	}

	public void setMem_jumin(String mem_jumin) {
		this.mem_jumin = mem_jumin;
	}

	public int getMem_div() {
		return mem_div;
	}

	public void setMem_div(int mem_div) {
		this.mem_div = mem_div;
	}

	public String getMem_regdt() {
		return mem_regdt;
	}

	public void setMem_regdt(String mem_regdt) {
		this.mem_regdt = mem_regdt;
	}

	@Override
	public String toString() {
		return "TestUserVO [mem_id=" + mem_id + ", mem_password=" + mem_password + ", mem_name=" + mem_name
				+ ", mem_email=" + mem_email + ", mem_phone=" + mem_phone + ", mem_jumin=" + mem_jumin + ", mem_div="
				+ mem_div + ", mem_regdt=" + mem_regdt + "]";
	}
	
}
