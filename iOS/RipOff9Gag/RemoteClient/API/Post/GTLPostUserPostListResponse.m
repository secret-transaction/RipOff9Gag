/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLPostUserPostListResponse.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   post/v1
// Description:
//   Rogag API for Viewing and Posting Funny Pics
// Classes:
//   GTLPostUserPostListResponse (0 custom class methods, 2 custom properties)

#import "GTLPostUserPostListResponse.h"

#import "GTLPostUserPost.h"

// ----------------------------------------------------------------------------
//
//   GTLPostUserPostListResponse
//

@implementation GTLPostUserPostListResponse
@dynamic cursor, posts;

+ (NSDictionary *)arrayPropertyToClassMap {
  NSDictionary *map =
    [NSDictionary dictionaryWithObject:[GTLPostUserPost class]
                                forKey:@"posts"];
  return map;
}

@end
