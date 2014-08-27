package com.secrettransaction.rogagserver.util;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.logging.Logger;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.LoadType;
import com.secrettransaction.rogagserver.entity.AppUser;
import com.secrettransaction.rogagserver.entity.AppUserAccount;
import com.secrettransaction.rogagserver.entity.AppUserLogin;
import com.secrettransaction.rogagserver.entity.FunnyPost;

public class ObjectifySupport {
	
	private static final Logger log = Logger.getLogger(ObjectifySupport.class.getSimpleName());

	//TODO: ensure that this is called at warmup
	//this works for now, but a lot of bugs are bound to happen soon
	static {
		log.config("Registering Entities:");
		ObjectifyService.register(AppUser.class);
		ObjectifyService.register(AppUserAccount.class);
		ObjectifyService.register(AppUserLogin.class);
		ObjectifyService.register(FunnyPost.class);
	}
	
	public static<T> Key<T> save(T entity) {
		return ofy().save().entity(entity).now();
	}
	
	public static<T> LoadType<T> load(Class<T> type) {
		return ofy().load().type(type);
	}
}
