package com.secrettransaction.rogagserver;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static com.secrettransaction.rogagserver.util.ObjectifySupport.*;

import java.util.Date;
import java.util.logging.Logger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.secrettransaction.rogagserver.api.dto.AppError;
import com.secrettransaction.rogagserver.api.dto.UserLoginRequest;
import com.secrettransaction.rogagserver.api.dto.UserLoginResponse;
import com.secrettransaction.rogagserver.api.dto.UserRegistrationRequest;
import com.secrettransaction.rogagserver.api.dto.UserRegistrationResponse;
import com.secrettransaction.rogagserver.entity.AppUser;
import com.secrettransaction.rogagserver.entity.AppUserAccount;
import com.secrettransaction.rogagserver.entity.AppUserLoginSession;
import com.secrettransaction.rogagserver.util.AccessTokenGenerator;
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
			appUser.setDisplayImageUrl("http://38.media.tumblr.com/avatar_a1f7bb7ebcce_128.png");
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
		log.config("logging in:" + request);
		UserLoginResponse response = new UserLoginResponse();
		
		Query<AppUserAccount> loginQuery = load(AppUserAccount.class).
				filter("type", request.getLoginType()).
				filter("username", request.getUsername());
		
		AppUserAccount account = loginQuery.first().now();
		
		if (account==null) {
			AppError accountDoesNotExist = new AppError(ErrorConstants.CODE_ACCOUNT_DOES_NOT_EXIST, ErrorConstants.MESSAGE_ACCOUNT_DOES_NOT_EXIST);
			response.getAppErrors().add(accountDoesNotExist);
		} else if(account.getPassword()!=null && !account.getPassword().equals(PasswordEncryption.encrypt(request.getPassword()))) {
			AppError incorrectPassword = new AppError(ErrorConstants.CODE_ACCOUNT_INCORRECT_PASSWORD, ErrorConstants.MESSAGE_ACCOUNT_INCORRECT_PASSWORD);
			response.getAppErrors().add(incorrectPassword);
		}
		
		if (response.getAppErrors().isEmpty()) {
			
			AppUserLoginSession login = new AppUserLoginSession();
			login.setClientId(request.getClientId());
			login.setClientDevice(request.getClientDevice());
			login.setClientOS(request.getClientOS());
			login.setClientVersion(request.getClientOSVersion());
			login.setClientVersion(request.getClientVersion());
			login.setClientVersionId(request.getClientVersionId());
			login.setAccessToken(AccessTokenGenerator.generate(request.getUsername()+request.getPassword())); //TODO: generate access token
			login.setLoginDate(new Date());
			login.setLastAccessedDate(new Date());
			login.setUserKey(account.getUserKey());
			login.setUserAccountKey(account.key());
			save(login);
			
			AppUser user = ofy().load().key(account.getUserKey()).now();

			response.setUserId(account.getUserKey().getId()+"");
			response.setUserToken(login.getAccessToken());
			response.setUsername(user.getDisplayName());
			response.setUserImageUrl(user.getDisplayImageUrl());
		}
		
		return response;
	}
}
