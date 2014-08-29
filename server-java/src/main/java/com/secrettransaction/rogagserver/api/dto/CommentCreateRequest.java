package com.secrettransaction.rogagserver.api.dto;

public class CommentCreateRequest extends BaseRequest {

	private Long postId;
	private Boolean isComment;
	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
