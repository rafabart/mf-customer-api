pipeline {
	agent any

	tools {
	    gradle "Gradle6.8"
	}

	stages {
		stage('Build') {
			steps {
				/* Executa o build */
				sh 'gradle assemble'
			}
		}
		stage('Unit tests') {
            steps {
                /* Executa os testes unitários */
                sh '.gradle clean test'
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