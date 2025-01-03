package com.pcwk.ehr.community.domain;

import com.pcwk.ehr.cmn.DTO;

public class CommunityVO extends DTO {
	private int cmnNo;
	private String memId;
	private String title;
	private String content;
	private String category;
	private String div;
	private String regDt;
	private String modDt;
	private int view;
	
	public CommunityVO() {
		super();
	}
	
	//int cmnNo, 
	public CommunityVO(String memId, String title, String content, String category, String div, String regDt,
			String modDt, int view) {
		super();
		//this.cmnNo = cmnNo;
		this.memId = memId;
		this.title = title;
		this.content = content;
		this.category = category;
		this.div = div;
		this.regDt = regDt;
		this.modDt = modDt;
		this.view = view;
	}

	public int getCmnNo() {
		return cmnNo;
	}

	public void setCmnNo(int cmnNo) {
		this.cmnNo = cmnNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	@Override
	public String toString() {
		return "CommunityVO [cmnNo=" + cmnNo + ", memId=" + memId + ", title=" + title + ", content=" + content
				+ ", category=" + category + ", div=" + div + ", regDt=" + regDt + ", modDt=" + modDt + ", view=" + view
				+ ", toString()=" + super.toString() + "]";
	}
}