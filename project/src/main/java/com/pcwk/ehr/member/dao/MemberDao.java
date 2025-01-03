package com.pcwk.ehr.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.member.domain.MemberVO;

public interface MemberDao {
	
	/* id 존재 확인 */
	int idCheck(MemberVO inVO) throws SQLException;
	
	/* pw 존재 확인 */
	int idPassCheck(MemberVO inVO) throws SQLException;

	int saveAll();

	List<MemberVO> doRetrieve(DTO dto);

	List<MemberVO> getAll() throws SQLException;

	void deleteAll() throws SQLException;

	int getCount() throws SQLException;

	int doSave(MemberVO inVO) throws SQLException;

	MemberVO doSelectOne(MemberVO inVO) throws SQLException, NullPointerException;

	int doUpdate(MemberVO inVO) throws SQLException;

	int doDelete(MemberVO inVO);
}