/**
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.ipaas.github;

import com.redhat.ipaas.github.backend.ExtendedContentsService;
import com.redhat.ipaas.github.backend.KeycloakTokenAwareGitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author roland
 * @since 09/03/2017
 */
@Configuration
public class SpringGitServiceFactory {

    @Value("${github.service}")
    private String gitHubHost = "ipaas-github-proxy";

    @Bean
    @Scope("request")
    public RepositoryService repositoryService() {
        return new RepositoryService(new KeycloakTokenAwareGitHubClient(gitHubHost));
    }

    @Bean
    @Scope("request")
    public ExtendedContentsService contentsService() {
        return new ExtendedContentsService(new KeycloakTokenAwareGitHubClient(gitHubHost));
    }
}
