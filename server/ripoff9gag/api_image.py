import endpoints
import logging
import base64
import time
import urllib

from datetime import datetime, timedelta
from Crypto.Hash import SHA256
from Crypto.PublicKey import RSA
from Crypto.Signature import PKCS1_v1_5
# TODO: work on actual implementation later, priority is client completion
# from OpenSSL import crypto
from api_common import *
from protorpc import messages
from protorpc import remote


class GetUrlRequest(messages.Message):
    auth = messages.MessageField(UserAuthentication, 1, required=True)


class GetUrlResponse(messages.Message):
    uploadUrl = messages.StringField(1, required=True)
    downloadUrl = messages.StringField(2, required=True)


# this is how i installed the freaking crypto library
# http://stackoverflow.com/questions/11788508/pycrypto-on-google-app-engine-1-7-0-with-python-2-7-on-mac-os-x-10-8 \
# -causes-im


# also and error with OpenSSL:
# from OpenSSL import crypto
# ImportError: No module named OpenSSL
# http://stackoverflow.com/questions/21793471/no-module-named-openssl-crypto-and-importerror- \
# signedjwtassertioncredentials
# also read this:
# https://developers.google.com/appengine/docs/python/sockets/ssl_support

@endpoints.api(name='image', version='v1',
               description='Rogag API for Images')
class ImageAPI(remote.Service):


    @endpoints.method(GetUrlRequest, GetUrlResponse, path='url', http_method='GET', name='get.url')
    def get_url(self, request):
        return GetUrlResponse(uploadUrl='', downloadUrl='')
