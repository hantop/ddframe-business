package com.ddframe.exception;

public class ExceptionResponse {
	private long errorcode;
	private String errormessage;
	private ExceptionResponse subexception;

	public ExceptionResponse(int errorCode2, String message) {
		this.errorcode = errorCode2;
		this.errormessage = message;
	}

	public long getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(long errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public ExceptionResponse getSubexception() {
		return subexception;
	}

	public void setSubexception(ExceptionResponse subexception) {
		this.subexception = subexception;
	}

}
