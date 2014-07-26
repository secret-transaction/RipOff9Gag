import endpoints
from api_user import UserAPI
from api_posts import PostsAPI


APPLICATION = endpoints.api_server([PostsAPI, UserAPI])