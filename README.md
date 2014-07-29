RipOff9Gag
=========

A 9Gag Ripoff App

> I got bored/uninspired and I just want to brush up my iOS and Google App Engine skills.
> I can't think of anything easy to build so I just decided to mimic what 9Gag does.

> Plans to send this to Google Play and Apple Appstore is still under consideration.

Modules
--------------

* [iOS Client]
* [Android Client]
* [Python Server]

Server Technologies
--------------
* [Google App Engine] - backbone of the application logic
* [NDB API] - database for non-image user data (uses GAE Datastore under the hood)
* [Google Cloud Storage] - for storing user-provided image files

Main Features
--------------
* View Funny Pictures
* Post Funny Pictures

Instructions for Generating Client Library:
--------------
***
iOS:
--------------

Sources: 
* [Generate API Discovery Docs]
* [Sample Code for Endpoints]

1. Go to Application Module Root
```sh
server > ripoff9gag
```

2. Generate Discovery Doc 
* for local:
```sh
endpointscfg.py get_discovery_doc --format rpc --application server/ripoff9gag --hostname "localhost:8080" --output endpoints \
    api.ReactionAPI \
	api.PostsAPI \
	api.UserAPI
````
* for production server:
```sh
endpointscfg.py get_discovery_doc --format rpc --application server/ripoff9gag --hostname "rogag-server.appspot.com" --output endpoints \
    api.ReactionAPI \
	api.PostsAPI \
	api.UserAPI
```

3. Generate Client Library 

```sh
/Users/lbibera/Library/Developer/Xcode/DerivedData/ServiceGenerator-auogvovcdkjnxnblqocbhybotoxv/Build/Products/Debug/ServiceGenerator \
    endpoints/post-v1.discovery \
    --outputDir iOS/RipOff9Gag/RemoteClient/API/Post
	
/Users/lbibera/Library/Developer/Xcode/DerivedData/ServiceGenerator-auogvovcdkjnxnblqocbhybotoxv/Build/Products/Debug/ServiceGenerator \
    endpoints/reaction-v1.discovery \
    --outputDir iOS/RipOff9Gag/RemoteClient/API/Reaction
	
/Users/lbibera/Library/Developer/Xcode/DerivedData/ServiceGenerator-auogvovcdkjnxnblqocbhybotoxv/Build/Products/Debug/ServiceGenerator \
    endpoints/user-v1.discovery \
    --outputDir iOS/RipOff9Gag/RemoteClient/API/User
```

4. Delete the Generated **_Sources.m files (VERY IMPORTANT)

***

Android:
--------------




[Google App Engine]:https://developers.google.com/appengine/docs/python/
[NDB API]:https://developers.google.com/appengine/docs/python/ndb/
[Google Cloud Storage]:https://developers.google.com/appengine/docs/python/googlecloudstorageclient/
[iOS Client]:https://github.com/secret-transaction/RipOff9Gag/tree/master/iOS
[Android Client]:https://github.com/secret-transaction/RipOff9Gag/tree/master/Android
[Python Server]:https://github.com/secret-transaction/RipOff9Gag/tree/master/server
[Generate API Discovery Docs]:https://developers.google.com/appengine/docs/python/endpoints/endpoints_tool#generating_a_discovery_doc
[Sample Code for Endpoints]:https://github.com/GoogleCloudPlatform/appengine-endpoints-helloendpoints-python