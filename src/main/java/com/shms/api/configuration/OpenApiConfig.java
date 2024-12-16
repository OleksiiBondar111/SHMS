package com.shms.api.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Custom API Documentation", version = "1.0", description = "API Documentation"),
    tags = {
        @Tag(name = "Insurance", description = "API for managing insurances"),
        @Tag(name = "Patient", description = "API for managing patients"),
        @Tag(name = "Doctor", description = "API for managing doctors"),
        @Tag(name = "Appointment", description = "API for managing appointments"),
        @Tag(name = "Medical Record", description = "API for managing of medical records"),
        @Tag(name = "Test Result", description = "API for managing of test results"),
        @Tag(name = "Invoice", description = "API for managing invoices"),
        @Tag(name = "Payment", description = "API for managing payments"),
    }
)
public class OpenApiConfig {
}
