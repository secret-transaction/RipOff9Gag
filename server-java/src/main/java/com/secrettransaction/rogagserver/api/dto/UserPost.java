package com.secrettransaction.rogagserver.api.dto;


public class UserPost {

	private String postId;
	private Integer points;
	private Integer commentCount;
	private Boolean upVoted;
	private Boolean downVoted;
	private Boolean isUnsafe;
	private String title;
	private String imageUrl;
	private String imageType;
	private UserAccount owner;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Boolean getUpVoted() {
		return upVoted;
	}

	public void setUpVoted(Boolean upVoted) {
		this.upVoted = upVoted;
	}

	public Boolean getDownVoted() {
		return downVoted;
	}

	public void setDownVoted(Boolean downVoted) {
		this.downVoted = downVoted;
	}

	public Boolean getIsUnsafe() {
		return isUnsafe;
	}

	public void setIsUnsafe(Boolean isUnsafe) {
		this.isUnsafe = isUnsafe;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public UserAccount getOwner() {
		return owner;
	}

	public void setOwner(UserAccount owner) {
		this.owner = owner;
	}

}
