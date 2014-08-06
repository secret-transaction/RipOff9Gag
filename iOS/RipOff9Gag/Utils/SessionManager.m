//
//  SessionManager.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/3/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "SessionManager.h"

//private variables
@interface SessionManager()

@property NSMutableDictionary *props;

@end

@implementation SessionManager

@synthesize userId = _userId;
@synthesize userToken = _userToken;

+ (SessionManager *)sharedInstance
{
    static SessionManager *_sharedInstance = nil;
    
    static dispatch_once_t oncePredicate;
    
    dispatch_once(&oncePredicate, ^{
        _sharedInstance = [SessionManager new];
    });
    
    return _sharedInstance;
}

- (void)startSessionForUser:(NSString *)userId withToken:(NSString *)userToken
{
    NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
    [ud setObject:userId forKey:UDSessionUserId];
    [ud setObject:userToken forKey:UDSessionUserToken];
    
    [ud synchronize];
}

- (BOOL)hasSession
{
    NSLog(@"%@:%@", self.userId, self.userToken);
    return self.userId && self.userToken;
}

- (void)endSessionForUser
{
    NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
    [ud setObject:nil forKey:UDSessionUserId];
    [ud setObject:nil forKey:UDSessionUserToken];
    _userId = nil;
    _userToken = nil;
    
    [ud synchronize];
}

- (NSString *)userId
{
    if (!_userId) {
        NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
        _userId = [ud stringForKey:UDSessionUserId];
    }
    
    return _userId;
}

- (NSString *)userToken
{
    if (!_userToken) {
        NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
        _userToken = [ud stringForKey:UDSessionUserToken];
    }
    
    return _userToken;
}

- (void)put:(NSString *)value withKey:(NSString *)key;
{
    if (!self.props) {
        self.props = [NSMutableDictionary new];
    }
    NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
    [ud setObject:value forKey:key];
    [ud synchronize];

    [self.props setObject:value forKey:key];
}

- (NSString *)get:(NSString *)key
{
    if (!self.props) {
        self.props = [NSMutableDictionary new];
    }
    NSString *val = [self.props valueForKey:key];
    if (!val) {
        NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
        val = [ud valueForKey:key];
    }
    
    return val;
}

@end
