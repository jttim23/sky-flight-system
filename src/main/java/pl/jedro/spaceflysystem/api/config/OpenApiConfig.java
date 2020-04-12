package pl.jedro.spaceflysystem.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Space Flight System API",
                version = "v1",
                description = "This app provides REST APIs for flight and tourist entities",
                contact = @Contact(
                        name = "Example",
                        email = "admin@example.com"
                )
        )
)
public class OpenApiConfig {
}
