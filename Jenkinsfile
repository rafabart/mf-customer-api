pipeline {
    agent any
    tools {
        jdk 'JDK11'
    }

    stages {
        stage ('Build Backend') {
            steps {
                sh 'gradle clean build -x test'       
            }
        }
        
        stage ('Unit Tests') {
            steps {
                sh 'gradle test'       
            }
        }
    }
}