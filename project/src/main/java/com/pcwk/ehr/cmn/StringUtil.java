package com.pcwk.ehr.cmn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.*;

public class StringUtil {
	
	final Logger log = LogManager.getLogger(StringUtil.class);
	/**
	 * request에 파라메터 null처리
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static String nvl(String value, String defaultValue) {
		if(Strings.isNullOrEmpty(value)) {
			return defaultValue;
		}
	
		return value;
	}
	
//	/**
//	 * request에 파라메터 null처리
//	 * @param value
//	 * @param defaultValue
//	 * @return
//	 */
//	public static String nvl(String value, String defaultValue) {
//		if(value == null || value.trim().isEmpty()) {
//			return defaultValue;
//		}
//		return value;
//	}
}
