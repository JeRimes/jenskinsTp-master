pipeline {
  agent any
  tools {
    maven 'Maven 3'
  }
    post{

       always{
       emailext body: 'Ce Build $BUILD_NUMBER a ete éffectué',
       recipientProviders:[requestor()], subject: 'build', to:'lav.jeremy22@gmail.com'

        }
    }
    stages {
        stage('git checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/JeRimes/jenskinsTp-master.git'
            }
        }

        stage('build application') {
            steps {
                bat "mvn clean install"
            }
        }
        stage('Unit Test Execution') {
            steps{
                bat 'mvn test'
                
            }
        }
        stage('Build the docker image') {
                steps{
                    withCredentials([usernamePassword(credentialsId: 'dockerhubpass', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                         bat "docker login -u $USERNAME -p $PASSWORD"
                    }
                    bat "docker build -t jerimes/triang7:1.0.0 ."

                }
        }
        stage('Push to DockerHub'){
                steps{
                    bat "docker push jerimes/triang7:1.0.0"
                }
            }
    }
}