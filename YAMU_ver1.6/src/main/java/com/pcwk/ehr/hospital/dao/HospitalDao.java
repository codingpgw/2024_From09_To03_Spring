package com.pcwk.ehr.hospital.dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.hospital.domain.HospitalVO;

public interface HospitalDao {
	
	List<HospitalVO> getAll() throws SQLException;
	
	int doSave(HospitalVO inVO) throws SQLException;
	
	int doUpdate(HospitalVO inVO) throws SQLException;
	
	int doDelete(HospitalVO inVO) throws SQLException;
	
	List<HospitalVO> doRetrieve(DTO dto);
	
	int getCount() throws SQLException;

	HospitalVO doSelectOne(HospitalVO inVO) throws SQLException, NullPointerException;
}
