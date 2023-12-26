pipeline {

    agent any
    
    stages {

        stage('pull-code') {

            steps {

                git credentialsId: 'Github', url: 'https://github.com/madhav2903/Python.git'
//
            }

        }

        stage('build') {
          
             steps {
        
                script {
                       sh 'pip install -r requirements.txt'
                       }
            
                 }
         }

        stage('Test') {

            steps('Test python') {

                script {
                    sh """
                    pytest manage.py
                    """
                  }
               }

            }
        

        stage('Test 2') {

            steps('SonarQube Analysis') {
                
                withSonarQubeEnv('sonarserver') {

                    sh "mvn sonar:sonar"

                }

            }
        }
        stage('Deploy') {

            steps {
                    deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:8181// /opt/tomcat/webapps/')], contextPath: 'null', war: '**/*.war'//
                

            }

        }

        
    }
}
