//
//  LoginViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/3/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "LoginViewController.h"
#import "SessionManager.h"

@interface LoginViewController ()
@property (weak, nonatomic) IBOutlet UITextField *username;
@property (weak, nonatomic) IBOutlet UITextField *password;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *done;

@end

@implementation LoginViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
}

- (IBAction)reEvaluateForm:(id)sender
{
    NSString *username = self.username.text;
    NSString *password = self.password.text;
    
    NSLog(@"Validating:%@,%@", username, password);
    
    BOOL isValid = NO;
    if (username!=nil && password!=nil) {
        isValid = YES;
    }
    
    self.done.enabled = isValid;
}

- (IBAction)login:(id)sender
{
    self.done.enabled = NO;
    GTLServiceUser *service = [GTLServiceUser new];
    
    GTLUserApiUserUserLoginRequest *request = [GTLUserApiUserUserLoginRequest new];
    request.username = self.username.text;
    request.password = self.password.text;
    
    GTLQueryUser *query = [GTLQueryUser queryForLoginWithObject:request];
    
    [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLUserApiUserUserLoginResponse *response, NSError *error) {
        if (!error) {
            NSLog(@"Login Success:%@, %@", response.userId, response.userToken);
            
            SessionManager *sm = [SessionManager sharedInstance];
            [sm startSessionForUser:response.userId withToken:response.userToken];
        } else {
            NSLog(@"Login Failed:%@", error);
        }
        
        [self.presentingViewController dismissViewControllerAnimated:YES completion:nil];
    }];
}

@end
