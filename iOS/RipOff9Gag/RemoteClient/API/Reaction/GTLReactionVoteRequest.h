/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLReactionVoteRequest.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   reaction/v1
// Description:
//   Rogag API for Commenting, UpVoting and DownVoting Funny Posts
// Classes:
//   GTLReactionVoteRequest (0 custom class methods, 4 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLObject.h"
#else
  #import "GTLObject.h"
#endif

@class GTLReactionUserAuthentication;

// ----------------------------------------------------------------------------
//
//   GTLReactionVoteRequest
//

@interface GTLReactionVoteRequest : GTLObject
@property (retain) GTLReactionUserAuthentication *auth;
@property (retain) NSNumber *isComment;  // boolValue
@property (retain) NSNumber *isUpvote;  // boolValue
@property (retain) NSNumber *postId;  // longLongValue
@end
