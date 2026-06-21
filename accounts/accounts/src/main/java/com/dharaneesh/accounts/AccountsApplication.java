package com.dharaneesh.accounts;

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
				title = "Accounts Microservice REST API Documentation",
				description = "Dharaneesh Accounts Microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Dharaneesh",
						email = "dharaneesh0203@gmail.com",
						url="http://dharaneeshio.in"
				),
				license = @License(
						name = "Apache 2.O",
						url="http://dharaneeshio.in"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Dharaneesh Microservice REST API Documentation",
				url="http://dharaneeshio.in"
		)

)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
