/**
 * 
 */
package com.njevcs.pvawnings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author patel
 *
 *         Nov 18, 2024
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket SwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.njevcs.pvawnings.controller"))
                .paths(PathSelectors.any()).build().apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("NJ EVCS Awnings API").description("This is a swagger documentation for NJ EVCS awnings project.")
                /*
                 * .contact(new Contact("Tejaskumar Patel", "https://github.com/TejasPatel007/njevcs-backend",
                 * "patelt18@montclair.edu"))
                 */.version("1.0").build();
    }

}
