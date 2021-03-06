/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLPostUserPostCreateRequest.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   post/v1
// Description:
//   Rogag API for Viewing and Posting Funny Pics
// Classes:
//   GTLPostUserPostCreateRequest (0 custom class methods, 4 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLObject.h"
#else
  #import "GTLObject.h"
#endif

@class GTLPostUserAuthentication;

// ----------------------------------------------------------------------------
//
//   GTLPostUserPostCreateRequest
//

@interface GTLPostUserPostCreateRequest : GTLObject
@property (retain) GTLPostUserAuthentication *auth;
@property (copy) NSString *imageUrl;
@property (retain) NSNumber *isUnsafe;  // boolValue
@property (copy) NSString *title;
@end
