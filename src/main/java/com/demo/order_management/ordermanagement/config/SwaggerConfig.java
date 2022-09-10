package com.demo.order_management.ordermanagement.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@EnableSwagger2 // add this to enable Swagger UI
public class SwaggerConfig {
	// Swagger UI portion ---
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.demo")) // -->base package  means that only the api we created are shown and others are hidden
				.paths(PathSelectors.any()) //--> this gives all the apis.//ant("/amazon/v1/api/*")--> this gives only get and post for some reason
				.build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() { // must have all the fields listed below
		return new ApiInfo("New Amazing Amazon Api", // name
				"Some custom description of API.", // Description
				"v1", // version
				"Free to Use", // terms of service URL
				new springfox.documentation.service.Contact("Gunjan Basnet", "gunjanPortfolio.com", "gunjan@email.com"), // contact_info
				"License of API", "gunjanPortfolio.com:7777", Collections.emptyList());
	}
	// ---- till here.
}
