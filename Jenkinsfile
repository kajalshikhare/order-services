pipeline {

    agent any

    tools {
        maven 'maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
            post {
    always {
        junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
    }
}
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-server') {
                    sh '''
                    mvn sonar:sonar \
                     -Dsonar.projectKey=demo-app \
  -Dsonar.projectName=demo-app \
  -Dsonar.projectVersion=1.0
                    '''
                }   
            }
        }

        stage("Quality Gate") {
    steps {
        timeout(time: 2, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
    }
}

    }
}