pipeline {
    agent any
    tools {
        jdk 'JDK11'
    }
     environment {
        JAVA_HOME = "${jdk}"
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