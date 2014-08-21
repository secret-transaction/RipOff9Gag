/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLPostUserPost.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   post/v1
// Description:
//   Rogag API for Viewing and Posting Funny Pics
// Classes:
//   GTLPostUserPost (0 custom class methods, 10 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLObject.h"
#else
  #import "GTLObject.h"
#endif

@class GTLPostUserAccount;

// ----------------------------------------------------------------------------
//
//   GTLPostUserPost
//

@interface GTLPostUserPost : GTLObject
@property (retain) NSNumber *commentCount;  // intValue
@property (retain) NSNumber *downVoted;  // boolValue
@property (copy) NSString *imageType;
@property (copy) NSString *imageUrl;
@property (retain) NSNumber *isUnsafe;  // boolValue
@property (retain) GTLPostUserAccount *owner;
@property (retain) NSNumber *points;  // intValue
@property (copy) NSString *postId;
@property (copy) NSString *title;
@property (retain) NSNumber *upVoted;  // boolValue
@end
