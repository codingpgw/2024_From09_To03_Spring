package com.pcwk.ehr.mymapper.dao;

import java.sql.SQLException;

import com.pcwk.ehr.mymapper.domain.MyMapperVO;

public interface MyMapperDao {
	MyMapperVO doHello(MyMapperVO inVO) throws SQLException;
}
