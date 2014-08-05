from google.appengine.ext import ndb

# Create your models here.


class RogagUser(ndb.Model):
    fullName = ndb.StringProperty()
    email = ndb.StringProperty()
    password = ndb.StringProperty()

    @classmethod
    def query_email(cls, email):
        return cls.query(cls.email == email)


    @classmethod
    def query_email_and_password(cls, email, password):
        return cls.query(ndb.AND(cls.email == email, cls.password == password))
