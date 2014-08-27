package com.secrettransaction.rogagserver.entity;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class FunnyPost {

	@Id
	private Long id;

	private String imageUrl;

	@Index
	private String title;

	@Index
	private boolean isUnsafe = false;

	@Index
	private Date dateCreated;

	@Index
	private Key<AppUser> owner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isUnsafe() {
		return isUnsafe;
	}

	public void setUnsafe(boolean isUnsafe) {
		this.isUnsafe = isUnsafe;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Key<AppUser> getOwner() {
		return owner;
	}

	public void setOwner(Key<AppUser> owner) {
		this.owner = owner;
	}

}
