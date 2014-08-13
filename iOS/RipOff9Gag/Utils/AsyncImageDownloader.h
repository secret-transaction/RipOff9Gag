//
//  STRAsyncImageDownloader.h
//  Report
//
//  Created by Lyndon Michael Bibera on 7/17/14.
//  Copyright (c) 2014 Secret Transaction Inc. All rights reserved.
//
//  code copied from KyleBanks (https://github.com/KyleBanks/AsyncImageDownloader/tree/master/iOS)

#import <Foundation/Foundation.h>

@interface AsyncImageDownloader : NSObject
{
    NSMutableData *fileData;

    //Callback blocks
    void (^successCallbackFile)(NSData *data);
    void (^successCallback)(UIImage *image);
    void (^failCallback)(NSError *error);
}

@property NSString *mediaURL;
@property NSString *fileURL;

+(void)loadFromURL:(NSString *)imageUrl toImageView:(UIImageView *)imageView;

-(id)initWithMediaURL:(NSString *)theMediaURL successBlock:(void (^)(UIImage *image))success failBlock:(void(^)(NSError *error))fail;

-(id)initWithFileURL:(NSString *)theFileURL successBlock:(void (^)(NSData *data))success failBlock:(void(^)(NSError *error))fail;

-(void)startDownload;

@end
