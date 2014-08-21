package com.secrettransaction.rogagserver.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class AppUserAccount {
	
	public static final String TYPE_USERNAME = "USERNAME";
	public static final String TYPE_FACEBOOK = "FACEBOOK";
	public static final String TYPE_GOOGLE = "GOOGLE";

	@Id
	private String id;
	
	@Index
	private Key<AppUser> userKey;
	
	@Index
	private String type;
	
	/*
	 * username
	 * facebook id
	 * twitter id 
	 */
	@Index
	private String username;
	
	@Index
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Key<AppUser> getUserKey() {
		return userKey;
	}

	public void setUserKey(Key<AppUser> userKey) {
		this.userKey = userKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		updateId();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		updateId();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private void updateId() {
		this.id = String.format("%s_%s", type, username);
	}
}
