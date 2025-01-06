package com.pcwk.ehr.cmn;

/**
 * DML의 처리 상태와, 메시지
 * @author acorn
 *
 */
public class MessageVO extends DTO {

	private int messageId;//상태: 1이면 성공 / 1 이외는 다 실패
	private String message;//메시지
	
	public MessageVO() {
		
	}

	public MessageVO(int messageId, String message) {
		this.messageId = messageId;
		this.message = message;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageVO [messageId=" + messageId + ", message=" + message + "]";
	}
	
}