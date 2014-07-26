import endpoints
from api_user import UserAPI
from api_posts import PostsAPI
from api_comments import ReactionAPI


APPLICATION = endpoints.api_server([PostsAPI, ReactionAPI, UserAPI])