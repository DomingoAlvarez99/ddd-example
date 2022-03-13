package org.dalvarez.ddd_example.shared.infrastructure.metrics;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "elastic.apm")
@ConditionalOnProperty(value = "elastic.apm.enabled", havingValue = "true")
public class ElasticApmConfig {

    private static final String SERVER_URL_KEY = "server_url";
    private String serverUrl;

    private static final String SERVICE_NAME_KEY = "service_name";
    private String serviceName;

    private static final String SECRET_TOKEN_KEY = "secret_token";
    private String secretToken;

    private static final String ENVIRONMENT_KEY = "environment";
    private String environment;

    private static final String APPLICATION_PACKAGES_KEY = "application_packages";
    private String applicationPackages;

    private static final String LOG_LEVEL_KEY = "log_level";
    private String logLevel;

    @PostConstruct
    public void init() {
        Map<String, String> apmProps = Map.of(
                SERVER_URL_KEY, serverUrl,
                SERVICE_NAME_KEY, serviceName,
                SECRET_TOKEN_KEY, secretToken,
                ENVIRONMENT_KEY, environment,
                APPLICATION_PACKAGES_KEY, applicationPackages,
                LOG_LEVEL_KEY, logLevel
        );

        ElasticApmAttacher.attach(apmProps);
    }

    public String serverUrl() {
        return serverUrl;
    }

    public void setServerUrl(final String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String serviceName() {
        return serviceName;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

    public String secretToken() {
        return secretToken;
    }

    public void setSecretToken(final String secretToken) {
        this.secretToken = secretToken;
    }

    public String environment() {
        return environment;
    }

    public void setEnvironment(final String environment) {
        this.environment = environment;
    }

    public String applicationPackages() {
        return applicationPackages;
    }

    public void setApplicationPackages(final String applicationPackages) {
        this.applicationPackages = applicationPackages;
    }

    public String logLevel() {
        return logLevel;
    }

    public void setLogLevel(final String logLevel) {
        this.logLevel = logLevel;
    }

}
