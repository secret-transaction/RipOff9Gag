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
#import "UserPost.h"

@interface PostsTableViewController ()

@end

@implementation PostsTableViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    if (self.title) {
        self.navigationItem.title = self.title;
    }
    
    self.context =  [[DataManager sharedInstance] managedObjectContext];
    
    UIBarButtonItem *addBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(addPost)];
    UIBarButtonItem *refreshBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemRefresh target:self action:@selector(refreshTable)];
    
    self.navigationItem.rightBarButtonItems = [[NSArray alloc] initWithObjects:refreshBarButtonItem, addBarButtonItem, nil];
}

- (void)addPost
{
    NSLog(@"Add Post");
    //TODO: perform check if user has logged in
    [self performSegueWithIdentifier:kSegueAddPost sender:self];
}

- (void)refreshTable
{
    NSLog(@"Refresh");
    
    GTLServicePost *service = [GTLServicePost new];
    
    GTLQueryPost *query = [GTLQueryPost queryForListWithType:kGTLPostTypeHot];
    query.cursor = @"";
    query.pageSize = 10L;
    
    [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLPostApiPostsUserPostListResponse *response, NSError *error) {
        if (!error) {
            NSLog(@"Listing Posts Success");
            
            for (GTLPostApiCommonUserPost *post in response.posts) {
                NSLog(@"Got Post:%@", post.postId);
                UserPost *postEntity = [NSEntityDescription insertNewObjectForEntityForName:kEntityUserPost inManagedObjectContext:self.context];
                postEntity.title = post.title;
                postEntity.imageUrl = post.imageUrl;
            }
            
            [[DataManager sharedInstance] saveContext];
        } else {
            NSLog(@"Login Failed:%@", error);
        }
    }];

}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return [[self.fetchedResultsController sections] count];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    id <NSFetchedResultsSectionInfo> sectionInfo = [self.fetchedResultsController sections][section];
    return [sectionInfo numberOfObjects];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    PostTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:kTVCUserPost forIndexPath:indexPath];
    
    UserPost *userPost = [self.fetchedResultsController objectAtIndexPath:indexPath];
    cell.titleLabel.text = userPost.title;
    
    return cell;
}

#pragma mark - Fetched Results Controller

- (NSFetchedResultsController *)fetchedResultsController
{
    if (_fetchedResultsController != nil) {
        return _fetchedResultsController;
    }
    
    NSFetchRequest *request = [NSFetchRequest new];
    [request setEntity:[NSEntityDescription entityForName:kEntityUserPost inManagedObjectContext:self.context]];
    NSSortDescriptor *sortDescriptor = [[NSSortDescriptor alloc] initWithKey:@"dateCreated" ascending:NO];
    request.sortDescriptors = @[sortDescriptor];
    
    NSFetchedResultsController *fetchedResultsController = [[NSFetchedResultsController alloc] initWithFetchRequest:request managedObjectContext:self.context sectionNameKeyPath:@"title" cacheName:@"Master"];
    
    NSError *error = nil;
    if (![fetchedResultsController performFetch:&error]) {
        NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
    } else {
        self.fetchedResultsController = fetchedResultsController;
    }
    
    return _fetchedResultsController;
}

@end
