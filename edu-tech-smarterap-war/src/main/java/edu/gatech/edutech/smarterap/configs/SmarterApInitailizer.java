package edu.gatech.edutech.smarterap.configs;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import com.stormpath.spring.config.StormpathMethodSecurityConfiguration;

public class SmarterApInitailizer implements WebApplicationInitializer
{
	@Override
	public void onStartup(final ServletContext sc) throws ServletException
	{
		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(MainConfig.class);
		context.register(StormpathMethodSecurityConfiguration.class);
		sc.addListener(new ContextLoaderListener(context));

		final ServletRegistration.Dynamic dispatcher = sc.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/smarter-ap/*");

		//Stormpath Filter
		final FilterRegistration.Dynamic filter = sc.addFilter("stormpathFilter", new DelegatingFilterProxy());
		final EnumSet<DispatcherType> types = EnumSet.of(DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.REQUEST);
		filter.addMappingForUrlPatterns(types, false, "/*");

		//Spring Security Filter
		final FilterRegistration.Dynamic securityFilter = sc.addFilter(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, DelegatingFilterProxy.class);
		securityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException
	{
		final CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(5242880);//5MB
		return resolver;
	}

}
