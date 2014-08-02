import endpoints
import logging
from protorpc import messages
from protorpc import remote
from models import RogagUser


'''
Endpoint DTOs
'''


class UserRegistrationRequest(messages.Message):
    email = messages.StringField(1)
    fullName = messages.StringField(2)
    password = messages.StringField(3)


class UserRegistrationResponse(messages.Message):
    userId = messages.StringField(1)


class UserLoginRequest(messages.Message):
    username = messages.StringField(1, required=True)
    password = messages.StringField(2, required=True)


class UserLoginResponse(messages.Message):
    userId = messages.StringField(1, required=True)
    username = messages.StringField(2, required=True)
    userToken = messages.StringField(3, required=True)

'''
Endpoint APIs
'''


@endpoints.api(name='user', version='v1', description='Rogag API for User Management')
class UserAPI(remote.Service):

    @endpoints.method(UserRegistrationRequest, UserRegistrationResponse, path='create', http_method='POST',
                      name='create')
    def create_user(self, request):
        logging.info('registering:' + request.email)

        # creating entities:
        # https://developers.google.com/appengine/docs/python/ndb/entities
        user = RogagUser(fullName=request.fullName, email=request.email, password=request.password)
        user.put()

        # this is how we freakin' get our key
        return UserRegistrationResponse(userId=str(user.key.urlsafe()))

    @endpoints.method(UserLoginRequest, UserLoginResponse, path='login', http_method='PUT', name='login')
    def login(self, request):
        logging.info('login:' + request.username)

        return  UserLoginResponse(userId='1', username='fake_user', userToken='fake_token')