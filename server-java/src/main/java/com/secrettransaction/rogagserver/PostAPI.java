package com.secrettransaction.rogagserver;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.googlecode.objectify.Key;
import com.secrettransaction.rogagserver.api.dto.UserAccount;
import com.secrettransaction.rogagserver.api.dto.UserPost;
import com.secrettransaction.rogagserver.api.dto.UserPostCreateRequest;
import com.secrettransaction.rogagserver.api.dto.UserPostCreateResponse;
import com.secrettransaction.rogagserver.api.dto.UserPostListRequest;
import com.secrettransaction.rogagserver.api.dto.UserPostListResponse;
import com.secrettransaction.rogagserver.entity.AppUser;
import com.secrettransaction.rogagserver.entity.FunnyPost;
import com.secrettransaction.rogagserver.util.ObjectifySupport;

@Api(name="post", version="v1", description="Rogag API for Viewing and Posting Funny Pics")
public class PostAPI {

	private static final Logger log = Logger.getLogger(PostAPI.class.getSimpleName());
	
	@ApiMethod(name="list", path="list", httpMethod=HttpMethod.GET)
	public UserPostListResponse listPosts(UserPostListRequest request) {
		log.info("listing:" + request);
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUserId("1");
		userAccount.setUsername("test1");
		
		Long time = System.currentTimeMillis();
		List<UserPost> postList = new LinkedList<>();
		
		Random rand = new Random();
		
		for (int i=0; i<request.getPageSize(); i++) {
			UserPost post = new UserPost();
			post.setPostId("" + time + i);
			post.setImageUrl(String.format("https://rogag-server-stage.appspot.com/static/images/image%s.jpg", rand.nextInt(9)));
			post.setCommentCount(rand.nextInt(999));
			post.setIsUnsafe(rand.nextBoolean());
			post.setTitle(String.format("Some title %s", rand.nextInt(999)));
			
			postList.add(post);
		}
		
		UserPostListResponse response = new UserPostListResponse();
		response.setPosts(postList);
		
		return response;
	}
	
	@ApiMethod(name="create", path="create", httpMethod=HttpMethod.POST)
	public UserPostCreateResponse createPost(UserPostCreateRequest request) {
		log.info("create post:" + request);
		
		//TODO: validate crap
		//TODO: fix, this is lame
		Key<AppUser> key = Key.create(AppUser.class, Long.parseLong(request.getAuth().getUserId()));
		
		FunnyPost post = new FunnyPost();
		post.setDateCreated(new Date());
		post.setOwner(key);
		post.setTitle(request.getTitle());
		post.setImageUrl(request.getImageUrl());
		post.setUnsafe(request.getIsUnsafe());
		
		ObjectifySupport.save(post);
		
		//TODO: create various indexes
		
		UserPostCreateResponse response = new UserPostCreateResponse();
		response.setPostId("1111");
		
		return response;
	}
}
