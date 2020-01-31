package com.baloise.jenkinslibrary.git.api

def createComment(org, repo, prId, comment) {
    return sh(script: """sudo podman run --rm -ti docker.io/niiku/gitopscli:v0.7.0 createPRComment \
--username "${USERNAME}" \
--password "${PASSWORD}" \
--organisation "${org}" \
--repository-name "${repo}" \
--pull-request-id "${prId}" \
--text "${comment}" \
--git-provider-url https://bitbucket.baloise.dev \
""", returnStdout: true)
}