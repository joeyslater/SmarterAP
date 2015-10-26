package edu.gatech.edutech.smarterap.configs;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import com.stormpath.sdk.client.Client;
import com.stormpath.spring.security.authz.permission.evaluator.WildcardPermissionEvaluator;
import com.stormpath.spring.security.client.ClientFactory;
import com.stormpath.spring.security.provider.StormpathAuthenticationProvider;

import edu.gatech.edutech.smarterap.enums.SecurityRole;

@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@PropertySource("classpath:api-key.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	final String					applicationRestUrl	= "https://api.stormpath.com/v1/applications/2xCBGJXTlzumLJ1p5H9BdG";

	@Autowired
	private AuthenticationProvider	stormpathAuthenticationProvider;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}

	// The access control settings are defined here
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http.formLogin().and().authorizeRequests().accessDecisionManager(accessDecisionManager()).antMatchers("/smarter-ap/**/api/**")
		        .hasAnyAuthority(SecurityRole.returnAllRoles()).and().logout().logoutUrl("/logout").logoutSuccessUrl("/").and().httpBasic().and().csrf().disable();
	}

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception
	{
		return authenticationManagerBean();
	}

	// Let's add the StormpathAuthenticationProvider to the
	// AuthenticationManager
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(stormpathAuthenticationProvider);
	}

	// Prevents the addition of the "ROLE_" prefix in authorities
	@Bean
	public WebExpressionVoter webExpressionVoter()
	{
		final WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		return webExpressionVoter;
	}

	@Bean
	public AffirmativeBased accessDecisionManager()
	{
		final AffirmativeBased affirmativeBased = new AffirmativeBased(newArrayList((AccessDecisionVoter) webExpressionVoter()));
		affirmativeBased.setAllowIfAllAbstainDecisions(false);
		return affirmativeBased;
	}

	@Bean
	public String getApplicationRestUrl()
	{
		return applicationRestUrl;
	}

	@Bean
	public ClientFactory stormpathClient(final CacheManager cacheManager)
	{
		final ClientFactory clientFactory = new ClientFactory();
		clientFactory.setApiKeyFileLocation("classpath:api-key.properties");
		clientFactory.setCacheManager(cacheManager);
		return clientFactory;
	}

	// Let's instantiate the Stormpath Authentication Provider
	@Bean
	@Autowired
	public StormpathAuthenticationProvider stormpathAuthenticationProvider(final Client client, final String applicationRestUrl) throws Exception
	{
		final StormpathAuthenticationProvider stormpathAuthenticationProvider = new StormpathAuthenticationProvider();
		stormpathAuthenticationProvider.setClient(client);
		stormpathAuthenticationProvider.setApplicationRestUrl(applicationRestUrl);
		return stormpathAuthenticationProvider;
	}

	@Bean
	public WildcardPermissionEvaluator permissionEvaluator()
	{
		return new WildcardPermissionEvaluator();
	}

	@Bean
	public MethodSecurityExpressionHandler methodExpressionHandler(final WildcardPermissionEvaluator permissionEvaluator)
	{
		final DefaultMethodSecurityExpressionHandler methodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
		methodSecurityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
		return methodSecurityExpressionHandler;
	}

	@Bean
	public DefaultWebSecurityExpressionHandler webExpressionHandler(final WildcardPermissionEvaluator permissionEvaluator)
	{
		final DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		webSecurityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
		return webSecurityExpressionHandler;
	}

	@Bean
	public CacheManager cacheManager()
	{
		final SimpleCacheManager cacheManager = new SimpleCacheManager();
		final Collection<Cache> caches = new ArrayList<Cache>();
		caches.add(applicationCache().getObject());
		caches.add(accountCache().getObject());
		caches.add(groupCache().getObject());
		caches.add(customDataCache().getObject());
		cacheManager.setCaches(caches);
		return cacheManager;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean applicationCache()
	{
		final ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
		cacheFactoryBean.setName("com.stormpath.sdk.application.Application");
		return cacheFactoryBean;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean accountCache()
	{
		final ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
		cacheFactoryBean.setName("com.stormpath.sdk.account.Account");
		return cacheFactoryBean;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean groupCache()
	{
		final ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
		cacheFactoryBean.setName("com.stormpath.sdk.group.Group");
		return cacheFactoryBean;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean customDataCache()
	{
		final ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
		cacheFactoryBean.setName("com.stormpath.sdk.directory.CustomData");
		return cacheFactoryBean;
	}

}
