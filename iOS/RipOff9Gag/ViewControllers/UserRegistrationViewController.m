//
//  UserRegistrationTableViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 7/30/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "UserRegistrationViewController.h"

@interface UserRegistrationViewController ()
@property (weak, nonatomic) IBOutlet UISwitch *agreeSwitch;
@property (weak, nonatomic) IBOutlet UITextField *fullName;
@property (weak, nonatomic) IBOutlet UITextField *email;
@property (weak, nonatomic) IBOutlet UITextField *password;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *doneButton;

@end

@implementation UserRegistrationViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
}

- (IBAction)revalidateRegistration {
    BOOL valid = NO;
    
    if (self.agreeSwitch.isOn) {
        valid = YES;
    }
    
    //TODO: perform clever validation for fields
    
    self.doneButton.enabled = valid;
}

- (IBAction)done:(id)sender {
    self.doneButton.enabled = NO;
    GTLServiceUser *userService = [GTLServiceUser new];
    
    GTLUserApiUserUserRegistrationRequest *request = [GTLUserApiUserUserRegistrationRequest new];
    request.fullName = self.fullName.text;
    request.email = self.email.text;
    request.password = self.password.text;
    NSLog(@"Request:%@", request);
    
    GTLQueryUser *query = [GTLQueryUser queryForCreateWithObject:request];
    
    [userService executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLUserApiUserUserRegistrationResponse *response, NSError *error) {
        NSLog(@"done:%@", response.userId);
        [self.presentingViewController dismissViewControllerAnimated:YES completion:nil];
    }];
}

@end
