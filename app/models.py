from django.db import models


class User(models.Model):
    email = models.TextField()
    username = models.TextField()
    password = models.TextField()

    def __str__(self):
        return "{} with email: {}".format(self.username, self.email)
