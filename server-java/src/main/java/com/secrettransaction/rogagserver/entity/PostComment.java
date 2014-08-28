package com.secrettransaction.rogagserver.entity;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class PostComment {

	@Id
	private Long id;
	
	@Index
	private Date dateAdded;
	
	@Index
	private Key<AppUser> ownerKey;
	
	@Index
	private Key<FunnyPost> postKey;
	
	/**
	 * for replies
	 */
	@Index
	private Key<PostComment> commentKey;
	
	@Index
	private Integer points = 0;
	
	@Index
	private Integer upvotes = 0;
	
	@Index
	private Integer downvotes = 0;
	
	@Index
	private Integer commentCount = 0;
	
	private String message;

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

	public Key<AppUser> getOwnerKey() {
		return ownerKey;
	}

	public void setOwnerKey(Key<AppUser> ownerKey) {
		this.ownerKey = ownerKey;
	}

	public Key<FunnyPost> getPostKey() {
		return postKey;
	}

	public void setPostKey(Key<FunnyPost> postKey) {
		this.postKey = postKey;
	}

	public Key<PostComment> getCommentKey() {
		return commentKey;
	}

	public void setCommentKey(Key<PostComment> commentKey) {
		this.commentKey = commentKey;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(Integer upvotes) {
		this.upvotes = upvotes;
	}

	public Integer getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(Integer downvotes) {
		this.downvotes = downvotes;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
}
