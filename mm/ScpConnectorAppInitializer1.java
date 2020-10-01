package com.incture.sample.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Polireddy.M
 *
 */
@Configuration
@ComponentScan(basePackages = "com.incture")
public class FileUploadConfiguration implements WebMvcConfigurer {

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(104857600);// 100mb
		resolver.setMaxInMemorySize(1048576);// 1mb
		return resolver;
	}

	
}