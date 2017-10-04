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
        jdk 'Java8-1.8.0_112'
    }
    stages {
        stage('Build') {
            steps {
                sendNotifications
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