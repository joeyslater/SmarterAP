package edu.gatech.edutech.smarterap.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan({ "edu.gatech.edutech.smarterap.controllers", "edu.gatech.edutech.smarterap.controllers.auth" })
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter
{

}
