package com.secrettransaction.rogagserver.util;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.logging.Logger;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.secrettransaction.rogagserver.entity.AppUser;
import com.secrettransaction.rogagserver.entity.AppUserAccount;

public class ObjectifySupport {
	
	private static final Logger log = Logger.getLogger(ObjectifySupport.class.getSimpleName());

	static {
		log.config("Registering Entities:");
		ObjectifyService.register(AppUser.class);
		ObjectifyService.register(AppUserAccount.class);
	}
	
	public static<T> Key<T> save(T entity) {
		return ofy().save().entity(entity).now();
	}
}
