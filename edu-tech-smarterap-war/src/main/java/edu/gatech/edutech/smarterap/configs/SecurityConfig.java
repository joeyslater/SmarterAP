package edu.gatech.edutech.smarterap.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.stormpath.spring.config.EnableStormpathWebSecurity;
import com.stormpath.spring.config.StormpathWebSecurityConfigurerAdapter;

import edu.gatech.edutech.smarterap.enums.SecurityRole;

@Configuration
@EnableStormpathWebSecurity
@PropertySource("classpath:api-key.properties")
public class SecurityConfig extends StormpathWebSecurityConfigurerAdapter
{
	public final static String	APP_REST_URL	= "https://api.stormpath.com/v1/applications/2xCBGJXTlzumLJ1p5H9BdG";
	public final static String	DIRECTORY_URL	= "https://api.stormpath.com/v1/directories/2xCWsa0OXYGpDv8Uw6AMEA";

	@Override
	protected void doConfigure(final HttpSecurity http) throws Exception
	{
		http.httpBasic().and()

		        .authorizeRequests()

		        //		        .formLogin().usernameParameter("emailAddress")
		        //		.failureUrl("/login").loginProcessingUrl("/smarter-ap/login").usernameParameter("emailAddress").permitAll()
		        //		        .loginProcessingUrl("/smarter-ap/login").usernameParameter("emailAddress")
		        //		        .permitAll().and()
		        .antMatchers("/#/login", "/index.html").permitAll()

		        .antMatchers("/index.html", "/home.html", "/login.html", "/*.js", "/*.css").permitAll()

		        .antMatchers("/#/**", "/smarter-ap/authenticate", "/smarter-ap/logout", "/smarter-ap/register", "/smarter-ap/account").permitAll()

		        .antMatchers("/smarter-ap/api/**").authenticated()

		        .antMatchers("/smarter-ap/api/admin/**").hasAnyRole("ADMIN", SecurityRole.ADMIN.toString())

		        .and().formLogin().loginPage("/#/login").loginProcessingUrl("/smarter-ap/login").permitAll()

		        .and().logout().permitAll()

		        .and().csrf().disable();
	}

}