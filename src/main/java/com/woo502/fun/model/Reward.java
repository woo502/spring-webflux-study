package com.woo502.fun.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.woo502.fun.common.BaseModel;

@Document
public class Reward extends BaseModel {
	
	@Id
    private Long rId;

    private Integer userId;

    private Integer tId;

    private String voiceId;

    private String picId;

    private Date createTime;

    public Reward(Long rId, Integer userId, Integer tId, String voiceId, String picId, Date createTime) {
        this.rId = rId;
        this.userId = userId;
        this.tId = tId;
        this.voiceId = voiceId;
        this.picId = picId;
        this.createTime = createTime;
    }

    public Reward() {
        super();
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(String voiceId) {
        this.voiceId = voiceId == null ? null : voiceId.trim();
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId == null ? null : picId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}