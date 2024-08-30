pipeline {
    agent {label 'linux'}

 tools {
        jdk 'JDK-17'
        gradle 'Gradle-8.4'
        nodejs 'NodeJS_20.13.1'
    }

    stages {
        stage('Build - recommendation-management-service') {
                steps {
                    dir('recommendation-management-service') {
                        echo 'Building Recommendation Management Service Module..'
                        sh 'chmod +x ./gradlew'
                        sh './gradlew clean build'
                    }
                }
        }
         stage('Test - recommendation-management-service') {
            steps {
                dir('recommendation-management-service') {
                    echo 'Running tests for recommendation-management-service....'
                    sh './gradlew test'
                }
            }
        }
        stage('Build - user-management-service') {
            steps {
                dir('user-management-service') {
                    echo 'Building User Management Service Module..'
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean build'
                }
            }
        }
         stage('Test - user-management-service') {
            steps {
                dir('user-management-service') {
                    echo 'Running tests for user-management-service..'
                    sh './gradlew test'
                }
            }
        }
        stage('Build - travel-recommendation-app') {
                    steps {
                        dir('travel-recommendation-app') {
                            echo 'Building Travel Recommendation App..'
                            sh 'npm install'
                            sh 'npm run build'
                        }
                    }
                }
        stage('SonarQube analysis') {
            environment {
                scannerHome = tool 'SonarQube Scanner'
            }
            steps {
                withSonarQubeEnv('SonarHyd') {
                    sh '$scannerHome/bin/sonar-scanner'
                }
            }
        }
    }
	
	post {
        always {
            script {
                timeout(time:5, unit:'MINUTES'){
                    def qualityGateCheck = waitForQualityGate()
                    if (qualityGateCheck.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qualityGateCheck.status}"
                    }
                }
            }
        }
    }
}
