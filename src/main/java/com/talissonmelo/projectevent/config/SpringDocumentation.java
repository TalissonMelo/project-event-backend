package com.talissonmelo.projectevent.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringDocumentation implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
				.basePackage("com.talissonmelo.projectevent.resources"))
				.build()
				.useDefaultResponseMessages(true)
				.globalResponseMessage(RequestMethod.GET,  globalResponseMessage())
				.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
	            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
	            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.apiInfo(apiInfo())
				.tags(new Tag("Cidades", "Gerencia as cidades"));
	}
	
	private List<ResponseMessage> globalResponseMessage(){
		return Arrays.asList(
				new ResponseMessageBuilder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("Erro interno do servidor")
				.build(),
			
				new ResponseMessageBuilder()
				.code(HttpStatus.NOT_ACCEPTABLE.value())
				.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
				.build()
		);
	}
	
	private List<ResponseMessage> globalPostPutResponseMessages() {
	    return Arrays.asList(
	            new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisição inválida (dados do cliente)")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.NOT_ACCEPTABLE.value())
	                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
	                .message("Requisição recusada porque o corpo está em um formato não suportado")
	                .build()
	        );
	}

	private List<ResponseMessage> globalDeleteResponseMessages() {
	    return Arrays.asList(
	            new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisição inválida (dados do cliente)")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .build()
	        );
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
					.title("Event")
					.description("API aberta para estudantes e apaixonados por programação")
					.version("1")
					.contact(new Contact("Talisson De Melo Rodrigues", "https://projeto-integrador-projeto-implementacao-event.netlify.app/", "talisson.cursos@gmail.com"))
					.build();
	}

}
