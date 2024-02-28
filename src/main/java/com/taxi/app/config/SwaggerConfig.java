package com.taxi.app.config;

import springfox.documentation.service.ApiInfo;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public static Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.taxi.app.controller"))
                .paths(PathSelectors.any()).build().pathMapping("/").apiInfo(metadata());
    }

    private static ApiInfo metadata() {
        return new ApiInfoBuilder().title("TaxiApp API")
                .description("REST API for TaxiApp Application").version("1.0").build();
    }
}