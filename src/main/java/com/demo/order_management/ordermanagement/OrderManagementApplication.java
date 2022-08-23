package com.demo.order_management.ordermanagement;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 //add this to enable Swagger UI
public class OrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}
	//Swagger UI portion ---
	 @Bean 
	  public Docket api() { 
	    return new Docket(DocumentationType.SWAGGER_2)
	      .select() 
	      .apis(RequestHandlerSelectors.basePackage("com.demo.order_management.ordermanagement"))
	      .paths(PathSelectors.ant("/amazon/v1/api/*"))
	      .build()
	      .apiInfo(apiDetails()); 
	  }
	 
	 private ApiInfo apiDetails() { //must have all the fields listed below
		 return new ApiInfo(
				 "New Amazing Amazon Api", //name
				 "Some custom description of API.",//Description
				 "v1", //version
				 "Free to Use", //terms of service URL
				 new springfox.documentation.service.Contact("Gunjan Basnet", "gunjanPortfolio.com", "gunjan@email.com"), //contact_info
				 "License of API",
				 "gunjanPortfolio.com:7777",
				 Collections.emptyList()
				 );
	 }
	 // ---- till here.
}
// mvn spring-boot:run