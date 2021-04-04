pipeline {
    try{
    notifyBuild('STARTED')
   stage('Preparation') {
      git 'https://github.com/Mohammed394/Mohamed-Ali.git'

      mvnHome = tool 'MAVEN_HOME'
   }
   stage('Build') {
         bat(/"/usr/local/bin/mvn" test -Pregression/)
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
    } catch (e) {
    // If there was an exception thrown, the build failed
    currentBuild.result = "FAILED"
    throw e

  } finally {
    // Success or failure, always send notifications
    notifyBuild(currentBuild.result)
  }
}
