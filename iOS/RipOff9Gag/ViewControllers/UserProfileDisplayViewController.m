//
//  UserProfileDisplayViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/3/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "UserProfileDisplayViewController.h"
#import "SessionManager.h"

@interface UserProfileDisplayViewController ()

@end

@implementation UserProfileDisplayViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self.navigationItem setHidesBackButton:YES];
}

- (void)viewWillAppear:(BOOL)animated
{
    SessionManager *sm = [SessionManager sharedInstance];
    if (![sm hasSession]) {
        [self.navigationController popViewControllerAnimated:YES];
    }
    
}

- (IBAction)logout:(id)sender
{
    [[SessionManager sharedInstance] endSessionForUser];
    self.navigationController.tabBarController.selectedIndex = 0;
}

@end
