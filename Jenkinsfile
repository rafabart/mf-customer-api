pipeline {
	agent {
        docker {
            image 'gradle:jdk11'
//                     args '-v "$PWD":/app'
            reuseNode true
        }
    }

	stages {
		stage('Checkout') {
			steps {
				/* Faz um “git clone” no repositório do nosso projeto no github */
				 git url: 'https://github.com/rafabart/mf-customer-api'
			}
		}
		stage('Build') {
			steps {
				/* Executa o build */
				sh './gradlew clean classes'
			}
		}
		stage('Unit tests') {
            steps {
                /* Executa os testes unitários */
                sh './gradlew clean test'
            }
            post {
                always {
                    junit '**/build/test-results/test/TEST-*.xml'
                }
            }
        }
		stage('Archiving Reports') {
            steps {
                /* Para armazenar e publicar os relatórios testes unitários no Jenkins */
                dir(path: '.') {
                    publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/site/jacoco/',
                    reportFiles: 'index.html', reportName: 'Code Coverage', reportTitles: 'Code Coverage'])
                    step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
                }
            }
        }
	}
}