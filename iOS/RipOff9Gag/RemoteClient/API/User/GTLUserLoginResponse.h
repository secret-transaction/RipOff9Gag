/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLUserLoginResponse.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   user/v1
// Description:
//   Rogag API for User Management
// Classes:
//   GTLUserLoginResponse (0 custom class methods, 6 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLObject.h"
#else
  #import "GTLObject.h"
#endif

@class GTLUserAppError;

// ----------------------------------------------------------------------------
//
//   GTLUserLoginResponse
//

@interface GTLUserLoginResponse : GTLObject
@property (retain) NSArray *appErrors;  // of GTLUserAppError
@property (retain) NSNumber *serverTime;  // longLongValue
@property (copy) NSString *userId;
@property (copy) NSString *userImageUrl;
@property (copy) NSString *username;
@property (copy) NSString *userToken;
@end
