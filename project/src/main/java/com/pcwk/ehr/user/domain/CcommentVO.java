package com.pcwk.ehr.user.domain;

import com.pcwk.ehr.cmn.DTO;

public class CcommentVO extends DTO{
	private int cmt_code;
	private String mem_id;
	private int cmn_no;
	private String cmt_comment;
	private String cmt_comment_dt;
	
	public CcommentVO() {
		
	}

	public CcommentVO(int cmt_code, String mem_id, int cmn_no, String cmt_comment, String cmt_comment_dt) {
		super();
		this.cmt_code = cmt_code;
		this.mem_id = mem_id;
		this.cmn_no = cmn_no;
		this.cmt_comment = cmt_comment;
		this.cmt_comment_dt = cmt_comment_dt;
	}

	public int getCmt_code() {
		return cmt_code;
	}

	public void setCmt_code(int cmt_code) {
		this.cmt_code = cmt_code;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getCmn_no() {
		return cmn_no;
	}

	public void setCmn_no(int cmn_no) {
		this.cmn_no = cmn_no;
	}

	public String getCmt_comment() {
		return cmt_comment;
	}

	public void setCmt_comment(String cmt_comment) {
		this.cmt_comment = cmt_comment;
	}

	public String getCmt_comment_dt() {
		return cmt_comment_dt;
	}

	public void setCmt_comment_dt(String cmt_comment_dt) {
		this.cmt_comment_dt = cmt_comment_dt;
	}

	@Override
	public String toString() {
		return "CcommentVO [cmt_code=" + cmt_code + ", mem_id=" + mem_id + ", cmn_no=" + cmn_no + ", cmt_comment="
				+ cmt_comment + ", cmt_comment_dt=" + cmt_comment_dt + "]";
	}
	

}
