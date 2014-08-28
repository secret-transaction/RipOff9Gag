package com.secrettransaction.rogagserver.service;

import static com.secrettransaction.rogagserver.util.ObjectifySupport.load;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.secrettransaction.rogagserver.entity.FunnyPost;
import com.secrettransaction.rogagserver.util.CursorList;

public class FunnyPostQueryService {

	private static Object lock = new Object();
	private static FunnyPostQueryService instance;
	
	public static FunnyPostQueryService getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new FunnyPostQueryService();
				}
			}
		}
		
		return instance;
	}
	
	private FunnyPostQueryService(){}
	
	public CursorList<FunnyPost> getNewPosts(Date sinceDate, Cursor cursor, int pageSize) {
		
		final QueryResultIterator<FunnyPost> iter = load(FunnyPost.class).
				filter("dateCreated >", sinceDate).
				order("-dateCreated").
				limit(pageSize).
				iterator();
		
		final List<FunnyPost> posts = new LinkedList<>();
		while (iter.hasNext()) {
			posts.add(iter.next());
		}
		
		return new CursorList<FunnyPost>() {
			
			@Override
			public List<FunnyPost> getResults() {
				return posts;
			}
			
			@Override
			public Cursor getCursor() {
				return iter.getCursor();
			}
		};
	}
	
}
