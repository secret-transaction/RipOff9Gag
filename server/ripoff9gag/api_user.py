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
    userImageUrl = messages.StringField(4)

'''
Endpoint APIs
'''


@endpoints.api(name='user', version='v1', description='Rogag API for User Management')
class UserAPI(remote.Service):

    @endpoints.method(UserRegistrationRequest, UserRegistrationResponse, path='create', http_method='POST',
                      name='create')
    def create_user(self, request):
        logging.info('registering:' + request.email)

        existing_user = RogagUser.query_email(email=request.email).fetch(1)
        if len(existing_user) > 0:
            return UserRegistrationResponse(userId='failed')

        # creating entities:
        # https://developers.google.com/appengine/docs/python/ndb/entities
        user = RogagUser(fullName=request.fullName, email=request.email, password=request.password)
        user.put()

        # this is how we freakin' get our key
        return UserRegistrationResponse(userId=str(user.key.urlsafe()))

    @endpoints.method(UserLoginRequest, UserLoginResponse, path='login', http_method='PUT', name='login')
    def login(self, request):
        logging.info('login:' + request.username)

        results = RogagUser.query_email_and_password(email=request.username, password=request.password).fetch(1)
        if len(results) > 0:
            user = results[0]
            logging.info('found ' + user.fullName)

            #TODO: find a better way to create an access token (issue#2)
            access_token = user.key.urlsafe() + '_' + 'xxxtokenxxx'
            user.access_token = access_token
            user.put()

            #TODO: figure out where to store and retrieve images (issue#3)
            image_url = 'http://38.media.tumblr.com/avatar_a1f7bb7ebcce_128.png'

            return UserLoginResponse(userId=str(user.key.urlsafe()), username=user.fullName, userToken=access_token,
                                     userImageUrl=image_url)

        return  UserLoginResponse(userId='', username='', userToken='')