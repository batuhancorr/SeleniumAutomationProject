pipeline {
    agent any

    stages {
        stage('Kod Çek') {
            steps {
                echo 'Git repo çekildi (otomatik yapılıyor)'
            }
        }

        stage('Test Çalıştır') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    mkdir -p /tmp/mydeployment
                    cp -r deploy/* /tmp/mydeployment/
                '''
            }
        }
    }
}