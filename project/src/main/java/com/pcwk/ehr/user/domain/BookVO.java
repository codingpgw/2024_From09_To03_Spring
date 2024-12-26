package com.pcwk.ehr.user.domain;

public class BookVO {
	private String book_code; 
	private String mem_id;
	private String hospital_id;
	private String book_name;
	private String book_dt;
	
	public BookVO() {
		
	}

	public String getBook_code() {
		return book_code;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_dt() {
		return book_dt;
	}

	public void setBook_dt(String book_dt) {
		this.book_dt = book_dt;
	}

	@Override
	public String toString() {
		return "BookVO [book_code=" + book_code + ", mem_id=" + mem_id + ", hospital_id=" + hospital_id + ", book_name="
				+ book_name + ", book_dt=" + book_dt + "]";
	}
	
}
