package com.secrettransaction.rogagserver.api.dto;

import java.util.List;

public class UserPostListResponse extends BaseReponse {

	private String cursor;
	private List<UserPost> posts;

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public List<UserPost> getPosts() {
		return posts;
	}

	public void setPosts(List<UserPost> posts) {
		this.posts = posts;
	}
}
