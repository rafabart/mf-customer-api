pipeline {
    agent any
    stages {
        
        stage ('Build Backend') {
            steps {
                sh './gradlew clean package -DskipTests=true'       
            }
        }
        
        stage ('Unit Tests') {
            steps {
                sh './gradlew test'       
            }
        }
    }
}