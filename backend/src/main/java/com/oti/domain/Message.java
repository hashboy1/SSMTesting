package com.oti.domain;

/**
 * User映射类

 */
public class Message {

	private Integer flag;
	private String message;
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [flag=" + flag + ", message=" + message + "]";
	}


}
