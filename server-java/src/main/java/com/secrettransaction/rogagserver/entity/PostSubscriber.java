package com.secrettransaction.rogagserver.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class PostSubscriber {

	@Id
	private Long id;
	
	@Index
	private Key<FunnyPost> postKey;
	
	@Index
	private Key<AppUser> userKey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Key<FunnyPost> getPostKey() {
		return postKey;
	}

	public void setPostKey(Key<FunnyPost> postKey) {
		this.postKey = postKey;
	}

	public Key<AppUser> getUserKey() {
		return userKey;
	}

	public void setUserKey(Key<AppUser> userKey) {
		this.userKey = userKey;
	}
	
}
