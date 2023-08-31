package com.app.process;

public class DatabaseException extends Exception {
	
	private String msg;
	
	DatabaseException(String message){
		this.msg = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
