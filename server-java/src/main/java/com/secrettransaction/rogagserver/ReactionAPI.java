package com.secrettransaction.rogagserver;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.secrettransaction.rogagserver.api.dto.CommentCreateRequest;
import com.secrettransaction.rogagserver.api.dto.CommentCreateResponse;
import com.secrettransaction.rogagserver.api.dto.CommentListRequest;
import com.secrettransaction.rogagserver.api.dto.CommentListResponse;
import com.secrettransaction.rogagserver.api.dto.VoteRequest;
import com.secrettransaction.rogagserver.api.dto.VoteResponse;

@Api(name="reaction", version="v1", description="Rogag API for Commenting, UpVoting and DownVoting Funny Posts")
public class ReactionAPI {

	@ApiMethod(name="comment.list", path="list", httpMethod=HttpMethod.GET)
	public CommentListResponse listComments(CommentListRequest request) {
		CommentListResponse response = new CommentListResponse();
		
		return response;
	}
	
	@ApiMethod(name="comment.create", path="create", httpMethod=HttpMethod.POST)
	public CommentCreateResponse createComment(CommentCreateRequest request) {
		CommentCreateResponse response = new CommentCreateResponse();
		
		return response;
	}
	
	@ApiMethod(name="vote", path="vote", httpMethod=HttpMethod.PUT)
	public VoteResponse vote(VoteRequest request) {
		VoteResponse response = new VoteResponse();
		
		return response;
	}
}
