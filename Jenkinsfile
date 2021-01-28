pipeline {
    agent any
    tools {
        jdk 'OPENJDK11' 
    }
    stages {
        
        stage ('Build Backend') {
            steps {
                sh './gradlew clean build -x test'       
            }
        }
        
        stage ('Unit Tests') {
            steps {
                sh './gradlew test'       
            }
        }
    }
}