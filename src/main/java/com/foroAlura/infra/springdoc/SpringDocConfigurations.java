package com.foroAlura.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info().title("API Foro Alura")
                        .description("Api rest de la aplicacion foro alura")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("cristian.barajas09@hotmail.com")
                        ).license(new License()
                                .name("Apache 2.0").url(("http://foroalura/api/licencia"))
                        )
                );
    }

    @Bean
    public void message() {
        System.out.println("bearer is working");
    }
}
