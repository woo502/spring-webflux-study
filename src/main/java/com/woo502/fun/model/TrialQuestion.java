package com.woo502.fun.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.woo502.fun.common.BaseModel;

@Document
public class TrialQuestion extends BaseModel {
	
	@Id
    private Long tqId;

    private Long tId;

    private Long qId;

    private Date createTime;

    public TrialQuestion(Long tqId, Long tId, Long qId, Date createTime) {
        this.tqId = tqId;
        this.tId = tId;
        this.qId = qId;
        this.createTime = createTime;
    }

    public TrialQuestion() {
        super();
    }

    public Long getTqId() {
        return tqId;
    }

    public void setTqId(Long tqId) {
        this.tqId = tqId;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}