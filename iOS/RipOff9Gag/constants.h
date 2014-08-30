//
//  constants.h
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 7/31/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#ifndef RipOff9Gag_constants_h
#define RipOff9Gag_constants_h

//table view cells id
static NSString *const kTVCUserPost = @"TVCUserPost";
static NSString *const kTVCEmptyPost = @"TVCEmptyPost";

//entity names
static NSString *const kEntityFunnyPost = @"FunnyPost";

//segues Segue<VCName without "ViewController">
static NSString *const kSegueUserRegistration = @"SegueUserRegistration";
static NSString *const kSegueUserProfileDisplay = @"SegueUserProfileDisplay";
static NSString *const kSegueAddPost = @"SegueAddPost";

//unwind segues UnwindSegue<VCName without "ViewController">
static NSString *const kUnwindSegueUserProfile = @"UnwindSegueUserProfile";
static NSString *const kUnwindSeguePostTable = @"UnwindSeguePostTable";

#endif
