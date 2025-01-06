package com.pcwk.ehr.qna.domain;

import com.pcwk.ehr.cmn.DTO;

public class QnaVO extends DTO {
	private int qnaNo;
	private String question;
	private String answer;
	private String admin;
	private String regDt;
	
	public QnaVO() {
		super();
	}
	
	public QnaVO(int qnaNo, String question, String answer, String admin, String regDt) {
		super();
		this.qnaNo = qnaNo;
		this.question = question;
		this.answer = answer;
		this.admin = admin;
		this.regDt = regDt;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "QnaVO [qnaNo=" + qnaNo + ", question=" + question + ", answer=" + answer + ", admin=" + admin
				+ ", regDt=" + regDt + "]";
	}
}