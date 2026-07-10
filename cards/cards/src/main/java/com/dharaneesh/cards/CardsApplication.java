package com.dharaneesh.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards Microservice API",
				version = "1.0",
				description = "API documentation for the Cards Microservice",
				contact = @Contact(
						name = "Dharaneesh",
						email = "dharaneesh0203@gmail.com",
						url = "https://github.com/dharaneesh"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.iob.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "IOBBank Cards microservice REST API Documentation",
				url = "https://www.iob.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
