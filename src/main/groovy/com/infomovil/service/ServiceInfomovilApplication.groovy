package com.infomovil.service

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup

@SpringBootApplication
class ServiceInfomovilApplication extends SpringBootServletInitializer {
	
	@Autowired
	private Environment environment;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServiceInfomovilApplication.class);
	}
	
	static void main(String[] args) {
		SpringApplication.run ServiceInfomovilApplication, args
	}
	
}
