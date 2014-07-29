/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLPostApiCommonUserAuthentication.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   post/v1
// Description:
//   Rogag API for Viewing and Posting Funny Pics
// Classes:
//   GTLPostApiCommonUserAuthentication (0 custom class methods, 2 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLObject.h"
#else
  #import "GTLObject.h"
#endif

// ----------------------------------------------------------------------------
//
//   GTLPostApiCommonUserAuthentication
//

@interface GTLPostApiCommonUserAuthentication : GTLObject
@property (copy) NSString *userId;
@property (copy) NSString *userToken;
@end
