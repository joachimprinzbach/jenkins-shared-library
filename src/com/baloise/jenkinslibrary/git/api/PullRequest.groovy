package com.baloise.jenkinslibrary.git.api

def createComment(org, repo, prId, comment) {
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