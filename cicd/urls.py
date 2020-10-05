from django.urls import path
from cicd import views

app_name = 'cicd'

urlpatterns = [
    path('', views.index, name='index'),
]