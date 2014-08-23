package com.secrettransaction.rogagserver.api.dto;

public class BaseRequest {

	private UserAuthentication auth;

	public UserAuthentication getAuth() {
		return auth;
	}

	public void setAuth(UserAuthentication auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "BaseRequest [auth=" + auth + "]";
	}

}
