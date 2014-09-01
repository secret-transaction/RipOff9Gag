//
//  PostsTableViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 7/25/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "PostsTableViewController.h"
#import "DataManager.h"
#import "PostTableViewCell.h"
#import "FunnyPost.h"
#import "AsyncImageDownloader.h"

@interface PostsTableViewController ()

@property BOOL needsRefresh;
@property NSNumber *servertime;
@property (strong, nonatomic) UIBarButtonItem *addBarButtonItem;
@property (strong, nonatomic) UIBarButtonItem *refreshBarButtonItem;

@end

@implementation PostsTableViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    if (self.title) {
        self.navigationItem.title = self.title;
    }
    
    self.context =  [[DataManager sharedInstance] managedObjectContext];
    
    self.addBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(addPost)];
    self.refreshBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemRefresh target:self action:@selector(refreshTable)];
    
    self.navigationItem.rightBarButtonItems = [[NSArray alloc] initWithObjects:self.refreshBarButtonItem, self.addBarButtonItem, nil];
    
    //lame way to refresh: by code
    //[self.refreshControl addTarget:self action:@selector(refreshTable) forControlEvents:UIControlEventValueChanged];
}

- (IBAction)upvote:(id)sender
{
    NSLog(@"upvote");
    
}

- (IBAction)downvote:(id)sender
{
    NSLog(@"downvote");
}

- (IBAction)viewComments:(id)sender
{
    NSLog(@"view comments");
    [self performSegueWithIdentifier:kSegueShowComments sender:self];
}

- (void)addPost
{
    NSLog(@"Add Post");
    //TODO: perform check if user has logged in
    [self performSegueWithIdentifier:kSegueAddPost sender:self];
}

//cool way to refresh: using xcode
- (IBAction)pullDownRefresh
{
    NSLog(@"Pull down");
    [self refreshTable];
}

- (void)refreshTable
{
    NSLog(@"Refresh");
    [self startRefreshing];
    
    GTLServicePost *service = [GTLServicePost new];
    
    GTLQueryPost *query = [GTLQueryPost queryForListWithType:@"trending"];
    query.cursor = @"";
    query.pageSize = 10;
    query.sinceDate = self.servertime ? [self.servertime longLongValue] : 1;
    
    [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLPostUserPostListResponse *response, NSError *error) {
        if (!error) {
            NSLog(@"Listing Posts Success");
            self.servertime = response.serverTime;
            
            NSEntityDescription *entityDescription = [NSEntityDescription entityForName:kEntityFunnyPost inManagedObjectContext:self.context];
            NSFetchRequest *request = [NSFetchRequest new];
            [request setEntity:entityDescription];
            
            for (GTLPostUserPost *post in response.posts) {
                
                NSPredicate *predicate = [NSPredicate predicateWithFormat:@"postId = %@", post.postId];
                [request setPredicate:predicate];
                NSArray *arr = [self.context executeFetchRequest:request error:NULL];
                NSLog(@"Check if exists:%@", arr);
    
                NSLog(@"Got Post:%@", post.postId);
                FunnyPost *postEntity = arr != nil && [arr count] > 0  ? arr[0] : [NSEntityDescription insertNewObjectForEntityForName:kEntityFunnyPost inManagedObjectContext:self.context];
                postEntity.title = post.title;
                postEntity.imageUrl = post.imageUrl;
                postEntity.dateCreated = [[NSDate alloc] initWithTimeIntervalSince1970:[post.dateCreated doubleValue] /1000.0];
                postEntity.isDownvoted = post.downVoted;
                postEntity.isUnsafe = post.isUnsafe;
                postEntity.isUpvoted = post.upVoted;
                postEntity.pointsCount = post.points;
                postEntity.postId = post.postId;
                postEntity.unsafeImageUrl = post.unsafeImageUrl;
            }
            
            [[DataManager sharedInstance] saveContext];
            
            self.needsRefresh = YES;
            [self.tableView reloadData];
            [self endRefreshing];
        } else {
            NSLog(@"Fetch Failed:%@", error);
            [self endRefreshing];
        }
    }];
}

- (void)startRefreshing
{
    //[self.refreshControl.vis]
    [self.refreshControl beginRefreshing];
    self.refreshBarButtonItem.enabled = NO;
}

- (void)endRefreshing
{
    [self.refreshControl endRefreshing];
    self.refreshBarButtonItem.enabled = YES;
}

#pragma mark - Navigation
- (IBAction)unwindToPostTableViewController:(UIStoryboardSegue *)unwindSegue
{
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSUInteger sectionCount = [self.fetchedResultsController.sections count];

    if (sectionCount > 0) {
        NSUInteger objectCount = [self.fetchedResultsController.sections[section] numberOfObjects];
        return objectCount;
    } else {
        return 1;
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return [self.fetchedResultsController.sections count] > 0 ? 352.0 : 500;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSUInteger sectionCount = [self.fetchedResultsController.sections count];
    
    if (sectionCount > 0) {
        PostTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:kTVCUserPost forIndexPath:indexPath];
        
        FunnyPost *userPost = [self.fetchedResultsController objectAtIndexPath:indexPath];
        cell.titleLabel.text = userPost.title;
        cell.subtitleLabel.text = [NSString stringWithFormat:@"%@ points - %@ comments", userPost.pointsCount, userPost.commentCount];
        
        //TODO: store images in a cache (memory or at least in file)
        [AsyncImageDownloader loadFromURL:userPost.imageUrl toImageView:cell.image];
        NSLog(@"Image:%@", userPost.imageUrl);
        
        return cell;
    } else {
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:kTVCEmptyPost forIndexPath:indexPath];
        return cell;
    }
}

#pragma mark - Fetched Results Controller

- (NSFetchedResultsController *)fetchedResultsController
{
    if (_fetchedResultsController != nil && !self.needsRefresh) {
        self.needsRefresh = NO;
        return _fetchedResultsController;
    }
    
    NSFetchRequest *request = [NSFetchRequest new];
    [request setEntity:[NSEntityDescription entityForName:kEntityFunnyPost inManagedObjectContext:self.context]];
    NSSortDescriptor *sortDescriptor = [[NSSortDescriptor alloc] initWithKey:@"dateCreated" ascending:NO];
    request.sortDescriptors = @[sortDescriptor];
    
    NSFetchedResultsController *fetchedResultsController = [[NSFetchedResultsController alloc] initWithFetchRequest:request managedObjectContext:self.context sectionNameKeyPath:nil cacheName:@"Master"];
    
    NSError *error = nil;
    if (![fetchedResultsController performFetch:&error]) {
        NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
    } else {
        self.fetchedResultsController = fetchedResultsController;
    }
    
    return _fetchedResultsController;
}

@end
