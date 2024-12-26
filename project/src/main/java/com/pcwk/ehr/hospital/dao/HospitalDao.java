package com.pcwk.ehr.hospital.dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.user.domain.HospitalVO;


public interface HospitalDao {
	int doDelete(HospitalVO inVO) throws SQLException;
	
	int doUpdate(HospitalVO inVO) throws SQLException;
	
	List<HospitalVO> doRetrieve(DTO dto);
	
	void deleteAll() throws SQLException;
	
	int doSave(HospitalVO inVO) throws SQLException;
	
	HospitalVO doSelectOne(HospitalVO inVO) throws SQLException, NullPointerException;
}
