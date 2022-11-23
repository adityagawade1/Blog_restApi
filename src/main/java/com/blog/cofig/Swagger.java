package com.blog.cofig;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger {
  
	 private static final String Authorization_Header="Authorization";
	 
	 
	 
	 private ApiKey apiKey() {
		 
		 return new ApiKey("JWT", Authorization_Header, "header");
	 }
	
	 private List<SecurityContext> securityContext(){
		 return Arrays.asList(SecurityContext.builder().securityReferences(securityRef()).build());
	 }
	
	 private List<SecurityReference> securityRef(){
		 
		 
		 AuthorizationScope scope= new AuthorizationScope("global", "Access Everything");
		 return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {scope}));
	 }
	 
	@Bean
 public Docket api() {
		 
		 return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(securityContext()).securitySchemes(Arrays.asList(apiKey()))
				 .select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	 }

	private ApiInfo getInfo() {
		
	
		// TODO Auto-generated method stub
		return new ApiInfo("Blogging Application", "This project Developed by Aditya Gawade", "1.0", "Term of Service", new Contact("Aditya", "http://learn", "adityagawade1998@gmail.com"), "Licence of APIS", "APIS Licence URL", Collections.emptyList());
	}
}
