from google.appengine.ext import ndb

# Create your models here.


class RogagUser(ndb.Model):
    fullName = ndb.StringProperty()
    email = ndb.StringProperty()
    password = ndb.StringProperty()
