import endpoints
import logging
from protorpc import messages
from protorpc import remote


'''
Endpoint DTOs
'''


class UserRegistrationRequest(messages.Message):
    email = messages.StringField(1)
    full_name = messages.StringField(2)
    password = messages.StringField(3)


class UserRegistrationResponse(messages.Message):
    user_id = messages.StringField(1)


class UserLoginRequest(messages.Message):
    username = messages.StringField(1, required=True)
    password = messages.StringField(2, required=True)


class UserLoginResponse(messages.Message):
    user_id = messages.StringField(1, required=True)
    username = messages.StringField(2, required=True)
    user_token = messages.StringField(3, required=True)

'''
Endpoint APIs
'''


@endpoints.api(name='user', version='v1', description='Rogag API for User Management')
class UserAPI(remote.Service):

    @endpoints.method(UserRegistrationRequest, UserRegistrationResponse, path='create', http_method='POST',
                      name='userCreate')
    def register_user(self, request):
        logging.info('registering:' + request.email)

        return UserRegistrationResponse(user_id='111')

    @endpoints.method(UserLoginRequest, UserLoginResponse, path='login', http_method='PUT', name='userLogin')
    def login(self, request):
        logging.info('login:' + request.username)

        return  UserLoginResponse(user_id='1', username='fake_user', user_token='fake_token')