from protorpc import messages


class UserAccount(messages.Message):
    userId = messages.StringField(1, required=True)
    username = messages.StringField(2, required=True)


class UserAuthentication(messages.Message):
    userId = messages.StringField(1, required=True)
    userToken = messages.StringField(2, required=True)