pipeline {
	agent any
	stages {
// 		stage('Checkout') {
// 			steps {
// 				/* Faz um “git clone” no repositório do nosso projeto no github */
// 			     git branch: 'master', credentialsId: 'GitAcesso', url: 'https://github.com/rafabart/mf-customer-api'
// 			}
// 		}
		stage('Build') {
			steps {
				/* Executa o build */
				sh './gradlew assemble'
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