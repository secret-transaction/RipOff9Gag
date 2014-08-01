/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLQueryUser.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   user/v1
// Description:
//   Rogag API for User Management
// Classes:
//   GTLQueryUser (2 custom class methods, 1 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLQuery.h"
#else
  #import "GTLQuery.h"
#endif

@class GTLUserApiUserUserLoginRequest;
@class GTLUserApiUserUserRegistrationRequest;

@interface GTLQueryUser : GTLQuery

//
// Parameters valid on all methods.
//

// Selector specifying which fields to include in a partial response.
@property (copy) NSString *fields;

#pragma mark -
#pragma mark Service level methods
// These create a GTLQueryUser object.

// Method: user.create
//  Authorization scope(s):
//   kGTLAuthScopeUserUserinfoEmail
// Fetches a GTLUserApiUserUserRegistrationResponse.
+ (id)queryForCreateWithObject:(GTLUserApiUserUserRegistrationRequest *)object;

// Method: user.login
//  Authorization scope(s):
//   kGTLAuthScopeUserUserinfoEmail
// Fetches a GTLUserApiUserUserLoginResponse.
+ (id)queryForLoginWithObject:(GTLUserApiUserUserLoginRequest *)object;

@end