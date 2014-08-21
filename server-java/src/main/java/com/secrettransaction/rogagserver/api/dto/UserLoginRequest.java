package com.secrettransaction.rogagserver.api.dto;


public class UserLoginRequest {

	private String loginType;
	private String username;
	private String password;
	private String clientId;
	private String clientOS; // android, ios, windows
	private String clientOSVersion;
	private String clientDevice;
	private String clientVersion;
	private Long clientVersionId;
	
	public String getLoginType() {
		return loginType;
	}

	/**
	 * check AppUserAccount.TYPE_**
	 * @param loginType
	 */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getClientOSVersion() {
		return clientOSVersion;
	}

	public void setClientOSVersion(String clientOSVersion) {
		this.clientOSVersion = clientOSVersion;
	}

	@Override
	public String toString() {
		return "UserLoginRequest [loginType=" + loginType + ", username=" + username + ", clientId=" + clientId + ", clientOS=" + clientOS + ", clientOSVersion=" + clientOSVersion + ", clientDevice=" + clientDevice + ", clientVersion=" + clientVersion + ", clientVersionId=" + clientVersionId + "]";
	}

}
