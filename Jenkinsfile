pipeline {
    try{
    notifyBuild('STARTED')
   stage('Preparation') {
      git 'https://github.com/Mohammed394/Mohamed-Ali.git'

      mvnHome = tool 'MAVEN_HOME'
   }
   stage('Build') {
         // Run the maven build
              withEnv(["MVN_HOME=$mvnHome"]) {
                 if (isUnix()) {
                    sh '"$MVN_HOME/bin/mvn" test -Pregression'
                 } else {
                    bat(/"%MVN_HOME%\bin\mvn" test -Pregression/)
                 }
              }

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
