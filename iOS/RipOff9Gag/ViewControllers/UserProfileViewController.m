//
//  UserProfileTableViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 7/31/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "UserProfileViewController.h"
#import "SessionManager.h"

@interface UserProfileViewController ()

@end

@implementation UserProfileViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
}

- (void)viewDidAppear:(BOOL)animated
{
    SessionManager *sm = [SessionManager sharedInstance];
    if ([sm hasSession]) {
        NSLog(@"User Already Logged In...");
        [self performSegueWithIdentifier:SegueUserProfileDisplay sender:self];
    }

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
