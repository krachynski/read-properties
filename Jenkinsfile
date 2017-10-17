#!/usr/bin/env Groovy

pipeline {
    agent {
        label 'windows'
    }
    environment {
        MVN_SETTINGS=credentials('maven-settings-windows')
    }
    tools {
        maven 'Maven35'
        jdk 'jdk-1.8.0-112-win'
    }
    stages {
        stage('Build') {
            steps {
                sendNotifications 'STARTING'
                bat("mvn -B -V -U -s ${MVN_SETTINGS} clean deploy -DbuildNumber=${BUILD_NUMBER}")
            }
        }
    }
    post {
        always {
            sendNotifications currentBuild.result
        }
    }
}