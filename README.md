# jenkins-shared-library

## Usage
```groovy
@Library('jenkins-shared-library') _

import com.baloise.jenkinslibrary.containerdeploy.api.TeamConfigRepository

pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '100'))
        timeout(time: 1, unit: 'HOURS')
        timestamps()
        quietPeriod(0)
    }
    triggers {
        GenericTrigger(
                causeString: 'Generic Cause',
                genericVariables: [[defaultValue: '', key: 'webhookPayload', regexpFilter: '', value: '$']],
                regexpFilterExpression: '',
                regexpFilterText: '',
                token: 'webhook'
        )
    }
    stages {
        stage('Do magic') {
            steps {
                script {
                    new TeamConfigRepository().runSync("${webhookPayload}")
                }
            }
        }
    }
}
```