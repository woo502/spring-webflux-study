package com.lalagg.fun.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lalagg.fun.common.BaseModel;

@Document
public class Trial extends BaseModel {
	
	@Id
    private Long tId;

    private Long userId;

    private String voiceId;

    private String picId;

    private String poster;
    
    private Long money;
    
    private Integer status;
    
    private Integer limitUser;
    
    private Integer correct;

    private Long lastUpdateTime;

    private Date createTime;
    
    private List<Question> qlist;
    
    private List<Answer> alist;

    public Trial(Long userId) {
		super();
		this.tId = 0l;
        this.userId = userId;
        this.status = 1;
        this.limitUser = 0;
        this.money = 0l;
        this.correct = 0;
        this.createTime = new Date();;
        this.lastUpdateTime = createTime.getTime();
        
        qlist = new ArrayList<>();
        alist = new ArrayList<>();
	}

	public Trial() {
        super();
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster == null ? null : poster.trim();
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public List<Question> getQlist() {
		return qlist;
	}

	public void setQlist(List<Question> qlist) {
		this.qlist = qlist;
	}

	public List<Answer> getAlist() {
		return alist;
	}

	public void setAlist(List<Answer> alist) {
		this.alist = alist;
	}

	public Integer getLimitUser() {
		return limitUser;
	}

	public void setLimitUser(Integer limitUser) {
		this.limitUser = limitUser;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long moneny) {
		this.money = moneny;
	}

	public Integer getCorrect() {
		return correct;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}
	
}