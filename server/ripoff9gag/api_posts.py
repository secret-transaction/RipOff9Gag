import endpoints
import logging
from api_common import *
from random import *
from protorpc import remote

'''
Endpoint DTOs
'''


class UserPostListResponse(messages.Message):
    cursor = messages.StringField(1)
    posts = messages.MessageField(UserPost, 2, repeated=True)


class UserPostListRequest(messages.Message):
    cursor = messages.StringField(1)
    pageSize = messages.IntegerField(2)
    type = messages.EnumField(UserPostListType, 3, required=True)


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
        time = 1
        post_list = []

        for i in range(0, request.pageSize):
            post = UserPost(postId=str(time+i), owner=user1, points=1)

            # https://docs.python.org/2/library/random.html
            post.imageUrl = 'https://rogag-server.appspot.com/temp/images/image' + str(randint(1, 9)) + '.jpg'
            post.commentCount = randint(0, 500)
            post.isUnsafe = bool(getrandbits(1))
            post.title = 'Some title ' + str(randint(0, 999))
            post_list.append(post)

        response = UserPostListResponse(cursor='test')
        response.posts = post_list
        return response

    #guide for posts: https://developers.google.com/appengine/docs/python/endpoints/getstarted/backend/write_api_post
    @endpoints.method(UserPostCreateRequest, UserPostCreateResponse, path='create', http_method='POST', name='create')
    def create_post(self, request):
        logging.info('creating ' + request.title)

        return UserPostCreateResponse(postId='1')