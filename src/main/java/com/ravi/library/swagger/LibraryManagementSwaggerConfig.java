package com.ravi.library.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class LibraryManagementSwaggerConfig {

        @Bean
        public Docket demoApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
           //         .apis(RequestHandlerSelectors.basePackage("com.ravi.library.bookmgmt"))
                    .paths(Predicate.not(PathSelectors.regex("/error.*")))
                    .build().directModelSubstitute(Timestamp.class, String.class)
                    .directModelSubstitute(LocalDateTime.class, String.class)
                    .apiInfo(apiInfo());
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Library Management API Documentation")
                    .description("Details of the APIs consumed by Library Management ")
                    .build();
        }
}

