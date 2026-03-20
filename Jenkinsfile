pipeline {

    agent any

    tools {
        maven 'maven3'
        // jdk 'jdk-17'
    }
//     environment {
//     ART_CREDS = credentials('artifactory')
// }

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

 stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

//        stage('Deploy to Artifactory') {
//     steps {
//         withCredentials([usernamePassword(
//             credentialsId: 'artifactory',
//             usernameVariable: 'USER',
//             passwordVariable: 'PASS'
//         )]) {

//             configFileProvider([configFile(
//                 fileId: '261f015c-83e4-4b3c-8a4c-fb610202f84e',
//                 variable: 'MAVEN_SETTINGS'
//             )]) {

//                 sh """
//                 mvn deploy \
//                 -DskipTests \
//                 -s $MAVEN_SETTINGS \
//                 -DART_USER=$USER \
//                 -DART_PASS=$PASS

//                 """
//             }
//         }
//     }
// }
stage('Deploy to Artifactory') {
    steps {
        configFileProvider([configFile(
            fileId: '261f015c-83e4-4b3c-8a4c-fb610202f84e',
            variable: 'MAVEN_SETTINGS'
        )]) {
            sh "cat $MAVEN_SETTINGS"

            sh "mvn deploy -DskipTests -s $MAVEN_SETTINGS"
        }
    }
}

    }
}