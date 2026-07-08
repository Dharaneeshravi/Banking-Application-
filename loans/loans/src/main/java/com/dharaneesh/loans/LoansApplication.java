package com.dharaneesh.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans Microservice API",
				version = "1.0",
				description = "API documentation for the Loans Microservice",
				contact = @Contact(
						name = "Dharaneesh",
						email = "dharaneesh0203@gmail.com",
						url = "https://github.com/dharaneesh0203"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.iob.com"
				)


),
		externalDocs = @ExternalDocumentation(
				description = "Iob Loans microservice REST API Documentation",
				url = "https://www.iob.com"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
