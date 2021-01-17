pipeline {
	agent {
		docker {
			image 'maven'
			args '-v /root/.m2:/root/.m2'
		}
	}
	stages {
		stage('Checkout') {
			steps {
				/* Faz um “git clone” no repositorio do nosso projeto no github */
				 git branch: 'dev', credentialsId: 'GitAcesso', url: 'https://github.com/rafabart/mf-customer-api'
			}
		}
		stage('Build + Unit tests') {
			steps {
				/* Executa os testes unitários */
				sh 'mvn clean test'
			}
		}
		stage('Archiving Reports') {
            steps {
                /* FPara armazenar e publicar os relatórios testes unitários no Jenkins */
                dir(path: '.') {
                    publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/site/jacoco/', reportFiles: 'index.html', reportName: 'Code Coverage', reportTitles: 'Code Coverage'])
                    step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
                }
            }
        }
	}
}
