/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLQueryImage.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   image/v1
// Description:
//   Rogag API for Images
// Classes:
//   GTLQueryImage (1 custom class methods, 1 custom properties)

#import "GTLQueryImage.h"

#import "GTLImageGetUrlRequest.h"
#import "GTLImageGetUrlResponse.h"

@implementation GTLQueryImage

@dynamic fields;

#pragma mark -
#pragma mark "get" methods
// These create a GTLQueryImage object.

+ (id)queryForGetUrlWithObject:(GTLImageGetUrlRequest *)object {
  if (object == nil) {
    GTL_DEBUG_ASSERT(object != nil, @"%@ got a nil object", NSStringFromSelector(_cmd));
    return nil;
  }
  NSString *methodName = @"image.get.url";
  GTLQueryImage *query = [self queryWithMethodName:methodName];
  query.bodyObject = object;
  query.expectedObjectClass = [GTLImageGetUrlResponse class];
  return query;
}

@end