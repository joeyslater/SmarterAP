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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	final String applicationRestUrl = "https://api.stormpath.com/v1/applications/2xCBGJXTlzumLJ1p5H9BdG";

	@Autowired
	private AuthenticationProvider stormpathAuthenticationProvider;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	// The access control settings are defined here
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().and().authorizeRequests().accessDecisionManager(accessDecisionManager())
				.antMatchers("/smarter-ap/**/api/**").hasAnyAuthority(SecurityRole.returnAllRoles()).and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/").and().httpBasic().and().csrf().disable();
	}

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return this.authenticationManagerBean();
	}

	// Let's add the StormpathAuthenticationProvider to the
	// AuthenticationManager
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(stormpathAuthenticationProvider);
	}

	// Prevents the addition of the "ROLE_" prefix in authorities
	@Bean
	public WebExpressionVoter webExpressionVoter() {
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		return webExpressionVoter;
	}

	@Bean
	public AffirmativeBased accessDecisionManager() {
		AffirmativeBased affirmativeBased = new AffirmativeBased(
				newArrayList((AccessDecisionVoter) webExpressionVoter()));
		affirmativeBased.setAllowIfAllAbstainDecisions(false);
		return affirmativeBased;
	}

	@Bean
	public String getApplicationRestUrl() {
		return this.applicationRestUrl;
	}

	@Bean
	public ClientFactory stormpathClient(CacheManager cacheManager) {
		ClientFactory clientFactory = new ClientFactory();
		clientFactory.setApiKeyFileLocation("classpath:api-key.properties");
		clientFactory.setCacheManager(cacheManager);
		return clientFactory;
	}

	// Let's instantiate the Stormpath Authentication Provider
	@Bean
	@Autowired
	public StormpathAuthenticationProvider stormpathAuthenticationProvider(Client client, String applicationRestUrl)
			throws Exception {
		StormpathAuthenticationProvider stormpathAuthenticationProvider = new StormpathAuthenticationProvider();
		stormpathAuthenticationProvider.setClient(client);
		stormpathAuthenticationProvider.setApplicationRestUrl(applicationRestUrl);
		return stormpathAuthenticationProvider;
	}

	@Bean
	public WildcardPermissionEvaluator permissionEvaluator() {
		return new WildcardPermissionEvaluator();
	}

	@Bean
	public MethodSecurityExpressionHandler methodExpressionHandler(WildcardPermissionEvaluator permissionEvaluator) {
		DefaultMethodSecurityExpressionHandler methodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
		methodSecurityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
		return methodSecurityExpressionHandler;
	}

	@Bean
	public DefaultWebSecurityExpressionHandler webExpressionHandler(WildcardPermissionEvaluator permissionEvaluator) {
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		webSecurityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
		return webSecurityExpressionHandler;
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Collection<Cache> caches = new ArrayList<Cache>();
		caches.add(applicationCache().getObject());
		caches.add(accountCache().getObject());
		caches.add(groupCache().getObject());
		caches.add(customDataCache().getObject());
		cacheManager.setCaches(caches);
		return cacheManager;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean applicationCache() {
		ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
		cacheFactoryBean.setName("com.stormpath.sdk.application.Application");
		return cacheFactoryBean;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean accountCache() {
		ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
		cacheFactoryBean.setName("com.stormpath.sdk.account.Account");
		return cacheFactoryBean;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean groupCache() {
		ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
		cacheFactoryBean.setName("com.stormpath.sdk.group.Group");
		return cacheFactoryBean;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean customDataCache() {
		ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
		cacheFactoryBean.setName("com.stormpath.sdk.directory.CustomData");
		return cacheFactoryBean;
	}

}
