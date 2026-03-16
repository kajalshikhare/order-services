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

    }
}