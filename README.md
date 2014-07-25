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

[Google App Engine]:https://developers.google.com/appengine/docs/python/
[NDB API]:https://developers.google.com/appengine/docs/python/ndb/
[Google Cloud Storage]:https://developers.google.com/appengine/docs/python/googlecloudstorageclient/
[iOS Client]:https://github.com/secret-transaction/RipOff9Gag/tree/master/iOS
[Android Client]:https://github.com/secret-transaction/RipOff9Gag/tree/master/Android
[Python Server]:https://github.com/secret-transaction/RipOff9Gag/tree/master/server