package com.nagarro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.model.AuthorDTO;
import com.nagarro.model.BookDTO;
import com.nagarro.service.RestClientService;
import com.nagarro.service.UserService;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages={"com.nagarro"})
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public RestClientService restClientService() {
		return new RestClientService();
	}
	
	@Bean
	public UserService userService() {
		return new UserService();
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
	    return new ObjectMapper();
	}
	
	@Bean
	public AuthorDTO authorDTO() {
	    return new AuthorDTO();
	}
	
	@Bean
	public BookDTO bookDTO() {
	    return new BookDTO();
	}
	
	@Bean
	public ModelAndView modelAndView() {
	    return new ModelAndView();
	}
}
