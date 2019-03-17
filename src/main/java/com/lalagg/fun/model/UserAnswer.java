package com.lalagg.fun.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lalagg.fun.common.BaseModel;

@Document
public class UserAnswer extends BaseModel {

	@Id
	private Long id;
	
	private Long userId;
	
	private Long tid;
	
	private Long qId;
	
	private Long aId;
	
	private String text;
	
	private Date createTime;

	public UserAnswer(Long userId, Long tid, Long qId, String text) {
		super();
		this.userId = userId;
		this.tid = tid;
		this.qId = qId;
		this.text = text;
		this.createTime = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public Long getaId() {
		return aId;
	}

	public void setaId(Long aId) {
		this.aId = aId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
