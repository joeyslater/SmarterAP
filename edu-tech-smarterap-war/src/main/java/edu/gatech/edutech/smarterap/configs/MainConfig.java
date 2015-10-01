package edu.gatech.edutech.smarterap.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MvcConfig.class, ServicesConfig.class })
public class MainConfig {

}
