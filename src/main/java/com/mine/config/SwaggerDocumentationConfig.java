package com.mine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by seithuaung on 23/2/17.
 */

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                .title("Mine")
                .description("Mine OpenApi Swagger 2 specs")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .version("1.0")
                .contact(new Contact("","",""))
                .build();
    }

    @Bean
    public Docket customImplementation(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mine.controller"))
                .build()
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }
}
