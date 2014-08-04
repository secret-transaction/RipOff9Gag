echo "Starting Script..."
echo "Generating Discovery Files..."

endpointscfg.py get_discovery_doc --format rpc --application server/ripoff9gag --hostname "localhost:8080" --output endpoints api.ReactionAPI api.PostsAPI api.UserAPI

echo "Generating iOS Library..."

/Users/lbibera/Library/Developer/Xcode/DerivedData/ServiceGenerator-auogvovcdkjnxnblqocbhybotoxv/Build/Products/Debug/ServiceGenerator \
    endpoints/post-v1.discovery \
    --outputDir endpoints/API/Post    
/Users/lbibera/Library/Developer/Xcode/DerivedData/ServiceGenerator-auogvovcdkjnxnblqocbhybotoxv/Build/Products/Debug/ServiceGenerator \
    endpoints/reaction-v1.discovery \
    --outputDir endpoints/API/Reaction  
/Users/lbibera/Library/Developer/Xcode/DerivedData/ServiceGenerator-auogvovcdkjnxnblqocbhybotoxv/Build/Products/Debug/ServiceGenerator \
    endpoints/user-v1.discovery \
    --outputDir endpoints/API/User

echo "Deleting Useless Files..."

rm endpoints/API/**/*_Sources.m

echo "Done"