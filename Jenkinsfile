#!/usr/bin/env Groovy

pipeline {
    agent {
        label 'windows'
    }
    environment {
        MVN_SETTINGS=credentials('maven-settings-windows')
    }
    tools {
        maven 'Maven36'
        jdk 'jdk-1.8.0-112-win'
    }
    stages {
        stage('Build') {
            steps {
                sendNotifications 'STARTING'
                bat("mvn -V -B -s ${MVN_SETTINGS} clean deploy -DbuildNumber=${BUILD_NUMBER}")
            }
        }
    }
    post {
        always {
            sendNotifications currentBuild.currentResult
        }
    }
}