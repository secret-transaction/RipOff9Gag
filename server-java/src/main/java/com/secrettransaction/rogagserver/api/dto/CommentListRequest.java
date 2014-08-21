package com.secrettransaction.rogagserver.api.dto;

public class CommentListRequest {

	private String cursor;
	private Integer pageSize;

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
