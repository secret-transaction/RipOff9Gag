package com.secrettransaction.rogagserver.api.dto;

public class UserPostListRequest {

	private String cursor;
	private String type;
	private Integer pageSize;

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "UserPostListRequest [cursor=" + cursor + ", type=" + type + ", pageSize=" + pageSize + "]";
	}

}
