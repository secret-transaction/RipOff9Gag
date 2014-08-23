//
//  UserRegistrationTableViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 7/30/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "UserRegistrationViewController.h"

@interface UserRegistrationViewController ()

@property (weak, nonatomic) IBOutlet UITextField *firstName;
@property (weak, nonatomic) IBOutlet UITextField *lastName;
@property (weak, nonatomic) IBOutlet UITextField *email;
@property (weak, nonatomic) IBOutlet UITextField *password;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *doneButton;

@end

@implementation UserRegistrationViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    NSNotificationCenter *notif = [NSNotificationCenter defaultCenter];
    [notif addObserver:self selector:@selector(revalidateRegistration) name:UITextFieldTextDidChangeNotification object:self.firstName];
    [notif addObserver:self selector:@selector(revalidateRegistration) name:UITextFieldTextDidChangeNotification object:self.lastName];
    [notif addObserver:self selector:@selector(revalidateRegistration) name:UITextFieldTextDidChangeNotification object:self.email];
    [notif addObserver:self selector:@selector(revalidateRegistration) name:UITextFieldTextDidChangeNotification object:self.password];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
}

- (IBAction)revalidateRegistration {
    NSLog(@"Validating...");
    BOOL valid = YES;
    
    if (self.firstName.text.length == 0) {
        valid = NO;
    }
    
    if (self.lastName.text.length == 0) {
        valid = NO;
    }
    
    if (self.email.text.length == 0) {
        valid = NO;
    }
    
    if (self.password.text.length == 0) {
        valid = NO;
    }
    
    //TODO: perform clever validation for fields
    
    self.doneButton.enabled = valid;
}

- (IBAction)done:(id)sender {
    self.doneButton.enabled = NO;
    GTLServiceUser *userService = [GTLServiceUser new];
    
    GTLUserRegistrationRequest *request = [GTLUserRegistrationRequest new];
    request.firstName = self.firstName.text;
    request.lastName = self.lastName.text;
    request.email = self.email.text;
    request.password = self.password.text;
    NSLog(@"Request:%@", request);
    
    GTLQueryUser *query = [GTLQueryUser queryForCreateWithObject:request];
    
    [userService executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLUserRegistrationResponse *response, NSError *error) {
        NSLog(@"done:%@", response.userId);
        [self.presentingViewController dismissViewControllerAnimated:YES completion:nil];
    }];
}

@end
