package com.secrettransaction.rogagserver.entity;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * posts with points > 1,000
 */
@Entity
public class HotIndex {

	@Id
	private Long id;
	
	@Index
	private Date dateAdded;
	
	@Index
	private Key<FunnyPost> postKey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Key<FunnyPost> getPostKey() {
		return postKey;
	}

	public void setPostKey(Key<FunnyPost> postKey) {
		this.postKey = postKey;
	}
	
}
