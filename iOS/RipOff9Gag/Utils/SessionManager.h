//
//  SessionManager.h
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/3/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

static NSString *const UDSessionUserId = @"UDSessionUserId";
static NSString *const UDSessionUserToken = @"UDSessionUserToken";
static NSString *const UDSessionUserName = @"UDSessionUserName";
static NSString *const UDSessionUserImageUrl = @"UDSessionUserImageUrl";

@interface SessionManager : NSObject

@property (strong, nonatomic, readonly) NSString *userId;
@property (strong, nonatomic, readonly) NSString *userToken;

+ (SessionManager *)sharedInstance;

- (BOOL)hasSession;
- (void)startSessionForUser:(NSString *)userId withToken:(NSString *)userToken;

//clear all user-specific data from user defaults, especially useful for logout
- (void)endSessionForUser;

- (void)put:(NSString *)value withKey:(NSString *)key;
- (NSString *)get:(NSString *)key;

@end
