//
//  AddPostViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/9/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "AddPostViewController.h"

static NSInteger const PickerCamera = 0;
static NSInteger const PickerGallery = 1;

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
    self.description.delegate = self;
}

- (IBAction)changePhoto:(UITapGestureRecognizer *)sender
{
    NSLog(@"Change Picture");
    
    UIActionSheet *actionSheet = [[UIActionSheet alloc] initWithTitle:@"Choose an Image" delegate:self cancelButtonTitle:@"Cancel" destructiveButtonTitle:nil otherButtonTitles:@"Camera", @"Gallery", nil];
    
    //to make "Cancel" work
    //http://stackoverflow.com/questions/6699909/ios-uiactionsheet-cancel-button-doesnt-work-right
    //instead of
    //[actionSheet showInView:self.view];
    [actionSheet showFromTabBar:self.tabBarController.tabBar];
}

- (IBAction)upload:(id)sender
{
    NSLog(@"Upload Post");
}

- (void)textViewDidChange:(UITextView *)textView
{
    NSLog(@"textViewDidChange:");
    [self validate];
}

- (void)validate
{
    //TODO: perform validation for image
    BOOL validPicture = YES;

    BOOL validDescription = self.description.text.length > 0;
    
    self.uploadButton.enabled = validDescription && validPicture;
}

#pragma mark - UIActionSheetDelegate

- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex
{
    NSLog(@"You have pressed the %@ button", [actionSheet buttonTitleAtIndex:buttonIndex]);

    switch (buttonIndex) {
        case PickerCamera:
            [self startImagePicker:UIImagePickerControllerSourceTypeCamera];
            break;
        case PickerGallery:
            [self startImagePicker:UIImagePickerControllerSourceTypePhotoLibrary];
        default:
            break;
    }

}

- (void)startImagePicker:(UIImagePickerControllerSourceType)sourceType
{
    UIImagePickerController *picker = [[UIImagePickerController alloc] init];
    picker.sourceType = sourceType;
    picker.allowsEditing = YES;
    picker.delegate = self;
    [self presentViewController:picker animated:YES completion:nil];
    
}

- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info
{
    //TODO: store the image url somewhere
    //NSURL *urlPath = [info valueForKey:UIImagePickerControllerReferenceURL];
    //NSURLRequest *requestObj = [NSURLRequest requestWithURL:urlPath];
    NSLog(@"Picked Image");
    UIImage *chosenImage = info[UIImagePickerControllerEditedImage];
    
    if (self.image) {
        self.image.image = chosenImage;
    }
    
    [picker dismissViewControllerAnimated:YES completion:NULL];
}

@end
