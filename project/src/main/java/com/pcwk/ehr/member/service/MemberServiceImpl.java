package com.pcwk.ehr.member.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.member.dao.MemberDao;
import com.pcwk.ehr.member.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MemberDao memberDao;
	
	public MemberServiceImpl() {
		
	}
	
	/**
	 * 회원 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	@Override
	public int doDelete(MemberVO inVO) throws SQLException {
		return memberDao.doDelete(inVO);
	}
	
	/**
	 * 회원 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	@Override
	public int doUpdate(MemberVO inVO) throws SQLException {
		return memberDao.doUpdate(inVO);
	}
	
	/**
	 * 회원 목록 페이징 처리
	 * @param dto
	 * @return List<MemberVO>
	 */
	@Override
	public List<MemberVO> doRetrieve(DTO dto) {
		return memberDao.doRetrieve(dto);
	}
	
	/**
	 * 회원 상세 조회
	 * @param inVO
	 * @return MemberVO
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 * @throws NullPointerException
	 */
	@Override
	public MemberVO doSelectOne(MemberVO inVO) throws SQLException, EmptyResultDataAccessException, NullPointerException {
		return memberDao.doSelectOne(inVO);
	}

	@Override
	public int doSave(MemberVO inVO) throws SQLException {
		return memberDao.doSave(inVO);
	}
	
}
