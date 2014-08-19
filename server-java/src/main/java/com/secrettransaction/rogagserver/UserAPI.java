package com.secrettransaction.rogagserver;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.secrettransaction.rogagserver.api.dto.UserLoginRequest;
import com.secrettransaction.rogagserver.api.dto.UserLoginResponse;
import com.secrettransaction.rogagserver.api.dto.UserRegistrationRequest;
import com.secrettransaction.rogagserver.api.dto.UserRegistrationResponse;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/
@Api(name="user", version="v1", description="Rogag API for User Management")
public class UserAPI {
	
	@ApiMethod(name="create", httpMethod=HttpMethod.POST, path="create")
	public UserRegistrationResponse createUser(UserRegistrationRequest request) {
	
		UserRegistrationResponse response = new UserRegistrationResponse();
		response.setUserId("1111");
		
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
