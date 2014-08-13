//
//  UserPost.h
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/14/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>


@interface UserPost : NSManagedObject

@property (nonatomic, retain) NSString * title;
@property (nonatomic, retain) NSString * imageUrl;
@property (nonatomic, retain) NSNumber * commentCount;
@property (nonatomic, retain) NSNumber * pointsCount;
@property (nonatomic, retain) NSDate * dateCreated;

@end
