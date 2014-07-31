//
//  UserProfileTableViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 7/31/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "UserProfileTableViewController.h"

@interface UserProfileTableViewController ()

@end

@implementation UserProfileTableViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
}

#pragma mark - Navigation

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
}

- (IBAction)unwindToUserProfileVC:(UIStoryboardSegue *)segue
{
    NSString *segueId = segue.identifier;
    NSLog(@"Unwind Segue:%@", segueId);
}

@end
