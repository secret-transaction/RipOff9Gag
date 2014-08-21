package com.secrettransaction.rogagserver.api.dto;

import java.util.List;

public class CommentListResponse {

	private String cursor;
	private List<Comments> comments;

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

}
