package com.splitwise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {

	@Value("${swaggerenableurl}")
	String swaggerEnableUrl;

	@Value("${swaggerTitle}")
	String swaggerTitle;

	@Value("${swaggerDescription}")
	String swaggerDescription;

	@Value("${swaggerVersion}")
	String swaggerVersion;

	@Value("${swaggerTermsOfServiceUrl}")
	String swaggerTermsOfServiceUrl;

	@Value("${swaggerLicense}")
	String swaggerLicense;

	@Value("${swaggerLicenseUrl}")
	String swaggerLicenseUrl;

	@Value("${swaggerGroupName}")
	String swaggerGroupName;

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public Docket restfulApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(swaggerTitle, swaggerDescription, swaggerVersion, swaggerTermsOfServiceUrl,
				new Contact("", "", ""), swaggerLicense, swaggerLicenseUrl);
		return apiInfo;
	}
}
