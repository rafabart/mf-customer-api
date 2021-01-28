pipeline {
    agent any
    tools {
        jdk 'JDK11'
    }
    stages {

        env.JAVA_HOME = "${jdk}"
        
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