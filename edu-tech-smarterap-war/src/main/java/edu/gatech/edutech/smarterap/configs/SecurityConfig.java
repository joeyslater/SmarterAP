package edu.gatech.edutech.smarterap.configs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.stormpath.spring.config.EnableStormpathWebSecurity;
import com.stormpath.spring.config.StormpathWebSecurityConfigurerAdapter;

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
		http.httpBasic().and().formLogin().loginPage("/#/login").loginProcessingUrl("/smarter-ap/login").permitAll().and()
		        //		        .formLogin().usernameParameter("emailAddress")
		        //		.failureUrl("/login").loginProcessingUrl("/smarter-ap/login").usernameParameter("emailAddress").permitAll()
		        //		        .loginProcessingUrl("/smarter-ap/login").usernameParameter("emailAddress")
		        //		        .permitAll().and()

		        .authorizeRequests().antMatchers("/smarter-ap/authenticate", "/smarter-ap/register", "/smarter-ap/account").permitAll()

		        .anyRequest().authenticated()

		        .and()

		        .logout().permitAll()

		        .and().csrf().disable();
		//		csrfTokenRepository(csrfTokenRepository()).and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
		;
	}

	private Filter csrfHeaderFilter()
	{
		return new CsrfHeaderFilter();
	}

	private CsrfTokenRepository csrfTokenRepository()
	{
		final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	public class CsrfHeaderFilter extends OncePerRequestFilter
	{
		@Override
		protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException
		{
			final CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			if (csrf != null)
			{
				Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
				final String token = csrf.getToken();
				if (cookie == null || token != null && !token.equals(cookie.getValue()))
				{
					cookie = new Cookie("XSRF-TOKEN", token);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			filterChain.doFilter(request, response);
		}
	}

}