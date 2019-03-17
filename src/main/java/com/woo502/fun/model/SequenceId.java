package com.woo502.fun.model;

import com.woo502.fun.common.BaseModel;

public class SequenceId extends BaseModel {

	private String _id;
	private long seq;
	
	public SequenceId() {
		super();
	}

	public SequenceId(String _id, long seq) {
		super();
		this._id = _id;
		this.seq = seq;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	
	
}
