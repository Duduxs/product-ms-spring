package com.edudev.productms.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edudev.productms"))
                .paths(regex("/products.*"))
                .build()
                .apiInfo(metaInfo());

    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Product MS",
                "\n" +
                        "API developed for job vacancy at Compasso UOL where a Rest API was implemented to register products packaged in container.,",
                "1.0",
                "Terms of Service",
                new Contact(
                        "Eduardo J.",
                        "https://duduxs.github.io/portfolio/",
                        "eduarddoj1@gmail.com"
                ),
                "MIT License",
                "https://github.com/Duduxs/product-ms-spring/blob/master/LICENSE",
                new ArrayList<>()
        );
    }
}
