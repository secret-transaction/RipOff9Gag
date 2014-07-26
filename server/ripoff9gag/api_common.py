from protorpc import messages


class UserAccount(messages.Message):
    userId = messages.StringField(1, required=True)
    username = messages.StringField(2, required=True)


class UserAuthentication(messages.Message):
    userId = messages.StringField(1, required=True)
    userToken = messages.StringField(2, required=True)


class UserPostListType(messages.Enum):
    TRENDING = 1
    HOT = 2


class UserPost(messages.Message):
    postId = messages.StringField(1)
    points = messages.IntegerField(2)
    commentCount = messages.IntegerField(3)
    upVoted = messages.BooleanField(4)
    downVoted = messages.BooleanField(5)
    isUnsafe = messages.BooleanField(6)
    title = messages.StringField(7)
    imageUrl = messages.StringField(8)
    imageType = messages.StringField(9)
    owner = messages.MessageField(UserAccount, 10)