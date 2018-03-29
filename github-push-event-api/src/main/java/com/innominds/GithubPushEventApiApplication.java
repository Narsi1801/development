package com.innominds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.innominds.config.CORSResponseFilter;

@SpringBootApplication
public class GithubPushEventApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubPushEventApiApplication.class, args);
	}
	
	// Register CORS Filter
	  @Bean
	  public FilterRegistrationBean filterRegistrationBean() {
	    FilterRegistrationBean bean = new FilterRegistrationBean(new CORSResponseFilter());
	   
	    return bean;
	  }
}
