package edu.gatech.edutech.smarterap.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DatabaseConfig.class, MvcConfig.class, ServicesConfig.class, SecurityConfig.class, ComponentConfig.class })
public class MainConfig
{

}
