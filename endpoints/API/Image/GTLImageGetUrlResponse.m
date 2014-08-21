/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLImageGetUrlResponse.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   image/v1
// Description:
//   Rogag API for Images
// Classes:
//   GTLImageGetUrlResponse (0 custom class methods, 3 custom properties)

#import "GTLImageGetUrlResponse.h"

#import "GTLImageAppError.h"

// ----------------------------------------------------------------------------
//
//   GTLImageGetUrlResponse
//

@implementation GTLImageGetUrlResponse
@dynamic appErrors, downloadUrl, uploadUrl;

+ (NSDictionary *)arrayPropertyToClassMap {
  NSDictionary *map =
    [NSDictionary dictionaryWithObject:[GTLImageAppError class]
                                forKey:@"appErrors"];
  return map;
}

@end