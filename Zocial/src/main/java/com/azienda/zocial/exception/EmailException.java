package com.azienda.zocial.exception;

public class EmailException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public EmailException(String msg,Throwable cause) {
		super(msg,cause);
	}
}
