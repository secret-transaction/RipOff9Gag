package com.secrettransaction.rogagserver;

import static com.secrettransaction.rogagserver.util.ObjectifySupport.*;

import java.util.logging.Logger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.googlecode.objectify.Key;
import com.secrettransaction.rogagserver.api.dto.UserLoginRequest;
import com.secrettransaction.rogagserver.api.dto.UserLoginResponse;
import com.secrettransaction.rogagserver.api.dto.UserRegistrationRequest;
import com.secrettransaction.rogagserver.api.dto.UserRegistrationResponse;
import com.secrettransaction.rogagserver.entity.AppUser;

@Api(name="user", version="v1", description="Rogag API for User Management")
public class UserAPI {
	
	private static final Logger log = Logger.getLogger(UserAPI.class.getSimpleName());
	
	@ApiMethod(name="create", httpMethod=HttpMethod.POST, path="create")
	public UserRegistrationResponse createUser(UserRegistrationRequest request) {
		log.info(String.format("Creating user: %s", request.getEmail()));
		
		UserRegistrationResponse response = new UserRegistrationResponse();
		
		AppUser appUser = new AppUser();
		
		Key<AppUser> key = save(appUser);
		response.setUserId(key.getId()+"");
		
		return response;
	}
	
	@ApiMethod(name="login", httpMethod=HttpMethod.PUT, path="login")
	public UserLoginResponse login(UserLoginRequest request) {
		
		UserLoginResponse response = new UserLoginResponse();
		response.setUserId("1111");
		response.setUserToken("token");
		
		return response;
	}
}
