from django.urls import path
from django.urls import include, path
from rest_framework import routers
from . import views

router = routers.DefaultRouter()
router.register(r'users', views.UserViewSet)

urlpatterns = [
    path("", views.index, name="index"),
    path("api/", include("rest_framework.urls", namespace="rest_framework"))
]