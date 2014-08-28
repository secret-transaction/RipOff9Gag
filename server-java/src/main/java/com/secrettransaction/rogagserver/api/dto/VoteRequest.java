package com.secrettransaction.rogagserver.api.dto;

public class VoteRequest extends BaseRequest {

	private Long postId;
	private Boolean isUpvote;
	private Boolean isComment;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Boolean getIsUpvote() {
		return isUpvote;
	}

	public void setIsUpvote(Boolean isUpvote) {
		this.isUpvote = isUpvote;
	}

	public Boolean getIsComment() {
		return isComment;
	}

	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}

}
