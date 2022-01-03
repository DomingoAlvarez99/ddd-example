package org.dalvarez.ddd_example.shared.infrastructure.rest_api.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String appDescription;

    @Value("${app.version}")
    private String appVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info().title(appName)
                          .version(appVersion)
                          .description(appDescription)
                          .termsOfService("http://swagger.io/terms/")
                          .license(new License().name("Apache 2.0").url("http://springdoc.org"))
        );
    }


}
