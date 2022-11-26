pipeline{

    agent any

    tools{
            maven 'maven'
            jdk 'jdk-11.0.16.1'
        }

    post{

       always{
       emailext body: 'Ce Build $BUILD_NUMBER a ete éffectué',
       recipientProviders:[requestor()], subject: 'build', to:'richard91480@gmail.com'

        }
    }

        stages{
            stage('git checkout'){
                steps{
                    git credentialsId:'credentialsgit',url:'https://github.com/Nectosan/jenkinsTP'
                }
            }

            stage('Build the application'){
                steps{
                    bat 'mvn clean install'
                }
            }

            stage('Unit Test Execution'){
                steps{
                    bat 'mvn test'
                }
            }
            stage('Build the docker image') {
                steps{
                    withCredentials([usernamePassword(credentialsId: 'dockerhubpass', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){

                        bat "docker login -u $USERNAME -p $PASSWORD"
                    }
                    bat "docker build -t rihci/triang7:1.0.0 ."

                }
            }

            stage('Push to DockerHub'){
                steps{
                    bat "docker push rihci/triang7:1.0.0"
                }
            }

        }


}



