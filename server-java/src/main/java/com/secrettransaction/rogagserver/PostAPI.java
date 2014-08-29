package com.secrettransaction.rogagserver;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import com.secrettransaction.rogagserver.service.AppUserQueryService;
import com.secrettransaction.rogagserver.service.FunnyPostQueryService;
import com.secrettransaction.rogagserver.util.CursorList;
import com.secrettransaction.rogagserver.util.ObjectifySupport;

@Api(name="post", version="v1", description="Rogag API for Viewing and Posting Funny Pics")
public class PostAPI {

	private static final Logger log = Logger.getLogger(PostAPI.class.getSimpleName());
	private static final FunnyPostQueryService queryService = FunnyPostQueryService.getInstance();
	private static final AppUserQueryService userQueryService = AppUserQueryService.getInstance();
	
	@ApiMethod(name="list", path="list", httpMethod=HttpMethod.GET)
	public UserPostListResponse listPosts(UserPostListRequest request) {
		log.info("listing:" + request);
		
		CursorList<FunnyPost> postCList = queryService.getNewPosts(new Date(request.getDate()), null, 10);
		
		//gather users:
		List<Key<AppUser>> userKeyList = new LinkedList<>();
		for (FunnyPost p : postCList.getResults()) {
			userKeyList.add(p.getOwner());
		}
		
		Map<Long, AppUser> userMap = userQueryService.bulkFetch(userKeyList);
		List<UserPost> postList = new LinkedList<>();

		for (FunnyPost p : postCList.getResults()) {
			UserPost post = new UserPost();
			post.setCommentCount(p.getCommentCount());
			post.setImageUrl(p.getImageUrl());
			post.setTitle(p.getTitle());
			
			AppUser u = userMap.get(p.getOwner().getId());
			UserAccount user = new UserAccount();
			user.setUserId(u.getId().toString());
			user.setUsername(u.getDisplayName());
			
			post.setOwner(user);
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
