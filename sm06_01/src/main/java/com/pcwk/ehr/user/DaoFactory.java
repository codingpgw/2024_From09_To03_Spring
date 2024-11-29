package com.pcwk.ehr.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * xxxDao 생성 : ConnectionMaker
 * @author gy
 *
 */

@Configuration //애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보라는 표시
public class DaoFactory {
	
	/**
	 * UserDao 생성
	 * @return
	 */
	@Bean//오브젝트 생성을 담당하는 IoC용 메서드라는 표시
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}
	
//	public BoardDao boardDao() {
//		return new BoardDao(connectionMaker());
//	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}
	
}
