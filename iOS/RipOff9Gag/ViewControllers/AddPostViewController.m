//
//  AddPostViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/9/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "AddPostViewController.h"

@interface AddPostViewController ()

@property (weak, nonatomic) IBOutlet UIImageView *image;
@property (weak, nonatomic) IBOutlet UITextView *description;
@property (weak, nonatomic) IBOutlet UILabel *characterCountDisplay;
@property (weak, nonatomic) IBOutlet UISwitch *isUnsafe;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *uploadButton;

@end

@implementation AddPostViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (IBAction)changePhoto:(UITapGestureRecognizer *)sender
{
    NSLog(@"Change Picture");
}

- (IBAction)upload:(id)sender
{
    NSLog(@"Upload Post");
}


@end
