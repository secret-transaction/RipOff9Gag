import endpoints
import logging
from protorpc import messages
from protorpc import remote

'''
Endpoint DTOs
'''


class UserPostListType(messages.Enum):
    TRENDING = 1
    HOT = 2


class UserAccount(messages.Message):
    user_id = messages.StringField(1, required=True)
    username = messages.StringField(2, required=True)


class UserPost(messages.Message):
    post_id = messages.StringField(1)
    points = messages.IntegerField(2)
    comment_count = messages.IntegerField(3)
    up_voted = messages.BooleanField(4)
    down_voted = messages.BooleanField(5)
    unsafe = messages.BooleanField(6)
    title = messages.StringField(7)
    image_url = messages.StringField(8)
    image_type = messages.StringField(9)
    owner = messages.MessageField(UserAccount, 10)


class UserComment(messages.Message):
    comment_id = messages.StringField(1)
    points = messages.IntegerField(2)
    message = messages.StringField(3)
    owner = messages.MessageField(UserAccount, 4, repeated=False)


class UserPostListResponse(messages.Message):
    cursor = messages.StringField(1)
    posts = messages.MessageField(UserPost, 2, repeated=True)


class UserPostListRequest(messages.Message):
    cursor = messages.StringField(1)
    type = messages.EnumField(UserPostListType, 2, required=True)


class UserAuthentication(messages.Message):
    user_id = messages.StringField(1, required=True)
    user_token = messages.StringField(2, required=True)


class UserPostCreateRequest(messages.Message):
    auth = messages.MessageField(UserAuthentication, 1)
    title = messages.StringField(2, required=True)
    image_url = messages.StringField(3, required=True)
    is_unsafe = messages.BooleanField(4)


class UserPostCreateResponse(messages.Message):
    post_id = messages.StringField(1, required=True)


'''
Endpoint APIs
'''


@endpoints.api(name='post', version='v1', description='Rogag API for Viewing and Posting Funny Pics')
class PostsAPI(remote.Service):

    @endpoints.method(UserPostListRequest, UserPostListResponse, path='list', http_method='GET',
                      name='listPosts')
    def list_posts(self, request):
        logging.info('listing ' + str(request.type))

        user1 = UserAccount(user_id='1', username='test1')
        post1 = UserPost(owner=user1, points=1)

        user2 = UserAccount(user_id='2', username='test2')
        post2 = UserPost(owner=user2, points=1)

        post_list = UserPostListResponse(cursor='test')
        post_list.posts = [post1, post2]
        return post_list

    #guide for posts: https://developers.google.com/appengine/docs/python/endpoints/getstarted/backend/write_api_post
    @endpoints.method(UserPostCreateRequest, UserPostCreateResponse, path='create', http_method='POST',
                      name='createPost')
    def create_post(self, request):
        logging.info('creating ' + request.title)

        return UserPostCreateResponse(post_id='1')