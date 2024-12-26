package com.pcwk.ehr.user.domain;

import com.pcwk.ehr.cmn.DTO;

public class CommunityVO extends DTO {
	private int cmnNo;
	private String memId;
	private String cmnTitle;
	private String cmnCategory;
	private String cmnContent;
	private String cmnDiv;
	private String cmnRegDt;
	private String cmnModDt;
	private int cmnView;
	
	public CommunityVO() {
		
	}

	public CommunityVO(int cmnNo, String memId, String cmnTitle, String cmnCategory, String cmnContent, String cmnDiv,
			String cmnRegDt, String cmnModDt, int cmnView) {
		super();
		this.cmnNo = cmnNo;
		this.memId = memId;
		this.cmnTitle = cmnTitle;
		this.cmnCategory = cmnCategory;
		this.cmnContent = cmnContent;
		this.cmnDiv = cmnDiv;
		this.cmnRegDt = cmnRegDt;
		this.cmnModDt = cmnModDt;
		this.cmnView = cmnView;
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

	public String getCmnTitle() {
		return cmnTitle;
	}

	public void setCmnTitle(String cmnTitle) {
		this.cmnTitle = cmnTitle;
	}

	public String getCmnContent() {
		return cmnContent;
	}

	public void setCmnContent(String cmnContent) {
		this.cmnContent = cmnContent;
	}

	public String getCmnDiv() {
		return cmnDiv;
	}

	public void setCmnDiv(String cmnDiv) {
		this.cmnDiv = cmnDiv;
	}

	public String getCmnRegDt() {
		return cmnRegDt;
	}

	public void setCmnRegDt(String cmnRegDt) {
		this.cmnRegDt = cmnRegDt;
	}

	public String getCmnModDt() {
		return cmnModDt;
	}

	public void setCmnModDt(String cmnModDt) {
		this.cmnModDt = cmnModDt;
	}

	public int getCmnView() {
		return cmnView;
	}

	public void setCmnView(int cmnView) {
		this.cmnView = cmnView;
	}
	
	
	public String getCmnCategory() {
		return cmnCategory;
	}

	public void setCmnCategory(String cmnCategory) {
		this.cmnCategory = cmnCategory;
	}

	@Override
	public String toString() {
		return "CommunityVO [cmnNo=" + cmnNo + ", memId=" + memId + ", cmnTitle=" + cmnTitle + ", cmnCategory="
				+ cmnCategory + ", cmnContent=" + cmnContent + ", cmnDiv=" + cmnDiv + ", cmnRegDt=" + cmnRegDt
				+ ", cmnModDt=" + cmnModDt + ", cmnView=" + cmnView + ", toString()=" + super.toString() + "]";
	}

}
