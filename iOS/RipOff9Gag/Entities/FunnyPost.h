//
//  FunnyPost.h
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/30/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>


@interface FunnyPost : NSManagedObject

@property (nonatomic, retain) NSNumber * commentCount;
@property (nonatomic, retain) NSDate * dateCreated;
@property (nonatomic, retain) NSString * imageUrl;
@property (nonatomic, retain) NSNumber * isDownvoted;
@property (nonatomic, retain) NSNumber * isUnsafe;
@property (nonatomic, retain) NSNumber * isUpvoted;
@property (nonatomic, retain) NSNumber * pointsCount;
@property (nonatomic, retain) NSString * postId;
@property (nonatomic, retain) NSString * title;
@property (nonatomic, retain) NSString * unsafeImageUrl;

@end
