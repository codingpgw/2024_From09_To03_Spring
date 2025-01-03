package com.pcwk.ehr.file.domain;

import com.pcwk.ehr.cmn.DTO;

public class FileVO extends DTO {
	private String 	orgFileName;
	private String	saveFileName;
	private String 	savePath;
	private long 	fileSize;
	private String	extension;
	
	public FileVO() {
		
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "FileVO [orgFileName=" + orgFileName + ", saveFileName=" + saveFileName + ", savePath=" + savePath
				+ ", fileSize=" + fileSize + ", extension=" + extension + ", toString()=" + super.toString() + "]";
	}	
	
}
