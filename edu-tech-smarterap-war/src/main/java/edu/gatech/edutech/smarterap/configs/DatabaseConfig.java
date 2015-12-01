package edu.gatech.edutech.smarterap.configs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.daos.DatabaseDaoPostgreImpl;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:database.properties" })
@ComponentScan(basePackageClasses = { DatabaseDaoPostgreImpl.class })
public class DatabaseConfig
{
	@Bean
	public BasicDataSource dataSource()
	{
		final BasicDataSource basicDataSource = new BasicDataSource();
		try
		{
			final URI dbUri = new URI(System.getenv("DATABASE_URL"));
			final String username = dbUri.getUserInfo().split(":")[0];
			final String password = dbUri.getUserInfo().split(":")[1];
			final String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + "?user=" + username + "&password=" + password
			        + "&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

			basicDataSource.setUrl(dbUrl);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);
		}
		catch (final URISyntaxException e)
		{
			e.printStackTrace();
		}
		return basicDataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(buildHibernateProperties());
		configureSessionFactory(sessionFactory);
		return sessionFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager()
	{
		final HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}

	@Bean
	public DatabaseDao databaseDao()
	{
		return new DatabaseDaoPostgreImpl(sessionFactory().getObject());
	}

	private void configureSessionFactory(final LocalSessionFactoryBean sessionFactory)
	{
		sessionFactory.setAnnotatedPackages("edu.gatech.edutech.smarterap.dtos");
		sessionFactory.setPackagesToScan("edu.gatech.edutech.smarterap.dtos");

		//		sessionFactory.setAnnotatedClasses(Tag.class, Course.class, User.class, Subject.class);
	}

	private Properties buildHibernateProperties()
	{
		return new Properties()
		{
			private static final long serialVersionUID = 1L;

			{
				setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
				setProperty("hibernate.show_sql", "false");
				setProperty("hibernate.connection.autocommit", "true");
				//				setProperty("hibernate.hbm2ddl.auto", "validate");
			}
		};
	}

}
