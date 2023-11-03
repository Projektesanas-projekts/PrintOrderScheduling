from django.http import HttpResponse
from rest_framework import viewsets
from rest_framework import permissions
from django.contrib.auth.models import User
from app.serializers import UserSerializer

# Create your views here.
def index(request):
    return HttpResponse("Hello")

class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer
    permission_classes = [permissions.IsAuthenticated]
