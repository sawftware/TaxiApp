package com.taxi.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.service.ApiInfo;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 *
 * @author alankavanagh
 *
 * Swagger Configuration used to generate the Swagger API Documentation
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    private static final String API_TITLE = "TaxiApp API";
    private static final String API_DESCRIPTION = "REST API for TaxiApp Application";
    private static final String API_VERSION = "1.0";
    private static final String API_BASE_PACKAGE = "com.taxi.app.controller";

    @Bean
    public static Docket api() {
        logger.info("SwaggerConfig: Executing api()");

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(API_BASE_PACKAGE))
                .paths(PathSelectors.any()).build().pathMapping("/").apiInfo(metadata());
    }

    private static ApiInfo metadata() {
        logger.info("SwaggerConfig: Executing metadata()");

        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION).build();
    }
}