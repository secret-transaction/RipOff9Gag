package com.secrettransaction.rogagserver.api.dto;

public class UserPostCreateRequest {

	private UserAuthentication auth;
	private String title;
	private String imageUrl;
	private Boolean isUnsafe;

	public UserAuthentication getAuth() {
		return auth;
	}

	public void setAuth(UserAuthentication auth) {
		this.auth = auth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getIsUnsafe() {
		return isUnsafe;
	}

	public void setIsUnsafe(Boolean isUnsafe) {
		this.isUnsafe = isUnsafe;
	}

	@Override
	public String toString() {
		return "UserPostCreateRequest [auth=" + auth + ", title=" + title + ", imageUrl=" + imageUrl + ", isUnsafe=" + isUnsafe + "]";
	}

}
