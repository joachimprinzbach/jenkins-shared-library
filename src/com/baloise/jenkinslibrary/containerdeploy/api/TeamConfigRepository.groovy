package com.baloise.jenkinslibrary.containerdeploy.api

def runSync(eventString) {
    def webhookChangeEventPayload = readJSON text: eventString
    def organisation = webhookChangeEventPayload["repository"]["project"]["key"]
    def repository = webhookChangeEventPayload["repository"]["slug"]
    def refId = webhookChangeEventPayload["changes"][0]["refId"]
    if ("refs/heads/master".equals(refId)) {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '0f34a51f-334f-4ebe-a663-90e312fb32f6', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            return sh(script: """sudo podman run --rm -ti docker.io/niiku/gitopscli:v0.1.0 sync-apps \
--username "${USERNAME}" \
--password "${PASSWORD}" \
--organisation "${organisation}" \
--repository-name "${repository}" \
--root-organisation "DPL" \
--root-repository-name "apps-root-config" \
--git-provider-url https://bitbucket.baloise.dev \
""", returnStdout: true)
        }
    }
}