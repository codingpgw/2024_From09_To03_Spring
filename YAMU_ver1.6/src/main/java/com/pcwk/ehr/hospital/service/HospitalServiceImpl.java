package com.pcwk.ehr.hospital.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.hospital.dao.HospitalDao;
import com.pcwk.ehr.hospital.domain.HospitalVO;

@Service
public class HospitalServiceImpl implements HospitalService {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	HospitalDao hospitalDao;
	
	public HospitalServiceImpl() {

	}

	@Override
	public int doSave(HospitalVO inVO) throws SQLException {
		return hospitalDao.doSave(inVO);
	}

	@Override
	public HospitalVO doSelectOne(HospitalVO inVO) throws SQLException, NullPointerException {
		return hospitalDao.doSelectOne(inVO);
	}

	@Override
	public int doUpdate(HospitalVO inVO) throws SQLException {
		return hospitalDao.doUpdate(inVO);
	}

	@Override
	public int doDelete(HospitalVO inVO) throws SQLException {
		return hospitalDao.doDelete(inVO);
	}

	@Override
	public List<HospitalVO> doRetrieve(DTO dto) {
		return hospitalDao.doRetrieve(dto);
	}
	
}
