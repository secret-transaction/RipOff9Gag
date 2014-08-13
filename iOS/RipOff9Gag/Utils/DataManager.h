//
//  STRDataManager.h
//  Report
//
//  Created by Lyndon Michael Bibera on 7/14/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

//use this stuff as a guide
@interface DataManager : NSObject

@property (readonly, strong, nonatomic) NSManagedObjectContext *managedObjectContext;
@property (readonly, strong, nonatomic) NSManagedObjectModel *managedObjectModel;
@property (readonly, strong, nonatomic) NSPersistentStoreCoordinator *persistentStoreCoordinator;

+ (DataManager*)sharedInstance;

- (void)saveContext;
- (NSURL *)applicationDocumentsDirectory;

@end
