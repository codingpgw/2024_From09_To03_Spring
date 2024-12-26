package com.pcwk.ehr.cmn;

import java.util.HashMap;
import java.util.Map;

public class DTO {
	private int no; //글 번호
	private int totalCnt; //총 글수
	
	private int pageSize; //페이지 사이즈
	private int pageNo;   //페이지 번호
	
	private Map<String, String> optionSearch = new HashMap<String, String>(); 
	
	public DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public Map<String, String> getOptionSearch() {
		return optionSearch;
	}
	public void setOptionSearch(Map<String, String> optionSearch) {
		this.optionSearch = optionSearch;
	}
	@Override
	public String toString() {
		return "DTO [no=" + no + ", totalCnt=" + totalCnt + "]";
	}
	
	
	
}
