environment {
    registry = riverforest02/jenkins_test_web
    rmipython = 'python:3'
    host = 'http://52.231.33.87:8000'
    hostregistry = 'http://52.231.33.87:8000/jenkins_test_web'
}
agent any
stages {
    stage('unit test django') {
        steps{
            sh  'python3 manage.py test'
        }
    }
    stage('build docker image') {
        steps {
            sh 'docker build -t $registry:latest'
        }
    }
    stage('Deploy docker images to local registry') {
        steps {
            sh 'docker tag $registry $hostregistry'
        }
    }
    stage('clean docker images') {
        steps{
            sh 'docker rmi $registry:latest'
        }
    }
    stage('clean docker python iamge') {
        steps{
            sh 'docker rmi $rmipython'
        }
    }
}