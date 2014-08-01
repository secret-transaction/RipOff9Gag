/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLQueryUser.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   user/v1
// Description:
//   Rogag API for User Management
// Classes:
//   GTLQueryUser (2 custom class methods, 1 custom properties)

#import "GTLQueryUser.h"

#import "GTLUserApiUserUserLoginRequest.h"
#import "GTLUserApiUserUserLoginResponse.h"
#import "GTLUserApiUserUserRegistrationRequest.h"
#import "GTLUserApiUserUserRegistrationResponse.h"

@implementation GTLQueryUser

@dynamic fields;

#pragma mark -
#pragma mark Service level methods
// These create a GTLQueryUser object.

+ (id)queryForCreateWithObject:(GTLUserApiUserUserRegistrationRequest *)object {
  if (object == nil) {
    GTL_DEBUG_ASSERT(object != nil, @"%@ got a nil object", NSStringFromSelector(_cmd));
    return nil;
  }
  NSString *methodName = @"user.create";
  GTLQueryUser *query = [self queryWithMethodName:methodName];
  query.bodyObject = object;
  query.expectedObjectClass = [GTLUserApiUserUserRegistrationResponse class];
  return query;
}

+ (id)queryForLoginWithObject:(GTLUserApiUserUserLoginRequest *)object {
  if (object == nil) {
    GTL_DEBUG_ASSERT(object != nil, @"%@ got a nil object", NSStringFromSelector(_cmd));
    return nil;
  }
  NSString *methodName = @"user.login";
  GTLQueryUser *query = [self queryWithMethodName:methodName];
  query.bodyObject = object;
  query.expectedObjectClass = [GTLUserApiUserUserLoginResponse class];
  return query;
}

@end