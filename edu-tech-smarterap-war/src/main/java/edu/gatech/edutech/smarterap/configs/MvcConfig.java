package edu.gatech.edutech.smarterap.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan({ "edu.gatech.edutech.smarterap.controllers", "edu.gatech.edutech.smarterap.controllers.auth" })
@Configuration
@ControllerAdvice
public class MvcConfig extends WebMvcConfigurerAdapter
{
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void handle(final HttpMessageNotReadableException e)
	{
		e.printStackTrace();
		throw e;
	}
}