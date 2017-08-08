package com.org.readability.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
/**
 * @author Kshitiz Garg
 */
public class SwaggerConfiguration {

	@Value("${swagger-endpointRegex}")
	private String endpointRegex;
	
	@Value("${swagger-serviceTitle}")
	private String serviceTitle;
	
	@Value("${swagger-serviceDescription}")
	private String serviceDescription;
	
	@Value("${swagger-serviceVersion}")
	private String serviceVersion;
			
	@Bean
    public Docket api() {
		 return new Docket(DocumentationType.SWAGGER_2).
		    		useDefaultResponseMessages(false).select().
		    		apis(Predicates.not(RequestHandlerSelectors.withClassAnnotation(Deprecated.class))).
		    		paths(PathSelectors.regex(endpointRegex)).build()
		            .apiInfo(apiInfo());
	}
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(serviceTitle)
            .description(serviceDescription)
            .version(serviceVersion)
            .build();
    }
    
}