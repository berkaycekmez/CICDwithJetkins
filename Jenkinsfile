pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "berkaycekmez/ci_cd_with_jetkins"
        // Kubernetes config dosyasının yolu - Windows için
        KUBECONFIG = "C:\\Users\\BERKAY\\.kube\\config"
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/berkaycekmez/CICDwithJetkins.git'
            }
        }
        stage('Build') {
            steps {
                bat '.\\mvnw clean package'
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE% .'
            }
        }
        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat '''
                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                    '''
                }
            }
        }
        stage('Docker Push') {
            steps {
                bat 'docker push %DOCKER_IMAGE%'
            }
        }
        stage('Deploy to K8s') {
            steps {
                bat 'kubectl apply --kubeconfig="%KUBECONFIG%" -f deployment.yaml'
                bat 'kubectl apply --kubeconfig="%KUBECONFIG%" -f service.yaml'
            }
        }
    }
}
