package com.open.patient.patientapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Patient Application API")
                        .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                        .termsOfService("terms")
                        .contact(new Contact().email("@aarkatta.io"))
                        .license(new License().name("GNU"))
                        .version("1.0")
                );
    }
}
