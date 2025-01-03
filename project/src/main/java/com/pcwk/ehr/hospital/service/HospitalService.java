package com.pcwk.ehr.hospital.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.hospital.domain.HospitalVO;

public interface HospitalService {
	
	int doSave(HospitalVO inVO) throws SQLException;
	
	HospitalVO doSelectOne(HospitalVO inVO) throws SQLException, NullPointerException;
	
	int doUpdate(HospitalVO inVO) throws SQLException;
	
	int doDelete(HospitalVO inVO) throws SQLException;
	
	List<HospitalVO> doRetrieve(DTO dto);
}
