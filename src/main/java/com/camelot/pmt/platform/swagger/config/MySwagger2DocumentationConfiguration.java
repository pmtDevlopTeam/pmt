package com.camelot.pmt.platform.swagger.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class MySwagger2DocumentationConfiguration {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()//
                .apis(RequestHandlerSelectors.any())//
                .build().pathMapping("/")//
                .directModelSubstitute(LocalDate.class, String.class)//
                .genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false)
                .tags(new Tag("pmt-Service", "PMT服务"))//
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("PMT服务", "", "");
        return new ApiInfoBuilder()//
                .title("PMT提供的Api")//
                .description("PMT服务接口说明")//
                .license("Camelot License Version 1.0")//
                .contact(contact)//
                .version("1.0")//
                .build();
    }
}