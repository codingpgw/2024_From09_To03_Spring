package com.pcwk.ehr.file;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileuploadUtilTest {
	
	final static Logger log = LogManager.getLogger(FileuploadUtilTest.class);
	
	public static String getCurrentDate(String pattern) {
		
		if(null == pattern || "".equals(pattern)) {
			pattern = "yyyy/MM/dd";
		}
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}
	
	public static String getPK(String pattern) {
		if(null == pattern || "".equals(pattern)) {
			pattern = "yyyyMMdd";
		}
		
		return getCurrentDate(pattern)+"_"+getUUID();
	}
	
	public static void main(String[] args) {
		final String IMG_PATH = "C:\\Users\\gy\\OneDrive\\바탕 화면\\JAP_20240909\\04_SPRING\\WORKSPACE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\sweb18\\resources\\upload";
		//none image 파일
		final String FILE_PATH = "C:\\upload";
		
		String yyyyMMPath = "";//  \\2025\01
		String fileFullPath = ""; // FILE_PATH+yyyyMMPath
		String imageFullPath = ""; // IMG_PATH+yyyyMMPath
		
		String yyyy = getCurrentDate("yyyy");
		String mm = getCurrentDate("MM");
		log.debug("yyyy:{}",yyyy);
		log.debug("mm:{}",mm);
		
		//디렉토리 동적 생성
		yyyyMMPath = File.separator+yyyy+File.separator+mm;
		log.debug("yyyyMMPath:{}",yyyyMMPath);
		log.debug("FILE_PATH+yyyyMMPath:{}",FILE_PATH+yyyyMMPath);
		
		File normalFile = new File(FILE_PATH+yyyyMMPath);
		
		if(normalFile.isDirectory() == false) {
			boolean isMakeDirs = normalFile.mkdirs();
			log.debug("isMakeDirs:{}",isMakeDirs);
		}
		
		log.debug("IMG_PATH+yyyyMMPath:{}",IMG_PATH+yyyyMMPath);
		File imageFile = new File(IMG_PATH+yyyyMMPath);
		
		if(imageFile.isDirectory() == false) {
			boolean isMakeDirs = imageFile.mkdirs();
			log.debug("isMakeDirs:{}",isMakeDirs);
		}
		
		//원본 파일명을 유일하게
		//yyyymmdd HH24miss()+UUID(14+_+36)+.확장자(4) -> 60
		
		String dateStr = getCurrentDate("yyyyMMddHH24mmss");
		log.debug("dateStr:{}",dateStr);
		
		log.debug("uuid:{}",getUUID());
		String uuidStr = "8e02d9bd-6350-4e47-8b75-95436799b134";
		log.debug("uuidStr.length:{}",uuidStr.length());
		
		String saveFileName = getPK("yyyyMMddHH24mmss");
		log.debug("saveFileName:{}",saveFileName);
		
		String fileName = "board_insert.sql";
		String ext = "";
		if(fileName.lastIndexOf(".") > -1) {
			int lastIndex = fileName.lastIndexOf(".");
			log.debug("lastIndex:{}",lastIndex);
			
			ext = fileName.substring(lastIndex+1);
			log.debug("ext:{}",ext);
		}
		
		String newExt = getExt(fileName);
		log.debug("newExt:{}",newExt);
	}
	
	public static String getExt(String fileName) {
		String ext = "";
		if(fileName.lastIndexOf(".") > -1) {
			int lastIndex = fileName.lastIndexOf(".");
			ext = fileName.substring(lastIndex+1);
		}
		
		return ext;
	}

}
