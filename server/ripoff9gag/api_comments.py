import endpoints
import logging
from api_common import *
from protorpc import messages
from protorpc import remote

'''
Endpoint DTOs
'''


class UserComment(messages.Message):
    commentId = messages.StringField(1)
    postId = messages.StringField(2)
    points = messages.IntegerField(3)
    message = messages.StringField(4)
    owner = messages.MessageField(UserAccount, 5, repeated=False)


class CommentListRequest(messages.Message):
    cursor = messages.StringField(1)


class CommentListResponse(messages.Message):
    cursor = messages.StringField(1)


class CommentCreateRequest(messages.Message):
    auth = messages.MessageField(UserAuthentication, 1, required=True)
    postId = messages.StringField(2, required=True)
    comment = messages.StringField(3, required=True)


class CommentCreateResponse(messages.Message):
    commentId = messages.StringField(1)


class VoteRequest(messages.Message):
    auth = messages.MessageField(UserAuthentication, 1, required=True)
    postId = messages.StringField(2, required=True)
    isUpVote = messages.BooleanField(3, required=True)


class VoteResponse(messages.Message):
    isSuccessful = messages.BooleanField(1)


'''
Endpoint APIs
'''


@endpoints.api(name='reaction', version='v1',
               description='Rogag API for Commenting, UpVoting and DownVoting Funny Posts')
class ReactionAPI(remote.Service):

    @endpoints.method(CommentListRequest, CommentListResponse, path='list', http_method='GET', name='comment.list')
    def list_comment(self, request):
        logging.info('listing comments with cursor:' + request.cursor)
        return CommentListResponse(cursor='test cursor')

    @endpoints.method(CommentCreateRequest, CommentCreateResponse, path='create', http_method='POST',
                      name='comment.create')
    def create_comment(self, request):
        logging.info('creating comment ' + request.title)

        return CommentCreateResponse(commentId='1')

    @endpoints.method(VoteRequest, VoteResponse, path='vote', http_method='PUT', name='vote')
    def vote(self, request):
        logging.info('voted for:' + request.postId + ' [' + str(request.isUpVote) + ']')
        return VoteResponse(isSuccessful=True)
