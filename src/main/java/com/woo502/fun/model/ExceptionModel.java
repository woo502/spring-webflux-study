package com.woo502.fun.model;

public class ExceptionModel extends Throwable  {

	private long errCode;
	
	private Exception e;

	public ExceptionModel() {
		super();
	}

	public ExceptionModel(long errCode, Exception e) {
		super();
		this.errCode = errCode;
		this.e = e;
	}

	public long getErrCode() {
		return errCode;
	}

	public void setErrCode(long errCode) {
		this.errCode = errCode;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}
	
}
