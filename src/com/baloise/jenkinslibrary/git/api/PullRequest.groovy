package com.baloise.jenkinslibrary.git.api

def createComment(webhookChangeEventPayload, comment) {
    def org = webhookChangeEventPayload["pullRequest"]["fromRef"]["repository"]["project"]["key"]
    def repo = webhookChangeEventPayload["pullRequest"]["fromRef"]["repository"]["slug"]
    def prId = webhookChangeEventPayload["pullRequest"]["id"]
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '0f34a51f-334f-4ebe-a663-90e312fb32f6', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        return sh(script: """sudo podman run --rm -ti docker.io/baloiseincubator/gitopscli:latest create-pr-comment \
--username "${USERNAME}" \
--password "${PASSWORD}" \
--organisation "${org}" \
--repository-name "${repo}" \
--pr-id "${prId}" \
--text "${comment}" \
--git-provider-url https://bitbucket.baloise.dev \
""", returnStdout: true)
    }
}