from google.appengine.ext import db

# Create your models here.


class RogagUser(db.Model):
    fullName = db.StringProperty()
    email = db.StringProperty()
    password = db.StringProperty()
