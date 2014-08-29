package com.secrettransaction.rogagserver.service;

import static com.secrettransaction.rogagserver.util.ObjectifySupport.load;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Key;
import com.secrettransaction.rogagserver.entity.AppUser;

public class AppUserQueryService {

	private static Object lock = new Object();
	private static AppUserQueryService instance;
	
	public static AppUserQueryService getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new AppUserQueryService();
				}
			}
		}
		
		return instance;
	}
	
	private AppUserQueryService(){}
	
	public Map<Long, AppUser> bulkFetch(List<Key<AppUser>> keyList) {
		List<Long> userIds = new ArrayList<>(keyList.size());
		for (Key<AppUser> k : keyList) {
			if (!userIds.contains(k.getId())) {
				userIds.add(k.getId());
			}
		}
		
		return load(AppUser.class).ids(userIds);
	}
	
}
