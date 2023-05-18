package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//This annotation tells to spring-security that this class is the web security configuration class.
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter
{
	//
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new UserDetailsServiceImpl();
	}
	/* BCryptPasswordEncoder is implementation  PasswordEncoder interface.which provides two methods
	First is encode() , which takes the raw password and returns the encoded password. 
	The second method is matches() , which compares the raw password and the encoded password.
	whenever a user enters the password it should be able to match with stored password.
	*/
	@Bean
	public BCryptPasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder() ;
		
	}
	/*  DaoAuthenticationProvider is an implementation of authenticationProvider interface  
	  
	    For authentication, the user enters the username and password. But to validate those credentials, 
	    someone needs to provide the actual credential from DB or memory and compare it to authenticate the user.
	    
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoauthenticationProvider =new DaoAuthenticationProvider();
		daoauthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoauthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoauthenticationProvider;
		
	}
	/* CONFIGURE METHODS                                                                                    */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/Admin/**").hasRole("ADMIN")
		.antMatchers("/user**").hasRole("USER")
		.antMatchers("/**").permitAll().and().formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/user/index")
		.and().csrf().disable();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
