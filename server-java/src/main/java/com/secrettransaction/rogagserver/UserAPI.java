package com.secrettransaction.rogagserver;

import static com.secrettransaction.rogagserver.util.ObjectifySupport.save;

import java.util.logging.Logger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.googlecode.objectify.Key;
import com.secrettransaction.rogagserver.api.dto.AppError;
import com.secrettransaction.rogagserver.api.dto.UserLoginRequest;
import com.secrettransaction.rogagserver.api.dto.UserLoginResponse;
import com.secrettransaction.rogagserver.api.dto.UserRegistrationRequest;
import com.secrettransaction.rogagserver.api.dto.UserRegistrationResponse;
import com.secrettransaction.rogagserver.entity.AppUser;
import com.secrettransaction.rogagserver.entity.AppUserAccount;
import com.secrettransaction.rogagserver.util.PasswordEncryption;
import com.secrettransaction.rogagserver.util.SimpleValidatorUtil;

@Api(name="user", version="v1", description="Rogag API for User Management")
public class UserAPI {
	
	private static final Logger log = Logger.getLogger(UserAPI.class.getSimpleName());
	
	@ApiMethod(name="create", httpMethod=HttpMethod.POST, path="create")
	public UserRegistrationResponse createUser(UserRegistrationRequest request) {
		log.info(String.format("creating user: %s", request.getEmail()));
		
		UserRegistrationResponse response = new UserRegistrationResponse();

		String email = request.getEmail();
		boolean invalidEmail = email==null || email.isEmpty() || !SimpleValidatorUtil.validEmail(email);
		
		if (invalidEmail) {
			AppError error = new AppError();
			error.setErrorCode(ErrorConstants.CODE_INVALID_EMAIL_FORMAT);
			error.setErrorCode(ErrorConstants.MESSAGE_INVALID_EMAIL_FORMAT);
			response.getAppErrors().add(error);
		}
		
		if (!invalidEmail) {
			//TODO: perform unique email validation
		}
		
		//TODO: perform transactional save
		if (response.getAppErrors().isEmpty()) {
			AppUser appUser = new AppUser();
			appUser.setFullName(String.format("%s %s", request.getFirstName(), request.getLastName()));
			appUser.setDisplayName(appUser.getFullName());
			appUser.setFirstName(request.getFirstName());
			appUser.setLastName(request.getLastName());
			
			Key<AppUser> key = save(appUser);
			log.config("saved user:" + appUser);
			
			AppUserAccount userAccount = new AppUserAccount();
			userAccount.setType(AppUserAccount.TYPE_USERNAME);
			userAccount.setUsername(request.getEmail());
			userAccount.setPassword(PasswordEncryption.encrypt(request.getPassword()));
			userAccount.setUserKey(key);
			save(userAccount);
			log.config("saved account:" + userAccount);
			
			response.setUserId(key.getId()+"");
		}
		
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
