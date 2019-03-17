package com.lalagg.fun.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lalagg.fun.common.BaseModel;

@Document
public class Answer extends BaseModel {
	
	@Id
    private Long ansId;

    private long tId;

    private long qId;
    
    private long uId;

    private Byte isRight;

    private Byte isOffical;
    
    @Transient
    private Byte userChoose;

    private Date createTime;

    private String text;

	public Answer(long qid2, long userId, String text) {
		super();
		this.qId = qid2;
		this.uId = userId;
		this.text = text;
	}
	
	public Answer(Long ansId, long tId, long qId, long uId, Byte isRight, Byte isOffical, Date createTime,
			String text) {
		super();
		this.ansId = ansId;
		this.tId = tId;
		this.qId = qId;
		this.uId = uId;
		this.isRight = isRight;
		this.isOffical = isOffical;
		this.createTime = createTime;
		this.text = text;
	}

	@Override
	public String toString() {
		return "Answer [ansId=" + ansId + ", tId=" + tId + ", qId=" + qId + ", uId=" + uId + ", isRight=" + isRight
				+ ", isOffical=" + isOffical + ", createTime=" + createTime + ", text=" + text + "]";
	}

	public Answer() {
        super();
    }

    public Long getAnsId() {
        return ansId;
    }

    public void setAnsId(Long ansId) {
        this.ansId = ansId;
    }

    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    public long getqId() {
        return qId;
    }

    public void setqId(long qId) {
        this.qId = qId;
    }

    public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}

	public Byte getIsRight() {
        return isRight;
    }

    public void setIsRight(Byte isRight) {
        this.isRight = isRight;
    }

    public Byte getIsOffical() {
        return isOffical;
    }

    public void setIsOffical(Byte isOffical) {
        this.isOffical = isOffical;
    }

    public Byte getUserChoose() {
		return userChoose;
	}

	public void setUserChoose(Byte userChoose) {
		this.userChoose = userChoose;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}