/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLQueryImage.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   image/v1
// Description:
//   Rogag API for Images
// Classes:
//   GTLQueryImage (1 custom class methods, 1 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLQuery.h"
#else
  #import "GTLQuery.h"
#endif

@class GTLImageGetUrlRequest;

@interface GTLQueryImage : GTLQuery

//
// Parameters valid on all methods.
//

// Selector specifying which fields to include in a partial response.
@property (copy) NSString *fields;

#pragma mark -
#pragma mark "get" methods
// These create a GTLQueryImage object.

// Method: image.get.url
//  Authorization scope(s):
//   kGTLAuthScopeImageUserinfoEmail
// Fetches a GTLImageGetUrlResponse.
+ (id)queryForGetUrlWithObject:(GTLImageGetUrlRequest *)object;

@end
