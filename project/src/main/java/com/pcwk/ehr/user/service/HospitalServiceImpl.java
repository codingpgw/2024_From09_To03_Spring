/**
 * Package Name : com.pcwk.ehr.user.service  <br/>
 * Class Name: UserService.java 			 <br/>
 * Description:  <br/>
 * Modification imformation : 				 <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2024-11-28						 <br/>
 *
 * ------------------------------------------<br/>
 * @author :gy
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.hospital.dao.HospitalDao;
import com.pcwk.ehr.user.domain.HospitalVO;

/**
 * @author gy
 *
 */
@Service
public class HospitalServiceImpl implements HospitalService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	HospitalDao hospitalDao;

	public HospitalServiceImpl() {

	}

	@Override
	public int doDelete(HospitalVO inVO) throws SQLException {
		return hospitalDao.doDelete(inVO);
	}

	@Override
	public int doUpdate(HospitalVO inVO) throws SQLException {
		return hospitalDao.doUpdate(inVO);
	}

	@Override
	public List<HospitalVO> doRetrieve(DTO dto) {
		return hospitalDao.doRetrieve(dto);
	}

	@Override
	public HospitalVO doSelectOne(HospitalVO inVO)
			throws SQLException, EmptyResultDataAccessException, NullPointerException {
		return hospitalDao.doSelectOne(inVO);
	}

	@Override
	public int doSave(HospitalVO inVO) throws SQLException {
		return hospitalDao.doSave(inVO);
	}

	

}
