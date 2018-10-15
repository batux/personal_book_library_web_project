package com.personal.book.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.personal.book.library.web" })
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private SigninFailureHandler signinFailureHandler;
	
	@Autowired
	private SigninSuccessHandler signinSuccessHandler;
	
	@Autowired
	private SigninUserDetailsService signinUserDetailsService;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	     
	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(signinUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	    web.ignoring().antMatchers(
	    		"/login.html", 
	    		// "/rest/credit/application/v1/city/list", 
	    		// "/rest/credit/application/v1/town/list/**",
	    		"/js/*", "/img/*", "/css/*", "/fonts/*");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeRequests()
			.anyRequest()
				.authenticated()
				.antMatchers("/rest/**").permitAll()
				.and()
		        .requestCache()
		        .requestCache(new NullRequestCache())
		    .and()
			    .formLogin()
			    	.loginPage("/login.html")
			    	.loginProcessingUrl("/login")
			    	.failureForwardUrl("/login.html")
			    	.usernameParameter("username")
			    	.passwordParameter("password")
			        .successHandler(signinSuccessHandler)
			        .failureHandler(signinFailureHandler)
		    .and()
			    .logout()
			    	.logoutUrl("/logout")
			    	.logoutSuccessUrl("/login.html")
			    	.logoutSuccessHandler(logoutSuccessHandler)
			    	.invalidateHttpSession(true)
			    	.clearAuthentication(true);
		
	}
	
}
