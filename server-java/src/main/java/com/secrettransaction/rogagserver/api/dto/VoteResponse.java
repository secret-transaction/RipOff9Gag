package com.secrettransaction.rogagserver.api.dto;

public class VoteResponse {

	private Long postId;
	private Boolean isComment;
	private Integer upvoteCount;
	private Integer downvoteCount;
	private Integer pointsCount;
	private Integer commentCount;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Boolean getIsComment() {
		return isComment;
	}

	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}

	public Integer getUpvoteCount() {
		return upvoteCount;
	}

	public void setUpvoteCount(Integer upvoteCount) {
		this.upvoteCount = upvoteCount;
	}

	public Integer getDownvoteCount() {
		return downvoteCount;
	}

	public void setDownvoteCount(Integer downvoteCount) {
		this.downvoteCount = downvoteCount;
	}

	public Integer getPointsCount() {
		return pointsCount;
	}

	public void setPointsCount(Integer pointsCount) {
		this.pointsCount = pointsCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

}
