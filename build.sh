echo "Starting Script..."
echo "Generating Discovery Files..."

domain="localhost:8080"
service_generator="/Users/lbibera/Library/Developer/Xcode/DerivedData/ServiceGenerator-auogvovcdkjnxnblqocbhybotoxv/Build/Products/Debug/ServiceGenerator"
output_dir="endpoints"

endpointscfg.py get_discovery_doc --format rpc --application server/ripoff9gag --hostname "$domain" --output endpoints api.ReactionAPI api.PostsAPI api.UserAPI

echo "Generating iOS Library..."

$service_generator \
    endpoints/post-v1.discovery \
    --outputDir $output_dir/API/Post    
$service_generator  \
    endpoints/reaction-v1.discovery \
    --outputDir $output_dir/API/Reaction  
$service_generator \
    endpoints/user-v1.discovery \
    --outputDir $output_dir/API/User

echo "Deleting Useless Files..."

rm endpoints/API/**/*_Sources.m

echo "Done"
