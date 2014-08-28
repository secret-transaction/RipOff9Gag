package com.secrettransaction.rogagserver;

import static com.secrettransaction.rogagserver.util.ObjectifySupport.load;
import static com.secrettransaction.rogagserver.util.ObjectifySupport.save;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.secrettransaction.rogagserver.api.dto.CommentCreateRequest;
import com.secrettransaction.rogagserver.api.dto.CommentCreateResponse;
import com.secrettransaction.rogagserver.api.dto.CommentListRequest;
import com.secrettransaction.rogagserver.api.dto.CommentListResponse;
import com.secrettransaction.rogagserver.api.dto.VoteRequest;
import com.secrettransaction.rogagserver.api.dto.VoteResponse;
import com.secrettransaction.rogagserver.entity.FunnyPost;
import com.secrettransaction.rogagserver.entity.PostComment;

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
		
		//TODO: perform some of these operation on a task queue
		//TODO: check if the user has already voted
		if (request.getIsComment()) {
			PostComment comment = load(PostComment.class).id(request.getPostId()).now();
			
			if (request.getIsUpvote()) {
				comment.setUpvotes(comment.getUpvotes()+1);
				comment.setPoints(comment.getPoints()+1);
			} else {
				comment.setDownvotes(comment.getDownvotes()+1);
				comment.setPoints(comment.getPoints()-1);
			}
			
			response.setCommentCount(comment.getCommentCount());
			response.setDownvoteCount(comment.getDownvotes());
			response.setUpvoteCount(comment.getUpvotes());
			response.setPointsCount(comment.getPoints());
			
			save(comment);
		} else {
			FunnyPost funnyPost = load(FunnyPost.class).id(request.getPostId()).now();
			
			if (request.getIsUpvote()) {
				funnyPost.setUpvoteCount(funnyPost.getUpvoteCount()+1);
				funnyPost.setPoints(funnyPost.getPoints()+1);
			} else {
				funnyPost.setDownvoteCount(funnyPost.getDownvoteCount()+1);
				funnyPost.setPoints(funnyPost.getPoints()-1);
			}
			
			response.setCommentCount(funnyPost.getCommentCount());
			response.setDownvoteCount(funnyPost.getDownvoteCount());
			response.setUpvoteCount(funnyPost.getUpvoteCount());
			response.setPointsCount(funnyPost.getPoints());
			
			save(funnyPost);
		}
		
		return response;
	}
}
