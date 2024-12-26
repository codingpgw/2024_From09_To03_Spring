package com.pcwk.ehr.user.domain;

import com.pcwk.ehr.cmn.DTO;

public class HospitalVO extends DTO{
	private String hospital_id;
	private String hospital_addr;
	private String hospital_div;
	private String hospital_etc;
	private String hospital_name;
	private String hospital_mapimg;
	private String hospital_tel;
	private int    hospital_lon;
	private int    hospital_lat;
	private String hospital_time;
	
	public HospitalVO() {
		
	}

	public HospitalVO(String hospital_id, String hospital_addr, String hospital_div, String hospital_etc,
			String hospital_name, String hospital_mapimg, String hospital_tel, int hospital_lon, int hospital_lat,
			String hospital_time) {
		super();
		this.hospital_id = hospital_id;
		this.hospital_addr = hospital_addr;
		this.hospital_div = hospital_div;
		this.hospital_etc = hospital_etc;
		this.hospital_name = hospital_name;
		this.hospital_mapimg = hospital_mapimg;
		this.hospital_tel = hospital_tel;
		this.hospital_lon = hospital_lon;
		this.hospital_lat = hospital_lat;
		this.hospital_time = hospital_time;
	}

	public String getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getHospital_addr() {
		return hospital_addr;
	}

	public void setHospital_addr(String hospital_addr) {
		this.hospital_addr = hospital_addr;
	}

	public String getHospital_div() {
		return hospital_div;
	}

	public void setHospital_div(String hospital_div) {
		this.hospital_div = hospital_div;
	}

	public String getHospital_etc() {
		return hospital_etc;
	}

	public void setHospital_etc(String hospital_etc) {
		this.hospital_etc = hospital_etc;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getHospital_mapimg() {
		return hospital_mapimg;
	}

	public void setHospital_mapimg(String hospital_mapimg) {
		this.hospital_mapimg = hospital_mapimg;
	}

	public String getHospital_tel() {
		return hospital_tel;
	}

	public void setHospital_tel(String hospital_tel) {
		this.hospital_tel = hospital_tel;
	}

	public int getHospital_lon() {
		return hospital_lon;
	}

	public void setHospital_lon(int hospital_lon) {
		this.hospital_lon = hospital_lon;
	}

	public int getHospital_lat() {
		return hospital_lat;
	}

	public void setHospital_lat(int hospital_lat) {
		this.hospital_lat = hospital_lat;
	}

	public String getHospital_time() {
		return hospital_time;
	}

	public void setHospital_time(String hospital_time) {
		this.hospital_time = hospital_time;
	}

	@Override
	public String toString() {
		return "HospitalVO [hospital_id=" + hospital_id + ", hospital_addr=" + hospital_addr + ", hospital_divNam="
				+ hospital_div + ", hospital_etc=" + hospital_etc + ", hospital_name=" + hospital_name
				+ ", hospital_mapimg=" + hospital_mapimg + ", hospital_tel1=" + hospital_tel + ", hospital_lon="
				+ hospital_lon + ", hospital_lat=" + hospital_lat + ", hospital_time=" + hospital_time + "]";
	}
	
	
	
	
}