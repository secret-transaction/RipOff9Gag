/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLReactionVoteResponse.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   reaction/v1
// Description:
//   Rogag API for Commenting, UpVoting and DownVoting Funny Posts
// Classes:
//   GTLReactionVoteResponse (0 custom class methods, 6 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLObject.h"
#else
  #import "GTLObject.h"
#endif

// ----------------------------------------------------------------------------
//
//   GTLReactionVoteResponse
//

@interface GTLReactionVoteResponse : GTLObject
@property (retain) NSNumber *commentCount;  // intValue
@property (retain) NSNumber *downvoteCount;  // intValue
@property (retain) NSNumber *isComment;  // boolValue
@property (retain) NSNumber *pointsCount;  // intValue
@property (retain) NSNumber *postId;  // longLongValue
@property (retain) NSNumber *upvoteCount;  // intValue
@end
