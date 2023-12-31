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
        
                       sh 'pip install -r requirements.txt'
                       
                 }
         }

        // stage('Test') {

        //     steps('Test python') {

        //             sh 'pytest manage.py'
                
        //        }

        //     }
        

        stage('Sonarqube Testing') {

            steps('SonarQube Analysis') {

                // def scannerHome = tool 'SonarQube Scanner 2.8';
                
                withSonarQubeEnv('sonarserver') {

                    sh "${scannerHome}/bin/sonar-scanner"

                }

            }
        }
        // stage('Deploy-to-Tomcat') {

        //     steps {
        //             deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:8181// /opt/tomcat/webapps/')], contextPath: 'null', war: ''//
                

        //     }

        // }

        
    }
}
