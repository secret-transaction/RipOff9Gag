import endpoints
import logging
from api_common import *
from protorpc import messages
from protorpc import remote

'''
Endpoint DTOs
'''


class UserPostListType(messages.Enum):
    TRENDING = 1
    HOT = 2


class UserPost(messages.Message):
    postId = messages.StringField(1)
    points = messages.IntegerField(2)
    commentCount = messages.IntegerField(3)
    upVoted = messages.BooleanField(4)
    downVoted = messages.BooleanField(5)
    isUnsafe = messages.BooleanField(6)
    title = messages.StringField(7)
    imageUrl = messages.StringField(8)
    imageType = messages.StringField(9)
    owner = messages.MessageField(UserAccount, 10)


class UserPostListResponse(messages.Message):
    cursor = messages.StringField(1)
    posts = messages.MessageField(UserPost, 2, repeated=True)


class UserPostListRequest(messages.Message):
    cursor = messages.StringField(1)
    type = messages.EnumField(UserPostListType, 2, required=True)


class UserPostCreateRequest(messages.Message):
    auth = messages.MessageField(UserAuthentication, 1)
    title = messages.StringField(2, required=True)
    imageUrl = messages.StringField(3, required=True)
    isUnsafe = messages.BooleanField(4)


class UserPostCreateResponse(messages.Message):
    postId = messages.StringField(1, required=True)


'''
Endpoint APIs
'''


@endpoints.api(name='post', version='v1', description='Rogag API for Viewing and Posting Funny Pics')
class PostsAPI(remote.Service):

    @endpoints.method(UserPostListRequest, UserPostListResponse, path='list', http_method='GET', name='list')
    def list_posts(self, request):
        logging.info('listing ' + str(request.type))

        user1 = UserAccount(userId='1', username='test1')
        post1 = UserPost(owner=user1, points=1)

        user2 = UserAccount(userId='2', username='test2')
        post2 = UserPost(owner=user2, points=1)

        post_list = UserPostListResponse(cursor='test')
        post_list.posts = [post1, post2]
        return post_list

    #guide for posts: https://developers.google.com/appengine/docs/python/endpoints/getstarted/backend/write_api_post
    @endpoints.method(UserPostCreateRequest, UserPostCreateResponse, path='create', http_method='POST', name='create')
    def create_post(self, request):
        logging.info('creating ' + request.title)

        return UserPostCreateResponse(postId='1')