pipeline {

    agent any
    
    stages {

        stage('pull-code') {

            steps {

                git credentialsId: 'Github', url: ''

            }

        }

        stage('build') {
          
             steps {
        
                sh "mvn clean package"
            
        }
    }

        stage('Test') {

            steps('SonarQube Analysis') {
                
                withSonarQubeEnv('sonarserver') {

                    sh "mvn sonar:sonar"

                }

            }
        }

        stage('Deploy') {

            steps {
                    deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:8081// /opt/tomcat/webapps/')], contextPath: 'null', war: '**/*.war'//
                    //deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '/home/fs-shubhranshu/Tomcat/webapps', url: 'http://localhost:8081')], contextPath: '/', war: '/var/lib/jenkins/workspace/ci-cd pipeline/target/*.war'

            }

        }

        
    }
}
