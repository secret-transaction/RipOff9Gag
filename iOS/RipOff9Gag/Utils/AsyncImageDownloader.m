//
//  STRAsyncImageDownloader.m
//  Report
//
//  Created by Lyndon Michael Bibera on 7/17/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//
//  code copied from KyleBanks (https://github.com/KyleBanks/AsyncImageDownloader/tree/master/iOS)

#import "AsyncImageDownloader.h"

@implementation AsyncImageDownloader

@synthesize mediaURL, fileURL;

+(void)loadFromURL:(NSString *)imageUrl toImageView:(UIImageView *)imageView
{
    AsyncImageDownloader *asyncImageDLM = [[AsyncImageDownloader alloc] initWithMediaURL:imageUrl successBlock:^(UIImage *image) {
        NSLog(@"Image Download Succeeded");
        imageView.image = image;
    } failBlock:^(NSError *error) {
        //TODO: hanle failure
        NSLog(@"Image Download Failed");
    }];
    
    [asyncImageDLM startDownload];

}

-(id)initWithMediaURL:(NSString *)theMediaURL successBlock:(void (^)(UIImage *image))success failBlock:(void(^)(NSError *error))fail
{
    self = [super init];
    
    if(self)
    {
        [self setMediaURL:theMediaURL];
        [self setFileURL:nil];
        successCallback = success;
        failCallback = fail;
    }
    
    return self;
}
-(id)initWithFileURL:(NSString *)theFileURL successBlock:(void (^)(NSData *data))success failBlock:(void(^)(NSError *error))fail
{
    self = [super init];
    
    if(self)
    {
        [self setMediaURL:nil];
        [self setFileURL:theFileURL];
        successCallbackFile = success;
        failCallback = fail;
    }
    
    return self;
}

//Perform the actual download
-(void)startDownload
{
    fileData = [[NSMutableData alloc] init];
    
    NSURLRequest *request = nil;
    if (fileURL)
        request = [NSURLRequest requestWithURL:[NSURL URLWithString:fileURL]];
    else
        request = [NSURLRequest requestWithURL:[NSURL URLWithString:mediaURL]];
    
    NSURLConnection *connection = [[NSURLConnection alloc] initWithRequest:request delegate:self startImmediately:YES];
    if(!connection)
    {
        failCallback([NSError errorWithDomain:@"Failed to create connection" code:0 userInfo:nil]);
    }
}

#pragma mark NSURLConnection Delegate
-(void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{
    failCallback(error);
}
-(void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response
{
    if([response respondsToSelector:@selector(statusCode)])
    {
        int statusCode = [((NSHTTPURLResponse *)response) statusCode];
        if (statusCode >= 400)
        {
            [connection cancel];
            failCallback([NSError errorWithDomain:@"Image download failed due to bad server response" code:0 userInfo:nil]);
        }
    }
}
-(void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    [fileData appendData:data];
}
-(void)connectionDidFinishLoading:(NSURLConnection *)connection
{
    
    if(fileData == nil)
    {
        failCallback([NSError errorWithDomain:@"No data received" code:0 userInfo:nil]);
    }
    else
    {
        if (fileURL) {
            successCallbackFile(fileData);
        } else {
            UIImage *image = [UIImage imageWithData:fileData];
            successCallback(image);
        }
    }
}


@end
