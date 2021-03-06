/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLReactionCommentListResponse.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   reaction/v1
// Description:
//   Rogag API for Commenting, UpVoting and DownVoting Funny Posts
// Classes:
//   GTLReactionCommentListResponse (0 custom class methods, 2 custom properties)

#import "GTLReactionCommentListResponse.h"

#import "GTLReactionComments.h"

// ----------------------------------------------------------------------------
//
//   GTLReactionCommentListResponse
//

@implementation GTLReactionCommentListResponse
@dynamic comments, cursor;

+ (NSDictionary *)arrayPropertyToClassMap {
  NSDictionary *map =
    [NSDictionary dictionaryWithObject:[GTLReactionComments class]
                                forKey:@"comments"];
  return map;
}

@end
