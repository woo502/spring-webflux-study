package com.lalagg.fun.model;

import com.lalagg.fun.common.BaseModel;

public class Token extends BaseModel {

	private String userId;
	
	private String token;

	public Token(String userId, String token) {
		super();
		this.userId = userId;
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
