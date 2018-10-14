package com.personal.book.library.datalayer.config.jpa;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
//@EnableCaching
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = { "com.personal.book.library.datalayer.entity", 
		"com.personal.book.library.datalayer.repository", "com.personal.book.library.servicelayer" })
@ComponentScan({ "com.personal.book.library.datalayer.entity", 
	"com.personal.book.library.datalayer.repository", "com.personal.book.library.servicelayer" })
public class RepositorySpringConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("database.driver"));
		dataSource.setUrl(environment.getProperty("database.url"));
		dataSource.setUsername(environment.getProperty("database.username"));
		dataSource.setPassword(environment.getProperty("database.password"));
		
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.personal.book.library.datalayer.entity", 
								 "com.personal.book.library.datalayer.repository",
								 "com.personal.book.library.servicelayer");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		factory.setJpaProperties(jpaProperties());
		
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		return new JpaTransactionManager(entityManagerFactory());
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
	
	/* If you want you can configure your cache manager in Java side.
	@Bean(name = "BookLibraryCacheManager")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("categories", "likedegrees");
    }
    */

	private final Properties jpaProperties() {
		
		Properties properties = new Properties();
	    	properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
	    	properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
	    	properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
	    	properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
	    	properties.setProperty("hibernate.connection.characterEncoding", environment.getProperty("hibernate.connection.characterEncoding"));
	    
	    	return properties;
	}
}
