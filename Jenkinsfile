pipeline {
    agent any
    tools {
        maven 'maven-3'   // Jenkins’te Tools’ta verdiğin isim
    }
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
                sh 'cp -r deploy/* /Users/batu/.jenkins/workspace/CDPipelineJob/'
            }
        }
    }
}