package com.pcwk.ehr.user.domain;

public class QnaVO {
	private int qna_no;
	private String qna_question;
	private String qna_answer;
	private int qna_admin;
	private String qna_reg_dt;
	
	public QnaVO() {
		
	}

	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	public String getQna_question() {
		return qna_question;
	}

	public void setQna_question(String qna_question) {
		this.qna_question = qna_question;
	}

	public String getQna_answer() {
		return qna_answer;
	}

	public void setQna_answer(String qna_answer) {
		this.qna_answer = qna_answer;
	}

	public int getQna_admin() {
		return qna_admin;
	}

	public void setQna_admin(int qna_admin) {
		this.qna_admin = qna_admin;
	}

	public String getQna_reg_dt() {
		return qna_reg_dt;
	}

	public void setQna_reg_dt(String qna_reg_dt) {
		this.qna_reg_dt = qna_reg_dt;
	}

	@Override
	public String toString() {
		return "QnaVO [qna_no=" + qna_no + ", qna_question=" + qna_question + ", qna_answer=" + qna_answer
				+ ", qna_admin=" + qna_admin + ", qna_reg_dt=" + qna_reg_dt + "]";
	}	
	
}
