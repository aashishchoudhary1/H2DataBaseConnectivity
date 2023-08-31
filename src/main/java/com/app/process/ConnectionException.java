package com.app.process;

public class ConnectionException extends Exception {
	
	private String msg;
	
	public ConnectionException(String message) {
		
		this.msg = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
