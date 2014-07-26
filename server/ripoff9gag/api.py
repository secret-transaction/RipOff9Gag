import endpoints
import logging
from protorpc import messages
from protorpc import remote

package = 'rogag'


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
    userId = messages.StringField(1, required=True)
    userToken = messages.StringField(2, required=True)


class UserPostCreateRequest(messages.Message):
    auth = messages.MessageField(UserAuthentication, 1)
    title = messages.StringField(2, required=True)
    imageUrl = messages.StringField(3, required=True)
    is_unsafe = messages.BooleanField(4)


class UserPostCreateResponse(messages.Message):
    post_id = messages.StringField(1, required=True)


'''
Endpoint APIs
'''


@endpoints.api(name='rogag', version='v1', description='Rogag API')
class RipOff9GagAPI(remote.Service):

    @endpoints.method(UserPostListRequest, UserPostListResponse, path='posts.list', http_method='GET',
                      name='posts.list')
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
    @endpoints.method(UserPostCreateRequest, UserPostCreateResponse, path='post.create', http_method='POST',
                      name='post.create')
    def create_post(self, request):
        logging.info('creating ' + request.title)

        return UserPostCreateResponse(post_id='1')



APPLICATION = endpoints.api_server([RipOff9GagAPI])