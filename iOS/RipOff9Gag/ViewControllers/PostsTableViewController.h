//
//  PostsTableViewController.h
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 7/25/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PostsTableViewController : UITableViewController

@property (strong, nonatomic) NSString *title;
@property (strong, nonatomic) NSManagedObjectContext *context;
@property (strong, nonatomic) NSFetchedResultsController *fetchedResultsController;

@end
