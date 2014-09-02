//
//  PostTableViewCell.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/13/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "PostTableViewCell.h"

@implementation PostTableViewCell


- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];
}

- (IBAction)vote:(UIButton *)sender
{
    NSLog(@"voted: %@", sender == self.upVote ? @"UP" : @"DOWN");
}

- (IBAction)viewComments:(UIButton *)sender
{
    [self.viewController performSegueWithIdentifier:kSegueShowComments sender:self];
}
@end
