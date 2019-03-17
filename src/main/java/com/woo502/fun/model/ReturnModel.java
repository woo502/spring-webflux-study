package com.woo502.fun.model;

import com.woo502.fun.common.BaseModel;

public class ReturnModel extends BaseModel {

	private int retCode;
	private String retMsg;
	private Object data;
	
	public ReturnModel() {
		super();
	}

	public ReturnModel(int retCode, String retMsg, Object data) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
		this.data = data;
	}
	
	public ReturnModel(int retCode, String retMsg) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
