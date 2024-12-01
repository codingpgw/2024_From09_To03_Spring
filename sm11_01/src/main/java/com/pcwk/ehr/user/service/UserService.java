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

import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.user.DTO;
import com.pcwk.ehr.user.Level;
import com.pcwk.ehr.user.UserDao;
import com.pcwk.ehr.user.UserVO;
/**
 * @author gy
 *
 */
public class UserService {
	//50(BASIC -> SILVER로 가기 위한 최소 로그인 횟수
	public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
	//30(SILVER -> GOLD로 가기 위한 최소 추천 횟수
	public static final int MIN_RECOMMEND_COUNT_FOR_GOLD = 30;
	
	UserDao userDao;
	
	public UserService() {

	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 회원 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int doDelete(UserVO inVO) throws SQLException {
		return userDao.doDelete(inVO);
	}
	
	/**
	 * 회원 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int doUpdate(UserVO inVO) throws SQLException {
		return userDao.doUpdate(inVO);
	}
	
	/**
	 * 회원 목록 페이징 처리
	 * @param dto
	 * @return List<UserVO>
	 */
	public List<UserVO> doRetrieve(DTO dto) {
		return userDao.doRetrieve(dto);
	}
	
	/**
	 * 회원 상세 조회
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 * @throws NullPointerException
	 */
	public UserVO doSelectOne(UserVO inVO) throws SQLException,EmptyResultDataAccessException ,NullPointerException {
		return userDao.doSelectOne(inVO);
	}
	
	/**
	 * 회원등록(가입)
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int doSave(UserVO inVO) throws SQLException {
		if(inVO.getGrade() == null) inVO.setGrade(Level.BASIC);
		
		return userDao.doSave(inVO);
	}
	
	//등업 가능 확인 메서드
	private boolean canUpgradeLevel(UserVO user) {
		//현재 등급, 등업 조건
		Level currentLevel = user.getGrade();
		
		switch (currentLevel) {
		case BASIC:return(user.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER);
		case SILVER:return(user.getRecommend() >= MIN_RECOMMEND_COUNT_FOR_GOLD);
		case GOLD: return false;
		
		default: throw new IllegalArgumentException("Unknown Level : "+currentLevel);
		}
	}
	
	//등업 실행
	private void upgradeLevel(UserVO user) throws SQLException {
		if(user.getGrade() == Level.BASIC) user.setGrade(Level.SILVER);
		else if(user.getGrade() == Level.SILVER) user.setGrade(Level.GOLD);
		
		userDao.doUpdate(user);
	}
	
	/**
	 * 회원 등업
	 * @throws SQLException
	 */
	public void upgradeLevels() throws SQLException{
		//1. 전체 회원 조회
		//2. BASIC, SILVER, GOLD 등업 조건
		//		1. BASIC : 기입후 50-회 이상 로그인
		//		2. SILVER: SILVER이면서 30회 이상 추천
		//		3. GOLD  : 최고 등급
		
		//1
		List<UserVO> users = userDao.getAll();
		
		for(UserVO user : users) {
			if(true == canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}//--for end
		
		
	}
	
}

