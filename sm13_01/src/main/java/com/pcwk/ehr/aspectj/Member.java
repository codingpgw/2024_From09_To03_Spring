package com.pcwk.ehr.aspectj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Member {
	final Logger log = LogManager.getLogger(Member.class);
	
	/**
	 * 단건 저장
	 * @return
	 */
	int doSave();
	
	/**
	 * 수정
	 * @return
	 */
	int doUpdate();
	
	/**
	 * 단건 등록
	 * @param num
	 */
	void doInsert(int num);
	
	/**
	 * 삭제
	 * @return
	 */
	int delete();
	
	/**
	 * 목록 조회
	 * @param num
	 * @return
	 */
	int doRetrieve(int num);
}
