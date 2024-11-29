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

import com.pcwk.ehr.user.Level;
import com.pcwk.ehr.user.UserDao;
import com.pcwk.ehr.user.UserVO;
/**
 * @author gy
 *
 */
public class UserService {
	
	UserDao userDao;
	
	
	public UserService() {

	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//전체 회원 등업
	public void upgradeLevels() throws SQLException{
		//1. 전체 회원 조회
		//2. BASIC, SILVER, GOLD 등업 조건
		//		1. BASIC : 기입후 50-회 이상 로그인
		//		2. SILVER: SILVER이면서 30회 이상 추천
		//		3. GOLD  : 최고 등급
		
		//1
		List<UserVO> users = userDao.getAll();
		
		for(UserVO user : users) {
			Boolean changed  = null; //등급 변환가능 확인
			
			//BASIC -> SILVER
			if(Level.BASIC == user.getGrade() && user.getLogin() >= 50) {
				user.setGrade(Level.SILVER);
				
				changed = true;
			}
			//SILVER -> GOLD
			else if(Level.SILVER == user.getGrade() && user.getRecommend() >= 30) {
				user.setGrade(Level.GOLD);
				
				changed = true;
			}
			//GOLD : 레벨 변경 없음.
			else if(Level.GOLD == user.getGrade()) {
				changed = false;
			}
			else {
				changed = false;
			}
			
			
			//3
			if(changed == true) {
				userDao.doUpdate(user);
			}
			
		}//--for end
		
		
	}
	
}

