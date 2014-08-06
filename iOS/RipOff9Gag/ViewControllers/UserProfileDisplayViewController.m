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
@property (weak, nonatomic) IBOutlet UIImageView *profileImage;
@property (weak, nonatomic) IBOutlet UILabel *profileName;

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
    
    if ([sm hasSession]) {
        [self setViewFromSession];
    } else {
        [self.navigationController popViewControllerAnimated:YES];
    }
    
}

- (void)setViewFromSession
{
    SessionManager *sm = [SessionManager sharedInstance];
    self.profileName.text = [sm get:UDSessionUserName];
    
    //TODO: probably need to run this on a separate thread
    //set profile image
    NSURL *url = [NSURL URLWithString:[sm get:UDSessionUserImageUrl]];
    NSData *data = [NSData dataWithContentsOfURL:url];
    UIImage *image = [UIImage imageWithData:data];
    
    self.profileImage.image = image;
}

- (IBAction)logout:(id)sender
{
    [[SessionManager sharedInstance] endSessionForUser];
    self.navigationController.tabBarController.selectedIndex = 0;
}

@end
