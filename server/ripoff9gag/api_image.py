import endpoints
import logging
import base64
import time

from datetime import datetime, timedelta
from Crypto.Hash import SHA256
from Crypto.PublicKey import RSA
from Crypto.Signature import PKCS1_v1_5
from OpenSSL import crypto
from api_common import *
from protorpc import messages
from protorpc import remote
from datetime import datetime, timedelta


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

# Note: If your users are only uploading resources (writing) to an access-controlled bucket,
# you can use the resumable uploads functionality of Google Cloud Storage,
# and avoid signing URLs or requiring a Google account. In a resumable upload scenario,
# your (server-side) code authenticates and initiates an upload to Google Cloud Storage
# without actually uploading any data. The initiation request returns an upload ID,
# which can then be used in a client request to upload the data.
# The client request does not need to be signed because the upload ID,
# in effect, acts as an authentication token. If you choose this path,
# be sure to transmit the upload ID over HTTPS.

@endpoints.api(name='image', version='v1',
               description='Rogag API for Images')
class ImageAPI(remote.Service):


    # I probably could use a resumed upload
    # but signed url just seemed sophisticated so I went this route
    # -sectran
    @endpoints.method(GetUrlRequest, GetUrlResponse, path='url', http_method='GET', name='get.url')
    def get_url(self, request):
        logging.debug('get url')

        expiration = datetime.utcnow() + timedelta(hours=2)
        expiration = int(time.mktime(expiration.timetuple()))

        http_verb = 'POST'
        content_md5 = ''
        content_type = ''
        expiration_str = str(expiration)
        canonical_ext_header = 'x-goog-acl:public-read'

        #TODO: export bucket name to settings
        canonical_resource = '/%s/%s/%s_%s.png' % ('rogag-server.appspot.com', request.auth.userId, 'image',
                                                   expiration,)
        logging.debug('cr:' + canonical_resource)

        string_to_sign = "%s \n%s \n%s \n%s \n%s%s" % (http_verb, content_md5, content_type, expiration_str,
                                                       canonical_ext_header, canonical_resource,)

        print("string to sign:" + string_to_sign)


        private_key = open('/security/rogag-server.p12', 'rb').read()
        pkcs12 = crypto.load_pkcs12(private_key, 'notasecret')

        download_url = 'http://storage.googleapis.com/rogag-server.appspot.com/test_image.jpg'

        return GetUrlResponse(uploadUrl=string_to_sign, downloadUrl=download_url)
