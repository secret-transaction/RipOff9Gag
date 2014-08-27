//
//  AddPostViewController.m
//  RipOff9Gag
//
//  Created by Lyndon Michael Bibera on 8/9/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//

#import "AddPostViewController.h"
#import "GTLImage.h"
#import "SessionManager.h"
#import "GTLPost.h"
#import <AFNetworking/AFNetworking.h>

static NSInteger const PickerCamera = 0;
static NSInteger const PickerGallery = 1;

@interface AddPostViewController () 

@property (weak, nonatomic) IBOutlet UIImageView *image;
@property (weak, nonatomic) IBOutlet UITextView *description;
@property (weak, nonatomic) IBOutlet UILabel *characterCountDisplay;
@property (weak, nonatomic) IBOutlet UISwitch *isUnsafe;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *uploadButton;
@property (weak, nonatomic) IBOutlet UIActivityIndicatorView *activityIndicator;

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
    self.uploadButton.enabled = NO;
    NSLog(@"Retrieving Upload/Download Url...");
    [self.activityIndicator startAnimating];
    GTLServiceImage *imageService = [GTLServiceImage new];
    
    NSData *imageData = UIImagePNGRepresentation(self.image.image);
    NSString *fileName = @"myFile.png";
    NSString *mimeType = @"image/png";
    
    SessionManager *sm = [SessionManager sharedInstance];
    
    GTLImageUserAuthentication *auth = [GTLImageUserAuthentication new];
    auth.userId = sm.userId;
    auth.userToken = sm.userToken;
    
    GTLImageGetUrlRequest *request = [GTLImageGetUrlRequest new];
    request.auth = auth;
    request.fileName = fileName;
    request.imageType = mimeType;
    
    GTLQueryImage *query = [GTLQueryImage queryForGetUrlWithObject:request];
    
    [imageService executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLImageGetUrlResponse *response, NSError *error) {
        NSLog(@"Get URL Response: Upload:%@ Download:%@", response.uploadUrl, response.downloadUrl);
        
        AFHTTPRequestOperationManager *requestManager = [AFHTTPRequestOperationManager new];
        
        NSError *error2;
        NSURL *url = [[NSURL alloc] initWithString:response.uploadUrl];
        NSMutableURLRequest *uploadRequest = [requestManager.requestSerializer multipartFormRequestWithMethod:@"PUT" URLString:[url absoluteString] parameters:nil constructingBodyWithBlock:^(id<AFMultipartFormData> formData) {
            //[formData appendPartWithFileData:imageData name:@"myFile" fileName:fileName mimeType:mimeType];
        } error:&error2];
        
        [uploadRequest setHTTPBody:imageData];
        [uploadRequest setValue:mimeType forHTTPHeaderField:@"Content-Type"];
        [uploadRequest setValue:@"public-read" forHTTPHeaderField:@"x-goog-acl"];
        
        AFHTTPRequestOperation *operation = [requestManager HTTPRequestOperationWithRequest:uploadRequest success:^(AFHTTPRequestOperation *operation, NSHTTPURLResponse *resp) {
            NSLog(@"Upload Success: %@", operation);
            
            
            GTLServicePost *postService = [GTLServicePost new];
            
            GTLPostUserAuthentication *postAuth = [GTLPostUserAuthentication new];
            postAuth.userId = sm.userId;
            postAuth.userToken = sm.userToken;
            
            GTLPostUserPostCreateRequest *postCreateRequest = [GTLPostUserPostCreateRequest new];
            postCreateRequest.auth = postAuth;
            postCreateRequest.imageUrl = response.downloadUrl;
            postCreateRequest.title = self.description.text;
            postCreateRequest.isUnsafe = self.isUnsafe.enabled ? @1 : @0;
            
            GTLQueryPost *postCreateQuery = [GTLQueryPost queryForCreateWithObject:postCreateRequest];
            
            [postService executeQuery:postCreateQuery completionHandler:^(GTLServiceTicket *ticket, id object, NSError *error) {
                //respond on success
                self.uploadButton.enabled = YES;
                [self.activityIndicator stopAnimating];
            }];
            
        } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
            NSLog(@"Upload Failed");
        }];
        
        [requestManager.operationQueue addOperation:operation];
    }];
    
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
