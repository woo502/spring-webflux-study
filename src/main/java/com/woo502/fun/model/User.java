package com.woo502.fun.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.woo502.fun.common.BaseModel;

@Document
public class User extends BaseModel {
	
	@Id
    private long userId;

	@Indexed(unique=true)
    private String openId;

    private String nick;

    private Date createTime;
    
    @Transient
    private String token;

    public User(long userId, String openId, String nick, Date createTime) {
        this.userId = userId;
        this.openId = openId;
        this.nick = nick;
        this.createTime = createTime;
    }

    public User() {
        super();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", openId=" + openId + ", nick=" + nick + ", createTime=" + createTime + "]";
	}
    
    
}