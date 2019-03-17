package com.lalagg.fun.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lalagg.fun.common.BaseModel;

@Document
public class Question extends BaseModel {
	
	@Id
    private Long qId;

    private String text;
    
    private Date createTime;
    
    @Transient
    private Answer[] answers;

    public Question(Long qId, String text, Date createTime) {
        this.qId = qId;
        this.text = text;
        this.createTime = createTime;
    }

    public Question() {
        super();
    }

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Answer[] getAnswers() {
		return answers;
	}

	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}

}