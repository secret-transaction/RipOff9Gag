package com.secrettransaction.rogagserver.entity;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class AppUserLoginSession {

	@Id
	private Long id;

	@Index
	private Date loginDate;

	@Index
	private Date lastAccessedDate;

	@Index
	private Key<AppUser> userKey;

	@Index
	private Key<AppUserAccount> userAccountKey;

	@Index
	private String accessToken;

	@Index
	private String clientId;

	@Index
	private String clientOS; // android, ios, windows
	
	@Index 
	private String clientOSVersion;

	@Index
	private String clientDevice;

	@Index
	private String clientVersion;
	
	@Index
	private Long clientVersionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLastAccessedDate() {
		return lastAccessedDate;
	}

	public void setLastAccessedDate(Date lastAccessedDate) {
		this.lastAccessedDate = lastAccessedDate;
	}

	public Key<AppUser> getUserKey() {
		return userKey;
	}

	public void setUserKey(Key<AppUser> userKey) {
		this.userKey = userKey;
	}

	public Key<AppUserAccount> getUserAccountKey() {
		return userAccountKey;
	}

	public void setUserAccountKey(Key<AppUserAccount> userAccountKey) {
		this.userAccountKey = userAccountKey;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientOS() {
		return clientOS;
	}

	public void setClientOS(String clientOS) {
		this.clientOS = clientOS;
	}

	public String getClientDevice() {
		return clientDevice;
	}

	public void setClientDevice(String clientDevice) {
		this.clientDevice = clientDevice;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public Long getClientVersionId() {
		return clientVersionId;
	}

	public void setClientVersionId(Long clientVersionId) {
		this.clientVersionId = clientVersionId;
	}

}
