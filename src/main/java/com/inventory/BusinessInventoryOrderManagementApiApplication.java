package com.inventory;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@OpenAPIDefinition(
        info = @Info(
                title = "BusinessInventoryOrderManagementApiApplication",
                description = "BusinessInventoryOrderManagementApiApplication REST API Documentation",
                version="v1",
                contact = @Contact(
                        name = "Code-crafter",
                        email = "info@code-crafter.in",
                        url = "https://code-crafter.in"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "BusinessInventoryOrderManagementApiApplication REST API Documentation",
                url = "https://code-crafter.in"
        )
)
//swagger by default url:http://localhost:8083/swagger-ui/index.html
@SpringBootApplication
public class BusinessInventoryOrderManagementApiApplication extends SpringBootServletInitializer {
        //jar conversion to war
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BusinessInventoryOrderManagementApiApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(BusinessInventoryOrderManagementApiApplication.class, args);
    }

}
