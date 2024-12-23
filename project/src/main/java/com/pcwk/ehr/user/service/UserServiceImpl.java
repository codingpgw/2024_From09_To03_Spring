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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

/**
 * @author gy
 *
 */
@Service
public class UserServiceImpl implements UserService {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private MailSender mailSender;

	public UserServiceImpl() {

	}

	/**
	 * 회원 삭제
	 * 
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	@Override
	public int doDelete(UserVO inVO) throws SQLException {
		return userDao.doDelete(inVO);
	}

	/**
	 * 회원 수정
	 * 
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		return userDao.doUpdate(inVO);
	}

	/**
	 * 회원 목록 페이징 처리
	 * 
	 * @param dto
	 * @return List<UserVO>
	 */
	@Override
	public List<UserVO> doRetrieve(DTO dto) {
		return userDao.doRetrieve(dto);
	}

	/**
	 * 회원 상세 조회
	 * 
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 * @throws NullPointerException
	 */
	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException, NullPointerException {
		return userDao.doSelectOne(inVO);
	}

	/**
	 * 회원등록(가입)
	 * 
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	@Override
	public int doSave(UserVO inVO) throws SQLException {
		return userDao.doSave(inVO);
	}

	/**
	 * 등업 email
	 * 
	 * @param user
	 */
	private void sendUpgradeEmail(UserVO user) {
		// 보내는 사람
		// 받는 사람
		// 제목, 내용
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("1026rjsdnd@naver.com");
			message.setTo(user.getMem_email());
			message.setSubject("등업 안내");
			
			mailSender.send(message);
		} catch (Exception e) {
			log.debug("┌───────────────────────────────────┐");
			log.debug("│ **Exception**                     │" + e.getMessage());
			log.debug("└───────────────────────────────────┘");
		}

		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **mail send TO**                  │" + user.getMem_email());
		log.debug("└───────────────────────────────────┘");
	}

	/**
	 * 회원 등업
	 * 
	 * @throws SQLException
	 */

}
