package com.secrettransaction.rogagserver;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.secrettransaction.rogagserver.api.dto.UserPostCreateRequest;
import com.secrettransaction.rogagserver.api.dto.UserPostCreateResponse;
import com.secrettransaction.rogagserver.api.dto.UserPostListRequest;
import com.secrettransaction.rogagserver.api.dto.UserPostListResponse;

@Api(name="post", version="v1", description="Rogag API for Viewing and Posting Funny Pics")
public class PostAPI {

	@ApiMethod(name="list", path="list", httpMethod=HttpMethod.GET)
	public UserPostListResponse listPosts(UserPostListRequest request) {
		
		UserPostListResponse response = new UserPostListResponse();
		
		return response;
	}
	
	@ApiMethod(name="create", path="create", httpMethod=HttpMethod.POST)
	public UserPostCreateResponse createPost(UserPostCreateRequest request) {
		
		UserPostCreateResponse response = new UserPostCreateResponse();
		response.setPostId("1111");
		
		return response;
	}
}
