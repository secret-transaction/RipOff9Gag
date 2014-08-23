package com.secrettransaction.rogagserver.api.dto;

public class UserAuthentication {

	private String userId;
	private String userToken;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	@Override
	public String toString() {
		return "UserAuthentication [userId=" + userId + ", userToken=" + userToken + "]";
	}

}
