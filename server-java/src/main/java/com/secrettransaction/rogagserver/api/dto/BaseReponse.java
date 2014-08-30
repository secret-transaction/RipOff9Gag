package com.secrettransaction.rogagserver.api.dto;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseReponse {

	private Long serverTime = System.currentTimeMillis();
	private List<AppError> appErrors;

	public List<AppError> getAppErrors() {
		if (appErrors==null) {
			appErrors = new LinkedList<>();
		}
		
		return appErrors;
	}

	public void setAppErrors(List<AppError> appErrors) {
		this.appErrors = appErrors;
	}

	public Long getServerTime() {
		return serverTime;
	}

	public void setServerTime(Long serverTime) {
		this.serverTime = serverTime;
	}

}
