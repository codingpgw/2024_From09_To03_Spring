package com.pcwk.ehr.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.file.domain.FileVO;

@Controller
@RequestMapping("file")
public class FileController {
	final Logger log = LogManager.getLogger(getClass());
	
	//image 파일
	final String IMG_PATH = "C:\\Users\\gy\\OneDrive\\바탕 화면\\JAP_20240909\\04_SPRING\\WORKSPACE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\sweb18\\resources\\upload";
	//none image 파일
	final String FILE_PATH = "C:\\upload";
	
	private String yyyyMMPath = "";//  \\2025\01
	private String fileFullPath = ""; // FILE_PATH+yyyyMMPath
	private String imageFullPath = ""; // IMG_PATH+yyyyMMPath
	
	public FileController() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **FileController**                │");
		log.debug("└───────────────────────────────────┘");
	
		String yyyy = StringUtil.getCurrentDate("yyyy");
		String mm = StringUtil.getCurrentDate("MM");
		log.debug("yyyy:{}",yyyy);
		log.debug("mm:{}",mm);
		
		//디렉토리 동적 생성
		yyyyMMPath = File.separator+yyyy+File.separator+mm;
		log.debug("yyyyMMPath:{}",yyyyMMPath);
		log.debug("FILE_PATH+yyyyMMPath:{}",FILE_PATH+yyyyMMPath);
		
		fileFullPath = FILE_PATH+yyyyMMPath;
		File normalFile = new File(fileFullPath);
		
		if(normalFile.isDirectory() == false) {
			boolean isMakeDirs = normalFile.mkdirs();
			log.debug("isMakeDirs:{}",isMakeDirs);
		}
		
		log.debug("IMG_PATH+yyyyMMPath:{}",IMG_PATH+yyyyMMPath);
		imageFullPath = IMG_PATH+yyyyMMPath;
		File imageFile = new File(imageFullPath);
		
		if(imageFile.isDirectory() == false) {
			boolean isMakeDirs = imageFile.mkdirs();
			log.debug("isMakeDirs:{}",isMakeDirs);
		}
		
	}
	
	@GetMapping("/asyncFileUploadView.do")
	public ModelAndView asyncFileUploadView(ModelAndView modelAndView) {
		log.debug("┌────────────────────────────────┐");
		log.debug("│ **asyncFileUploadView**        │");
		log.debug("└────────────────────────────────┘");
		
		modelAndView.addObject("hello", "새해에는 모두 건강하시고, 원하는 것 이루는 한 해");
		
		modelAndView.setViewName("file/asyncFileUploadView");
		return modelAndView;
	}
	
	@GetMapping("/asyncMultiUploadView.do")
	public ModelAndView asyncMultiUploadView(ModelAndView modelAndView) {
		log.debug("┌────────────────────────────────┐");
		log.debug("│ **asyncMultiUploadView**       │");
		log.debug("└────────────────────────────────┘");
		
		modelAndView.addObject("hello", "새해에는 모두 건강하시고, 원하는 것 이루는 한 해");
		
		modelAndView.setViewName("file/asyncMultiUpload");
		return modelAndView;
	}
	
	@PostMapping(value="/asyncMultiUpload.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<FileVO> asyncMultiUpload(@RequestParam("uploadfile") List<MultipartFile> uploadfile){
		List<FileVO> list = new ArrayList<FileVO>();
		log.debug("┌────────────────────────────────┐");
		log.debug("│ **asyncMultiUpload**        	│");
		log.debug("└────────────────────────────────┘");
		
		log.debug("uploadfile:{}",uploadfile);
		if(null != uploadfile && uploadfile.size() > 0) {
			for(MultipartFile file:uploadfile) {
				FileVO vo = new FileVO();
				
				if(file.isEmpty() == false) {
					String orgFileName = file.getOriginalFilename();
					long fileSize      = file.getSize();
					String saveFileName = StringUtil.getPK("yyyyMMddHHmmss");
					String ext	  		= StringUtil.getExt(orgFileName);
					
					try {
						vo.setOrgFileName(orgFileName);
						vo.setFileSize(fileSize);
						vo.setSavePath(fileFullPath);
						vo.setSaveFileName(saveFileName+"."+ext);
						vo.setExtension(ext);
						
						file.transferTo(new File(fileFullPath+File.separator+vo.getSaveFileName()));
						
					} catch (IllegalStateException e) {
						log.debug("IllegalStateException:"+e.getMessage());
					} catch (IOException e) {
						log.debug("IOException:"+e.getMessage());
					}
					
					list.add(vo);
				}
			}
		}
		
		for(FileVO vo : list) {
			log.debug("FileVO:{}",vo);
		}
		
		
		return list;
	}
	
	@PostMapping(value="/asyncUpload.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public FileVO asyncUpload(@RequestParam("file") MultipartFile file) {
		log.debug("┌────────────────────────────────┐");
		log.debug("│ **asyncUpload**        	    │");
		log.debug("└────────────────────────────────┘");
		
		FileVO vo = new FileVO();
		
		if(file.isEmpty() == false) {
			String orgFileName = file.getOriginalFilename();
			long fileSize      = file.getSize();
			String saveFileName = StringUtil.getPK("yyyyMMddHHmmss");
			String ext	  		= StringUtil.getExt(orgFileName);
			
			try {
				vo.setOrgFileName(orgFileName);
				vo.setFileSize(fileSize);
				vo.setSavePath(fileFullPath);
				vo.setSaveFileName(saveFileName+"."+ext);
				vo.setExtension(ext);
				
				file.transferTo(new File(fileFullPath+File.separator+vo.getSaveFileName()));
				
			} catch (IllegalStateException e) {
				log.debug("IllegalStateException:"+e.getMessage());
			} catch (IOException e) {
				log.debug("IOException:"+e.getMessage());
			}

		}else {
			log.debug("파일 업로드 실패 : file empty");
		}
		
		return vo;
	}
	
	// /file/fileUpload.jsp
	@GetMapping("/uploadView.do")
	public ModelAndView uploadView(ModelAndView modelAndView) {
		log.debug("┌────────────────────────────────┐");
		log.debug("│ **uploadView**                	│");
		log.debug("└────────────────────────────────┘");
		
		modelAndView.addObject("hello", "새해에는 모두 건강하시고, 취업합시다.");
		
		modelAndView.setViewName("file/fileUpload");
		return modelAndView;
	}
	
	@PostMapping("/fileUpload.do")
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file
			, ModelAndView modelAndView) {
		FileVO vo = new FileVO();
		
		
		if(file.isEmpty() == false) {
			String orgFileName = file.getOriginalFilename();
			long fileSize      = file.getSize();
			String saveFileName = StringUtil.getPK("yyyyMMddHHmmss");
			String ext	  		= StringUtil.getExt(orgFileName);
			
			try {
				vo.setOrgFileName(orgFileName);
				vo.setFileSize(fileSize);
				vo.setSavePath(fileFullPath);
				vo.setSaveFileName(saveFileName+"."+ext);
				vo.setExtension(ext);
				
				file.transferTo(new File(fileFullPath+File.separator+vo.getSaveFileName()));
			} catch (IllegalStateException e) {
				modelAndView.addObject("message", "파일 업로드 실패:"+e.getMessage());
			} catch (IOException e) {
				modelAndView.addObject("message", "파일 업로드 실패:"+e.getMessage());
			}
			modelAndView.addObject("vo", vo);
		}else {
			modelAndView.addObject("message","파일 업로드 실패 : file empty");
		}
		
		modelAndView.setViewName("file/fileUploadResult");
		return modelAndView;
	}
	
	
}
